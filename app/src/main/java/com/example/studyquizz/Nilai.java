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
        TextView nilaikimia1 = findViewById(R.id.nilai_kimia);

        int totalScore = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("totalScore", 0);
        int nilai = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("nilai", 0);


        textViewTotalScore.setText("Nilai Quiz   : " + totalScore * 20);
        nilaikimia1.setText("Nilai Ujian    : " + nilai * 20);
    }
}
