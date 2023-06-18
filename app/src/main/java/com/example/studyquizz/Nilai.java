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
        TextView nilaisejarah = findViewById(R.id.text_view_total_scoresjr);
        TextView nilaibindo = findViewById(R.id.nilai_bindo);

        int totalScore = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("totalScore", 0);
        int sejarah = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("totalScoresejarah", 0);
        int nilai = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("nilai", 0);
        int bindo = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("totalScoreindo", 0);


        textViewTotalScore.setText("Nilai Quiz   : " + totalScore * 20);
        nilaisejarah.setText("Nilai Quiz    : " + sejarah * 20);
        nilaikimia1.setText("Nilai Ujian    : " + nilai * 20);
        nilaibindo.setText("Nilai Ujian     : " + bindo * 20);
    }
}
