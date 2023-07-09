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

public class QuizMahasiswaStatistika extends AppCompatActivity {

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
            new Question("1. Apa yang dimaksud dengan regresi linear sederhana?",
                    "A. Metode untuk memprediksi nilai variabel independen",
                    "B. Metode untuk memprediksi nilai variabel dependen",
                    "C. Metode untuk menentukan korelasi antara variabel independen",
                    "D. Metode untuk menentukan korelasi antara variabel dependen",
                    "B. Metode untuk memprediksi nilai variabel dependen",
                    0),

            new Question("2. Apa yang dimaksud dengan koefisien determinasi (R^2)?",
                    "A. Seberapa dekat hubungan linear antara dua variabel",
                    "B. Seberapa baik model regresi linear memprediksi data",
                    "C. Persentase variasi variabel dependen yang dapat dijelaskan oleh variabel independen",
                    "D. Persentase variasi variabel independen yang dapat dijelaskan oleh variabel dependen",
                    "C. Persentase variasi variabel dependen yang dapat dijelaskan oleh variabel independen",
                    0),

            new Question("3. Apa yang dimaksud dengan metode pengambilan sampel acak sederhana?",
                    "A. Setiap anggota populasi memiliki peluang yang sama untuk dipilih dalam sampel",
                    "B. Sampel dipilih berdasarkan karakteristik tertentu yang diinginkan",
                    "C. Sampel dipilih dengan metode undian",
                    "D. Semua jawaban benar",
                    "A. Setiap anggota populasi memiliki peluang yang sama untuk dipilih dalam sampel",
                    0),

            new Question("4. Apa yang dimaksud dengan interval kepercayaan?",
                    "A. Rentang nilai yang mungkin mengandung parameter populasi",
                    "B. Rentang nilai yang mengandung sampel acak dari populasi",
                    "C. Rentang nilai yang mengandung persentase tertentu dari data sampel",
                    "D. Rentang nilai yang mengandung persentase tertentu dari populasi",
                    "A. Rentang nilai yang mungkin mengandung parameter populasi",
                    0),

            new Question("5. Apa yang dimaksud dengan analisis varians (ANOVA)?",
                    "A. Metode untuk membandingkan rata-rata antara lebih dari dua kelompok",
                    "B. Metode untuk menentukan korelasi antara variabel independen",
                    "C. Metode untuk memprediksi nilai variabel dependen",
                    "D. Metode untuk menentukan persentase variasi yang dijelaskan oleh regresi linear",
                    "A. Metode untuk membandingkan rata-rata antara lebih dari dua kelompok",
                    0)


    };


    private boolean quizstatistika;

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
        quizstatistika = sharedPreferences.getBoolean("quizstatistika", false);

        if (quizstatistika) {
            Intent intent = new Intent(QuizMahasiswaStatistika.this, MenuMahasiswa.class);
            Toast.makeText(QuizMahasiswaStatistika.this, "Anda Telah Melakukan Quiz", Toast.LENGTH_SHORT).show();
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
        editor.putInt("nilaiquizstatistika", score);
        editor.putBoolean("quizstatistika", true);
        editor.apply();


        Intent intent = new Intent(QuizMahasiswaStatistika.this, MenuMahasiswa.class);
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
