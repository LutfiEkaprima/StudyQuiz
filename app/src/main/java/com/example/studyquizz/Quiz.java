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

public class Quiz extends AppCompatActivity {

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

    static Question[] questions = {
            new Question("1. Dua benda dengan massa yang sama dipasang pada ujung-ujung dari sebuah batang seperti pada gambar, Jika batang dalam kondisi setimbang dan poros dapat berputar, maka batang .... jika benda di sisi kanan didekatkan ke arah poros",
                    "A. akan berputar searah jarum jam ",
                    "B. akan berputar berlawanan arah jarum jam",
                    "C. tidak berputar", "D. tidak berputar",
                    "B. akan berputar berlawanan arah jarum jam",
                    R.drawable.image1),

            new Question("2. Tiga buah pegas dengan konstanta pegas k = 600 N.m-1 disusun seri dan paralel seperti pada gambar, Jika ujung bawah susunan pegas diberi beban sebesar m = 2 kg, maka pertambahan panjang pegas adalah ...",
                    "A. 4 cm",
                    "B. 5 cm",
                    "C. 10 cm",
                    "D. 0,5 cm",
                    "B. 5 cm",
                    R.drawable.image2),

            new Question("3. Seekor kuda bermassa 100 kg memakai sepatu yang ditempeli pegas identik pada keempat kakinya. Tekanan akibat berat badan kuda tersebut terdistribusi merata pada keempat kakinya. Ketika kuda berdiri dengan 4 kaki, pegas menjadi 2 cm lebih pendek. Perubahan panjang pegas jika kuda tersebut berdiri dengan dua kaki adalah ... cm",
                    "A. 2",
                    "B. 2,5",
                    "C. 3", "D. 4",
                    "D. 4",
                    0),

            new Question("4. Nilai konstanta elastisitas yang sama dari percobaan elastisitas karet ban pada tabel berikut adalah",
                    "A. (1) dan (2)",
                    "B. (1) dan (4)",
                    "C. (1) dan (5)",
                    "D. (2) dan (3)",
                    "B. (1) dan (4)",
                    R.drawable.image4),

            new Question("5. Sepotong kawat logam homogen dengan panjang 140 cm dan luas penampangnya 2 mm2 ketika ditarik dengan gaya sebesar 100 N bertambah panjang 1 mm. Modulus elastik bahan kawat logam tersebut adalah ... ",
                    "A. 7 x 108 N/m3",
                    "B. 7 x 109 N/m3",
                    "C. 7 x 1010 N/m3",
                    "D. 7 x 1011 N/m3",
                    "C. 7 x 1010 N/m3",
                    0)
    };


    private boolean quizCompleted;

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
        quizCompleted = sharedPreferences.getBoolean("quizCompleted", false);

        if (quizCompleted) {
            Intent intent = new Intent(Quiz.this, Menu.class);
            Toast.makeText(Quiz.this, "Anda Telah Melakukan Quiz", Toast.LENGTH_SHORT).show();
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
        editor.putInt("totalScore", score);
        editor.putBoolean("quizCompleted", true);
        editor.apply();



        Intent intent = new Intent(Quiz.this, LihatJawabanActivity.class);
        intent.putExtra("questions", questions);
        intent.putExtra("userAnswers", userAnswers);
        intent.putExtra("totalScore", score);
        intent.putExtra("totalQuestions", questions.length);
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
