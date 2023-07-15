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

public class QuizSiswaEkonomi extends AppCompatActivity {

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

            new Question("1. Apa yang dimaksud dengan inflasi dalam ekonomi?",
                    "A. Penurunan harga barang dan jasa secara umum",
                    "B. Kenaikan nilai tukar mata uang",
                    "C. Kenaikan harga barang dan jasa secara umum",
                    "D. Penurunan tingkat pengangguran",
                    "C. Kenaikan harga barang dan jasa secara umum",
                    0),

            new Question("2. Apa yang dimaksud dengan pasar oligopoli dalam teori ekonomi?",
                    "A. Pasar yang dikuasai oleh satu produsen",
                    "B. Pasar yang dikuasai oleh beberapa produsen besar",
                    "C. Pasar yang dikuasai oleh banyak produsen kecil",
                    "D. Pasar yang tidak memiliki persaingan",
                    "B. Pasar yang dikuasai oleh beberapa produsen besar",
                    0),

            new Question("3. Apa yang dimaksud dengan GDP (Gross Domestic Product) dalam ekonomi?",
                    "A. Total nilai ekspor suatu negara",
                    "B. Total nilai impor suatu negara",
                    "C. Total nilai produksi barang dan jasa dalam suatu negara",
                    "D. Total pendapatan masyarakat dalam suatu negara",
                    "C. Total nilai produksi barang dan jasa dalam suatu negara",
                    0),

            new Question("4. Apa yang dimaksud dengan defisit anggaran dalam keuangan negara?",
                    "A. Kelebihan pendapatan pemerintah dari total pengeluaran",
                    "B. Kekurangan pendapatan pemerintah dari total pengeluaran",
                    "C. Kelebihan pengeluaran pemerintah dari total pendapatan",
                    "D. Kekurangan pengeluaran pemerintah dari total pendapatan",
                    "C. Kelebihan pengeluaran pemerintah dari total pendapatan",
                    0),

            new Question("5. Apa yang dimaksud dengan konsep penawaran dan permintaan dalam ekonomi?",
                    "A. Hubungan antara jumlah uang yang beredar dan tingkat inflasi",
                    "B. Hubungan antara harga barang dan jumlah uang yang beredar",
                    "C. Hubungan antara jumlah produksi dan tingkat konsumsi",
                    "D. Hubungan antara jumlah barang yang ditawarkan dan jumlah barang yang diminta",
                    "D. Hubungan antara jumlah barang yang ditawarkan dan jumlah barang yang diminta",
                    0),

    };
    private boolean quizekonomi;

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
        quizekonomi = sharedPreferences.getBoolean("quizekonomi", false);

        if (quizekonomi) {
            Intent intent = new Intent(QuizSiswaEkonomi.this, Menu.class);
            Toast.makeText(QuizSiswaEkonomi.this, "Anda Telah Melakukan Quiz", Toast.LENGTH_SHORT).show();
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
        editor.putInt("nilaiquizekonomi", score);
        editor.putBoolean("quizekonomi", true);
        editor.apply();

        Intent intent = new Intent(QuizSiswaEkonomi.this, LihatJawabanActivity.class);
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
