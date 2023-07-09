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

public class QuizDiskrit extends AppCompatActivity {

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

            new Question("1. Pada relasi rekurens di bawah ini, tentukan bahwa salah satu relasi rekurens tersebut homogen lanjar  ",
                    "A. an = (1.02)an-1",
                    "B. an = an-1 . an-2",
                    "C. an = 7an/2 + an-2 ",
                    "D. an = 7an/2 + an-3",
                    "A. an = (1.02)an-1",
                    0),

            new Question("2. Manakah diantara relasi berikut yang bersifat: refleksif, setangkup, tidak menghantar: ",
                    "A. Himpunan A = {1, 2, 3, 4}, R = {(1,1), (1,3), (2, 2), (2, 3), (2, 4), (3, 3), (4, 1), (4, 3), (4, 4)}",
                    "B. Himpunan A = {1, 2, 3}, R = {(1, 1), (1, 2), (1, 3), (2, 1), (2, 2), (2, 3), (3, 1), (3, 2), (3, 3)}",
                    "C. Himpunan A = {1, 2, 3, 4}, R = {(1,1), (1,3), (2, 2), (2, 3), (2, 4) (3, 3), (3, 4), (4, 3), (4, 4)}h n",
                    "D. Himpunan A = {1, 2, 3, 4}, R = {(1, 1), (1, 2), (1, 4), (2, 1), (2, 2), (2, 3), (3, 2), (3, 3), (4, 1), (4, 4)}",
                    "D. Himpunan A = {1, 2, 3, 4}, R = {(1, 1), (1, 2), (1, 4), (2, 1), (2, 2), (2, 3), (3, 2), (3, 3), (4, 1), (4, 4)}",
                    0),

            new Question("3. Fungsi f(n) = floor(n/5) untuk n bilangan bulat memiliki sifat: . . ",
                    "A. Injektif, surjektif, bijektif ",
                    "B. Injektif, tidak surjektif, tidak bijektif",
                    "C. Tidak injektif, surjektif, tidak bijektif ",
                    "D. Bukan fungsi ",
                    "C. Tidak injektif, surjektif, tidak bijektif ",
                    0),

            new Question("4. Berapa banyak solusi dari persamaan x1+x2+x3+x4=16yang dipunya dengan x1,x2,x3,x4adalah bilangan bulat nonnegatif? ",
                    "A. 969 Solusi",
                    "B. 302 Solusi ",
                    "C. 959 Solusi",
                    "D. 303 Solusi ",
                    "A. 969 Solusi",
                    0),

            new Question("5. Berapa peluang bilangan bulat positif yang habis dibagi 3 atau 5 terpilih secara acak dari himpunan semua bilangan bulat positif tak lebih dari 50 !",
                    "A. 13/50 ",
                    "B. 3/50 ",
                    "C. 33/50 ",
                    "D. 23/50 ",
                    "D. 23/50 ",
                    0),
    };


    private boolean quizDiskritCompleted;

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
        quizDiskritCompleted = sharedPreferences.getBoolean("quizDiskritCompleted", false);

        if (quizDiskritCompleted) {
            Intent intent = new Intent(QuizDiskrit.this, MenuMahasiswa.class);
            Toast.makeText(QuizDiskrit.this, "Anda Telah Melakukan Quiz", Toast.LENGTH_SHORT).show();
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
        editor.putInt("quizdiskrit", score);
        editor.putBoolean("quizDiskritCompleted", true);
        editor.apply();


        Intent intent = new Intent(QuizDiskrit.this, MenuMahasiswa.class);
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
