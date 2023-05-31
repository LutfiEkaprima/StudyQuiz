package com.example.studyquizz;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Nilai extends AppCompatActivity {
    private TextView textViewTotalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilai);

        textViewTotalScore = findViewById(R.id.text_view_total_score);

        // Ambil total skor dari SharedPreferences
        int totalScore = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("totalScore", 0);

        // Tampilkan total skor
        textViewTotalScore.setText("Total Skor: " + totalScore + "/5");
    }
}
