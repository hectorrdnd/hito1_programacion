package com.empresa.hito1_hectorredondo_programacion;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    private Button btnIncrementScore, btnSaveScore;
    private TextView txtScore, txtLevel, txtTargetScore, txtTimer;
    private String username;
    private int score = 0; // Puntuación inicial
    private int level = 1; // Nivel inicial
    private int targetScore = 10; // Puntuación objetivo inicial
    private long timeLeftInMillis = 30000; // Tiempo inicial en milisegundos (30 segundos)
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        username = getIntent().getStringExtra("username");

        btnIncrementScore = findViewById(R.id.btn_increment_score);
        btnSaveScore = findViewById(R.id.btn_save_score);
        txtScore = findViewById(R.id.txt_score);
        txtLevel = findViewById(R.id.txt_level);
        txtTargetScore = findViewById(R.id.txt_target_score);
        txtTimer = findViewById(R.id.txt_timer);

        updateUI();
        startTimer();

        btnIncrementScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score++;
                if (score >= targetScore) {
                    levelUp();
                }
                updateUI();
            }
        });

        btnSaveScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this, ScoreboardActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("score", score);
                startActivity(intent);
            }
        });
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                // Fin del tiempo, reiniciar el nivel
                score = 0;
                updateUI();
                startTimer();
            }
        }.start();
    }

    private void levelUp() {
        level++;
        targetScore += 10; // Aumentar la puntuación objetivo para el siguiente nivel
        score = 0; // Reiniciar la puntuación para el nuevo nivel
        timeLeftInMillis -= 5000; // Reducir el tiempo disponible en 5 segundos por nivel
        startTimer(); // Reiniciar el temporizador
    }

    private void updateUI() {
        txtScore.setText("Score: " + score);
        txtLevel.setText("Level: " + level);
        txtTargetScore.setText("Target Score: " + targetScore);
    }

    private void updateTimer() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeFormatted = String.format("%02d:%02d", minutes, seconds);
        txtTimer.setText("Time: " + timeFormatted);
    }
}