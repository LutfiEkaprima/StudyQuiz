package com.example.studyquizz;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UjianMahasiswaStatistika extends AppCompatActivity {

    private TextView textViewQuestion;
    private RadioGroup radioGroupOptions;
    private Button buttonNext;
    private Button buttonBack;
    private Button buttonSubmit;

    private int currentQuestionIndex = 0;
    private int score = 0;
    private int[] userAnswers;
    private boolean[] questionAnswered;
    private ImageView imageViewQuestion;


    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 900000; //

    Question[] questions = {
            new Question("1. Sebuah perusahaan ingin mengetahui rata-rata pengeluaran bulanan dari karyawan di departemen mereka. Dari populasi 500 karyawan, mereka mengambil sampel acak sederhana sebanyak 50 karyawan. Setelah menghitung, diperoleh rata-rata pengeluaran bulanan sebesar $1,200 dengan simpangan baku sebesar $200. Berapa estimasi rata-rata pengeluaran bulanan untuk seluruh karyawan di perusahaan dengan tingkat kepercayaan 95%?",
                    "A. $1,180",
                    "B. $1,200",
                    "C. $1,220",
                    "D. $1,240",
                    "C. $1,220",
                    0),

            new Question("2. Sebuah toko pakaian ingin mengetahui apakah terdapat perbedaan signifikan dalam tingkat kepuasan pelanggan terhadap dua merek pakaian yang mereka jual. Merek A memiliki 100 responden dengan tingkat kepuasan rata-rata 4.2 dan simpangan baku 0.6, sedangkan merek B memiliki 120 responden dengan tingkat kepuasan rata-rata 4.6 dan simpangan baku 0.8. Dengan tingkat signifikansi 0.05, apakah terdapat perbedaan signifikan dalam tingkat kepuasan pelanggan antara kedua merek pakaian?",
                    "A. Tidak ada perbedaan signifikan",
                    "B. Terdapat perbedaan signifikan",
                    "C. Tidak dapat ditentukan",
                    "D. Tidak ada informasi yang cukup",
                    "B. Terdapat perbedaan signifikan",
                    0),

            new Question("3. Seorang peneliti ingin mengetahui apakah terdapat korelasi antara tingkat pendidikan dan pendapatan per tahun. Dari 200 responden yang diambil, diperoleh koefisien korelasi Pearson sebesar 0.72 dengan tingkat signifikansi 0.01. Berdasarkan hasil ini, apa kesimpulan yang dapat diambil?",
                    "A. Terdapat korelasi positif yang signifikan antara tingkat pendidikan dan pendapatan",
                    "B. Terdapat korelasi negatif yang signifikan antara tingkat pendidikan dan pendapatan",
                    "C. Tidak terdapat korelasi yang signifikan antara tingkat pendidikan dan pendapatan",
                    "D. Tidak dapat ditentukan",
                    "A. Terdapat korelasi positif yang signifikan antara tingkat pendidikan dan pendapatan",
                    0),

            new Question("4. Sebuah perusahaan teknologi ingin menguji apakah rata-rata umur baterai pada dua model smartphone yang mereka produksi sama. Dari sampel acak sederhana sebanyak 100 baterai pada masing-masing model, diperoleh rata-rata umur baterai model A sebesar 18 jam dengan simpangan baku 2 jam, sedangkan rata-rata umur baterai model B sebesar 17 jam dengan simpangan baku 2.5 jam. Dengan tingkat signifikansi 0.05, apakah terdapat perbedaan signifikan dalam rata-rata umur baterai antara kedua model smartphone?",
                    "A. Tidak ada perbedaan signifikan",
                    "B. Terdapat perbedaan signifikan",
                    "C. Tidak dapat ditentukan",
                    "D. Tidak ada informasi yang cukup",
                    "B. Terdapat perbedaan signifikan",
                   0),

            new Question("5. Sebuah penelitian ingin menguji apakah terdapat perbedaan signifikan dalam performa belajar antara dua kelompok siswa yang diajar dengan dua metode pengajaran yang berbeda. Dari sampel acak sebanyak 80 siswa pada masing-masing kelompok, diperoleh rata-rata nilai kelompok A sebesar 85 dengan simpangan baku 6, sedangkan rata-rata nilai kelompok B sebesar 82 dengan simpangan baku 5. Dengan tingkat signifikansi 0.01, apakah terdapat perbedaan signifikan dalam performa belajar antara kedua kelompok siswa?",
                    "A. Tidak ada perbedaan signifikan",
                    "B. Terdapat perbedaan signifikan",
                    "C. Tidak dapat ditentukan",
                    "D. Tidak ada informasi yang cukup",
                    "B. Terdapat perbedaan signifikan",
                    0)


    };

    private boolean ujianstatistika;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        imageViewQuestion = findViewById(R.id.image_view_question);


        textViewQuestion = findViewById(R.id.text_view_question);
        radioGroupOptions = findViewById(R.id.radio_group_options);
        buttonNext = findViewById(R.id.button_next);
        buttonBack = findViewById(R.id.button_back);
        buttonSubmit = findViewById(R.id.button_submit);

        userAnswers = new int[questions.length];
        questionAnswered = new boolean[questions.length];
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        ujianstatistika = sharedPreferences.getBoolean("ujianstatistika", false);

        if (ujianstatistika) {
            Intent intent = new Intent(UjianMahasiswaStatistika.this, MenuMahasiswa.class);
            Toast.makeText(UjianMahasiswaStatistika.this, "Anda Telah Melakukan Ujian", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        }

        userAnswers = new int[questions.length];
        questionAnswered = new boolean[questions.length];


        showQuestion(currentQuestionIndex);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserAnswer();

                if (currentQuestionIndex < questions.length - 1) {
                    currentQuestionIndex++;
                    showQuestion(currentQuestionIndex);
                } else {
                    finishQuiz();
                }
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex--;
                    showQuestion(currentQuestionIndex);
                }
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishQuiz();
            }
        });

        startTimer();
    }

    private void showQuestion(int questionIndex) {
        textViewQuestion.setText(questions[questionIndex].getQuestion());

        if (questions[questionIndex].getImageResource() != 0) {
            imageViewQuestion.setVisibility(View.VISIBLE);
            imageViewQuestion.setImageResource(questions[questionIndex].getImageResource());
        } else {
            imageViewQuestion.setVisibility(View.GONE);
        }


        RadioButton[] radioButtons = new RadioButton[4];
        for (int i = 0; i < 4; i++) {
            radioButtons[i] = (RadioButton) radioGroupOptions.getChildAt(i);
            radioButtons[i].setText(questions[questionIndex].getOptions()[i]);
        }

        radioGroupOptions.clearCheck();
        if (questionAnswered[questionIndex]) {
            radioButtons[userAnswers[questionIndex]].setChecked(true);
        }

        if (questionIndex == 0) {
            buttonBack.setVisibility(View.INVISIBLE);
        } else {
            buttonBack.setVisibility(View.VISIBLE);
        }

        if (questionIndex == questions.length - 1) {
            buttonNext.setText("Finish");
            buttonSubmit.setVisibility(View.INVISIBLE);
        } else {
            buttonNext.setText("Next");
            buttonSubmit.setVisibility(View.GONE);
        }
    }

    private void saveUserAnswer() {
        int selectedAnswerIndex = radioGroupOptions.indexOfChild(findViewById(radioGroupOptions.getCheckedRadioButtonId()));
        if (selectedAnswerIndex != -1) {
            userAnswers[currentQuestionIndex] = selectedAnswerIndex;
            questionAnswered[currentQuestionIndex] = true;
        } else {
            userAnswers[currentQuestionIndex] = -1;
            questionAnswered[currentQuestionIndex] = false;
        }
    }

    private void finishQuiz() {
        score = calculateScore();
        Toast.makeText(this, "Skor Anda: " + score + "/" + questions.length, Toast.LENGTH_SHORT).show();

        buttonNext.setEnabled(false);
        buttonBack.setEnabled(false);
        buttonSubmit.setEnabled(false);
        countDownTimer.cancel();

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("nilaiujianstatistika", score);
        editor.putBoolean("ujianstatistika", true);
        editor.apply();


        Intent intent = new Intent(UjianMahasiswaStatistika.this, MenuMahasiswa.class);
        startActivity(intent);
        finish();
    }

    private int calculateScore() {
        int totalScore = 0;
        for (int i = 0; i < questions.length; i++) {
            if (questionAnswered[i] && questions[i].getCorrectAnswer().equals(questions[i].getOptions()[userAnswers[i]])) {
                totalScore++;
            }
        }
        return totalScore;
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimerText();
            }

            @Override
            public void onFinish() {
                finishQuiz();
            }
        }.start();
    }

    private void updateTimerText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);

        TextView textViewTimer = findViewById(R.id.text_view_timer);
        textViewTimer.setText(timeLeftFormatted);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda ingin menyelesaikan quiz?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishQuiz();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
