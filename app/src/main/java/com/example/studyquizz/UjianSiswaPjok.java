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

public class UjianSiswaPjok extends AppCompatActivity {

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

            new Question("1. Apa manfaat dari melatih kekuatan otot?",
                    "A. Meningkatkan daya tahan otot",
                    "B. Membantu pembentukan massa otot",
                    "C. Meningkatkan fungsi metabolisme",
                    "D. Semua jawaban di atas benar",
                    "D. Semua jawaban di atas benar",
                    0),

            new Question("2. Apa yang dimaksud dengan kebugaran kardiovaskular?",
                    "A. Kemampuan otot jantung untuk memompa darah",
                    "B. Kemampuan tubuh untuk bertahan dalam aktivitas fisik berat",
                    "C. Kemampuan tubuh untuk memperbaiki kerusakan sel-sel",
                    "D. Kemampuan tubuh untuk menjaga suhu tubuh dalam rentang normal",
                    "A. Kemampuan otot jantung untuk memompa darah",
                    0),

            new Question("3. Apa tujuan dari melakukan pemanasan sebelum berolahraga?",
                    "A. Mencegah cedera otot",
                    "B. Meningkatkan suhu tubuh",
                    "C. Meningkatkan performa fisik",
                    "D. Semua jawaban di atas benar",
                    "D. Semua jawaban di atas benar",
                    0),

            new Question("4. Apa yang dimaksud dengan fleksibilitas?",
                    "A. Kemampuan tubuh untuk melakukan gerakan dengan amplitudo penuh",
                    "B. Kemampuan tubuh untuk menghindari cedera saat berolahraga",
                    "C. Kemampuan tubuh untuk memperbaiki kerusakan sel-sel",
                    "D. Semua jawaban di atas benar",
                    "A. Kemampuan tubuh untuk melakukan gerakan dengan amplitudo penuh",
                    0),

            new Question("5. Apa yang dimaksud dengan latihan kebugaran anaerobik?",
                    "A. Latihan yang dilakukan dengan intensitas tinggi dan melibatkan pemulihan oksigen",
                    "B. Latihan yang dilakukan dengan intensitas rendah dan berlangsung dalam waktu lama",
                    "C. Latihan yang dilakukan untuk meningkatkan fleksibilitas tubuh",
                    "D. Latihan yang dilakukan untuk meningkatkan kekuatan otot",
                    "A. Latihan yang dilakukan dengan intensitas tinggi dan melibatkan pemulihan oksigen",
                    0)

    };

    private boolean ujianpjok;

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
        ujianpjok = sharedPreferences.getBoolean("ujianpjok", false);

        if (ujianpjok) {
            Intent intent = new Intent(UjianSiswaPjok.this, Menu.class);
            Toast.makeText(UjianSiswaPjok.this, "Anda Telah Melakukan Ujian", Toast.LENGTH_SHORT).show();
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
        editor.putInt("nilaiujianpjok", score);
        editor.putBoolean("ujianpjok", true);
        editor.apply();


        Intent intent = new Intent(UjianSiswaPjok.this, Menu.class);
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
