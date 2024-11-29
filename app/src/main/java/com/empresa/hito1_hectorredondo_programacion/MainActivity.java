package com.empresa.hito1_hectorredondo_programacion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnStartGame, btnScoreboard, btnContact;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = getIntent().getStringExtra("username");

        btnStartGame = findViewById(R.id.btn_start_game);
        btnScoreboard = findViewById(R.id.btn_scoreboard);
        btnContact = findViewById(R.id.btn_contact);

        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        btnScoreboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ScoreboardActivity.class));
            }
        });

        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ContactActivity.class));
            }
        });
    }
}