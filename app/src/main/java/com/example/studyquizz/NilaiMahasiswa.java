package com.example.studyquizz;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NilaiMahasiswa extends AppCompatActivity {
    private TextView textViewTotalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilai_mahasiswa);

        textViewTotalScore = findViewById(R.id.text_view_total_scoremtk);
        TextView nilaiujianmtk = findViewById(R.id.nilai_mtk);
        TextView nilaiquizmtkdisk = findViewById(R.id.text_view_total_scoredisk);
        TextView nilaiujianmtkdisk = findViewById(R.id.nilai_mtkdisk);
        TextView nilaiujianpbo = findViewById(R.id.text_view_total_scorepbo);
        TextView nilaiquizpbo = findViewById(R.id.nilai_pbo);

        int quizmtk = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("quizmtk", 0);
        int ujianmtk = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("ujianmtk", 0);
        int quizmtkdisk = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("quizdiskrit", 0);
        int ujiandisk = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("ujiandiskrit", 0);
        int quizpbo = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("quizpbo", 0);
        int ujianpbo = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("ujianpbo", 0);


        textViewTotalScore.setText("Nilai Quiz   : " + quizmtk * 20);
        nilaiujianmtk.setText("Nilai Ujian    : " + ujianmtk * 20);
        nilaiquizmtkdisk.setText("Nilai Quiz    : " + quizmtkdisk * 20);
        nilaiujianmtkdisk.setText("Nilai Ujian     : " + ujiandisk * 20);
        nilaiquizpbo.setText("Nilai Quiz     : " + quizpbo * 20);
        nilaiujianpbo.setText("Nilai Ujian     : " + ujianpbo * 20);
    }
}
