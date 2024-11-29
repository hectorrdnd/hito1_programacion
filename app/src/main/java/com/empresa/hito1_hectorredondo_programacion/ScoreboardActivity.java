package com.empresa.hito1_hectorredondo_programacion;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ScoreboardActivity extends AppCompatActivity {

    private TextView txtScoreboard;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        txtScoreboard = findViewById(R.id.txt_scoreboard);
        sharedPreferences = getSharedPreferences("Scoreboard", MODE_PRIVATE);

        String username = getIntent().getStringExtra("username");
        int score = getIntent().getIntExtra("score", 0);

        // Save the score
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(username, score);
        editor.apply();

        // Display the score
        StringBuilder scoreboardText = new StringBuilder();
        scoreboardText.append("User: ").append(username).append("\nScore: ").append(score).append("\n\n");

        // Retrieve and display all scores
        for (String key : sharedPreferences.getAll().keySet()) {
            int savedScore = sharedPreferences.getInt(key, 0);
            scoreboardText.append("User: ").append(key).append(" - Score: ").append(savedScore).append("\n");
        }

        txtScoreboard.setText(scoreboardText.toString());
    }
}