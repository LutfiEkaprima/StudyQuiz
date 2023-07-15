package com.example.studyquizz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class LihatJawabanActivity extends AppCompatActivity {

    private LinearLayout linearLayoutContainer;
    private Question[] questions;
    private int[] userAnswers;
    private int totalScore;
    private int totalQuestions;

    private TextView textViewJawabanBenar;
    private TextView textViewJawabanSalah;
    private TextView textViewNilaiAkhir;
    private Button buttonLihatJawaban;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_jawaban);

        textViewJawabanBenar = findViewById(R.id.text_view_jawaban_benar);
        textViewJawabanSalah = findViewById(R.id.text_view_jawaban_salah);
        textViewNilaiAkhir = findViewById(R.id.text_view_nilai_akhir);
        buttonLihatJawaban = findViewById(R.id.button_lihat_jawaban);

        // Mengambil data dari Intent
        Intent intent = getIntent();
        totalScore = intent.getIntExtra("totalScore", 0);
        totalQuestions = intent.getIntExtra("totalQuestions", 0);

        textViewJawabanBenar.setText("Jawaban Benar: " + totalScore);
        textViewJawabanSalah.setText("Jawaban Salah: " + (totalQuestions - totalScore));
        textViewNilaiAkhir.setText("Nilai Akhir: " + totalScore *20);

        linearLayoutContainer = findViewById(R.id.linear_layout_container);

        // Mengambil data dari Intent
        questions = (Question[]) getIntent().getSerializableExtra("questions");
        userAnswers = getIntent().getIntArrayExtra("userAnswers");

        buttonLihatJawaban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LihatJawabanActivity.this, Menu.class);
                startActivity(intent);
                finish();
            }
        });

        if (questions != null && userAnswers != null) {
            for (int i = 0; i < questions.length; i++) {
                addQuestionView(i, questions[i], userAnswers[i]);
            }
        }
    }


    private void addQuestionView(int questionIndex, Question question, int userAnswer) {
        LinearLayout questionLayout = new LinearLayout(this);
        questionLayout.setOrientation(LinearLayout.VERTICAL);

        TextView textViewQuestion = new TextView(this);
        textViewQuestion.setText(question.getQuestion());
        textViewQuestion.setTextColor(ContextCompat.getColor(this, R.color.textColor1));

        questionLayout.addView(textViewQuestion);

        RadioGroup radioGroupOptions = new RadioGroup(this);
        String[] options = question.getOptions();
        for (int i = 0; i < options.length; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(options[i]);
            radioButton.setEnabled(false);

            if (userAnswer == i) {
                if (options[i].equals(question.getCorrectAnswer())) {
                    // Jawaban pengguna yang benar berwarna hijau
                    radioButton.setTextColor(Color.GREEN);
                } else {
                    // Jawaban pengguna yang salah berwarna merah
                    radioButton.setTextColor(Color.RED);
                }
                radioButton.setChecked(true);
            } else {
                // Jawaban yang tidak dipilih berwarna hitam
                radioButton.setTextColor(Color.BLACK);
            }

            radioGroupOptions.addView(radioButton);
        }

        questionLayout.addView(radioGroupOptions);

        linearLayoutContainer.addView(questionLayout);
    }
}
