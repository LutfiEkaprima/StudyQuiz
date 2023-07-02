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

public class UjianDiskrit extends AppCompatActivity {

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
            new Question("1. Berapa banyak kombinasi 9 kartu dapat dibentuk dari satu deck kartu jika dikehendaki 4 kartu diantaranya adalah kartu diamond?",
                    "A. 20.000.500 ",
                    "B. 411.666.255",
                    "C.45.000",
                    "D. 190.000.000",
                    "B. 411.666.255",
                    0),

            new Question("2. Jika sebuah dadu dilempar 5 kali, maka contoh instan dari suatu “kejadian” adalah 3-1-2-4-1.  Berapa  jenis “kejadian” berbeda yang memuat kemunculan angka “1” tepat 2 kali ? ",
                    "A. 100",
                    "B.  500",
                    "C. 950",
                    "D. 1250",
                    "D. 1250",
                    0),

            new Question("3. Dalam seleksi penerima beasiswa prestasi, setiap mahasiswa harus lulus tes TPA (Tes PotensiAkademik) dan Bahasa Inggris. Dari 180 peserta terdapat 103 orang dinyatakan lulus tes TPA dan 142 orang lulus tes Bahasa. Banyak siswa yang dinyatakan lulus sebagai penerima beasiswa prestasi adalah . . ",
                    "A. 20 Orang ",
                    "B. 40 Orang ",
                    "C. 65 Orang ",
                    "D. 75 Orang ",
                    "C. 65 Orang ",
                    0),

            new Question("4. Himpunan ganda ( Multi set) adalah himpunan yang angotanya boleh berulang. A dan B adalah himpunan ganda, \n\nA= { a, a, a, b, b, c } \n\nB= { a, a, b, b, b, d, d , d, d }. \n\nPernyataan yang benar dari operasidibawah adalah",
                    "A. A∩ B = {a, a, b, b, c, d } ",
                    "B. A – B= {a, b, b, c} ",
                    "C. A + B= {a, a, a, a,a, b, b, b, c, d} ",
                    "D. A∩ B = { a, a, b, b }",
                    "D. A∩ B = { a, a, b, b }",
                    0),

            new Question("5. Diberikan fungsi bilangan riil sebagai berikut: \n\nf(x) = (x + 1)/(x + 2). \n\nFungsi tersebut bersifat:",
                    "A. Injektif, surjektif, bijektif ",
                    "B. Tidak injektif, surjektif, tidak bijektif ",
                    "C. Injektif, tidak surjektif, tidak bijektif ",
                    "D. Bukan fungsi ",
                    "D. Bukan fungsi ",
                    0),
    };

    private boolean ujiandiskritCompleted;

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
        ujiandiskritCompleted = sharedPreferences.getBoolean("ujiandiskritCompleted", false);

        if (ujiandiskritCompleted) {
            Intent intent = new Intent(UjianDiskrit.this, MenuMahasiswa.class);
            Toast.makeText(UjianDiskrit.this, "Anda Telah Melakukan Ujian", Toast.LENGTH_SHORT).show();
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
        editor.putInt("ujiandiskrit", score);
        editor.putBoolean("ujiandiskritCompleted", true);
        editor.apply();


        Intent intent = new Intent(UjianDiskrit.this, MenuMahasiswa.class);
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
