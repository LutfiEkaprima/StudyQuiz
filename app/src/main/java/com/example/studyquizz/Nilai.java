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
        TextView nilaiquizagama = findViewById(R.id.text_view_total_scorepai);
        TextView nilaiquizbindo = findViewById(R.id.text_view_total_scorebindo);
        TextView nilaiquizbing = findViewById(R.id.text_view_total_scorebing);
        TextView nilaiquizbiologi = findViewById(R.id.text_view_total_scorebio);
        TextView nilaiquizekonomi = findViewById(R.id.text_view_total_scoreeko);
        TextView nilaiquizkimia = findViewById(R.id.text_view_total_scorekimia);
        TextView nilaiquizmtk = findViewById(R.id.text_view_total_scoremtk);
        TextView nilaiquizpjok = findViewById(R.id.text_view_total_scorepjok);
        TextView nilaiquizpkn = findViewById(R.id.text_view_total_scorepkn);
        TextView nilaiquizsbk = findViewById(R.id.text_view_total_scoresbk);
        TextView nilaiujianagama = findViewById(R.id.nilai_pai);
        TextView nilaiujianbing = findViewById(R.id.nilai_bing);
        TextView nilaiujianbbio = findViewById(R.id.nilai_bio);
        TextView nilaiujianeko = findViewById(R.id.nilai_eko);
        TextView nilaiujianfisika = findViewById(R.id.nilai_fsk);
        TextView nilaiujianmtk = findViewById(R.id.nilai_mtk);
        TextView nilaiujianpjok = findViewById(R.id.nilai_pjok);
        TextView nilaiujianpkn = findViewById(R.id.nilai_pkn);
        TextView nilaiujiansbk = findViewById(R.id.nilai_sbk);
        TextView nilaiujiansejarah = findViewById(R.id.nilai_sjr);



        int totalScore = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("totalScore", 0);
        int sejarah = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("totalScoresejarah", 0);
        int nilai = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("nilai", 0);
        int bindo = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("totalScoreindo", 0);
        int quizagama = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("nilaiquizagama", 0);
        int quizbindo = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("nilaiquizbindo", 0);
        int quizbing = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("nilaiquizbing", 0);
        int quizbiologi = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("nilaiquizbiologi", 0);
        int quizekonomi = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("nilaiquizekonomi", 0);
        int quizkimia = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("nilaiquizkimia", 0);
        int quizmtk = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("nilaiquizmtk", 0);
        int quizpjok = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("nilaiquizpjok", 0);
        int quizpkn = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("nilaiquizpkn", 0);
        int quizsbk = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("nilaiquizsbk", 0);
        int ujianagama = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("nilaiujianagama", 0);
        int ujianbing = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("nilaiujianbing", 0);
        int ujianbio = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("nilaiujianbiologi", 0);
        int ujianeko = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("nilaiujianekonomi", 0);
        int ujianfisika = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("nilaiujianfisika", 0);
        int ujianmtk = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("nilaiujianmtk", 0);
        int ujianpjok = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("nilaiujianpjok", 0);
        int ujianpkn = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("nilaiujianpkn", 0);
        int ujiansbk = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("nilaiujiansbk", 0);
        int ujiansejarah = getSharedPreferences("MyPrefs", MODE_PRIVATE).getInt("nilaiujiansejarah", 0);


        textViewTotalScore.setText("Nilai Quiz   : " + totalScore * 20);
        nilaisejarah.setText("Nilai Quiz    : " + sejarah * 20);
        nilaikimia1.setText("Nilai Ujian    : " + nilai * 20);
        nilaibindo.setText("Nilai Quiz      : " + bindo * 20);
        nilaiquizagama.setText("Nilai Quiz      : " + quizagama * 20);
        nilaiquizbindo.setText("Nilai Quiz      : " + quizbindo * 20);
        nilaiquizbing.setText("Nilai Quiz      : " + quizbing * 20);
        nilaiquizbiologi.setText("Nilai Quiz      : " + quizbiologi * 20);
        nilaiquizekonomi.setText("Nilai Quiz      : " + quizekonomi * 20);
        nilaiquizkimia.setText("Nilai Quiz      : " + quizkimia * 20);
        nilaiquizmtk.setText("Nilai Quiz      : " + quizmtk * 20);
        nilaiquizpjok.setText("Nilai Quiz      : " + quizpjok * 20);
        nilaiquizpkn.setText("Nilai Quiz      : " + quizpkn * 20);
        nilaiquizsbk.setText("Nilai Quiz      : " + quizsbk * 20);
        nilaiujianagama.setText("Nilai Ujian      : " + ujianagama * 20);
        nilaiujianbing.setText("Nilai Ujian      : " + ujianbing * 20);
        nilaiujianbbio.setText("Nilai Ujian      : " + ujianbio * 20);
        nilaiujianeko.setText("Nilai Ujian      : " + ujianeko * 20);
        nilaiujianfisika.setText("Nilai Ujian      : " + ujianfisika * 20);
        nilaiujianmtk.setText("Nilai Ujian      : " + ujianmtk * 20);
        nilaiujianpjok.setText("Nilai Ujian      : " + ujianpjok * 20);
        nilaiujianpkn.setText("Nilai Ujian      : " + ujianpkn * 20);
        nilaiujiansbk.setText("Nilai Ujian      : " + ujiansbk * 20);
        nilaiujiansejarah.setText("Nilai Ujian      : " + ujiansejarah * 20);

    }
}
