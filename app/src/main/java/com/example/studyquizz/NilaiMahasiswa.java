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
        TextView nilaiquizpbo = findViewById(R.id.text_view_total_scorepbo);
        TextView nilaiujianpbo = findViewById(R.id.nilai_pbo);
        TextView nilaiquizaorkom = findViewById(R.id.text_view_total_scoreaorkom);
        TextView nilaiujianaorkom = findViewById(R.id.nilai_aorkom);
        TextView nilaiquizimk = findViewById(R.id.text_view_total_scoreimk);
        TextView nilaiujianimk = findViewById(R.id.nilai_imk);
        TextView nilaiquizstatistika = findViewById(R.id.text_view_total_scorestk);
        TextView nilaiujianstatistika = findViewById(R.id.nilai_stk);
        TextView nilaiquizstrukdat = findViewById(R.id.text_view_total_scorestrukdat);
        TextView nilaiujianstrukdat = findViewById(R.id.nilai_strukdat);

        int quizmtk = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("quizmtk", 0);
        int ujianmtk = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("ujianmtk", 0);
        int quizmtkdisk = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("quizdiskrit", 0);
        int ujiandisk = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("ujiandiskrit", 0);
        int quizpbo = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("quizpbo", 0);
        int ujianpbo = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("ujianpbo", 0);
        int quizaorkom = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("nilaiquizaorkom", 0);
        int ujianaorkom = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("nilaiujianaorkom", 0);
        int quizimk = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("nilaiquizimk", 0);
        int ujianimk = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("nilaiujianimk", 0);
        int quizstatistika = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("nilaiquizstatistika", 0);
        int ujianstatistika = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("nilaiujianstatistika", 0);
        int quizstrukdat = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("nilaiquizstrukdat", 0);
        int ujianstrukdat = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("nilaiujianstrukdat", 0);


        textViewTotalScore.setText("Nilai Quiz   : " + quizmtk * 20);
        nilaiujianmtk.setText("Nilai Ujian    : " + ujianmtk * 20);
        nilaiquizmtkdisk.setText("Nilai Quiz    : " + quizmtkdisk * 20);
        nilaiujianmtkdisk.setText("Nilai Ujian     : " + ujiandisk * 20);
        nilaiquizpbo.setText("Nilai Quiz     : " + quizpbo * 20);
        nilaiujianpbo.setText("Nilai Ujian     : " + ujianpbo * 20);
        nilaiquizaorkom.setText("Nilai Quiz     : " + quizaorkom * 20);
        nilaiujianaorkom.setText("Nilai Ujian     : " + ujianaorkom * 20);
        nilaiquizimk.setText("Nilai Quiz     : " + quizimk * 20);
        nilaiujianimk.setText("Nilai Ujian     : " + ujianimk * 20);
        nilaiquizstatistika.setText("Nilai Quiz     : " + quizstatistika * 20);
        nilaiujianstatistika.setText("Nilai Ujian     : " + ujianstatistika * 20);
        nilaiquizstrukdat.setText("Nilai Quiz     : " + quizstrukdat * 20);
        nilaiujianstrukdat.setText("Nilai Ujian     : " + ujianstrukdat * 20);
    }
}
