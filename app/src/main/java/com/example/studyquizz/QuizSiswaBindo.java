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

public class QuizSiswaBindo extends AppCompatActivity {

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

            new Question("1. Bacalah paragraf berikut ini!\n\n“Di tengah hiruk-pikuk ibukota yang padat penduduk, banyak orang yang merindukan suasana pedesaan yang sejuk dan tenang. Mereka ingin melarikan diri dari kebisingan dan polusi udara yang kian tak terelakkan.”\n\nKalimat tersebut termasuk jenis teks...",
                    "A. Deskripsi",
                    "B. Eksposisi",
                    "C. Narasi",
                    "D. Argumentasi",
                    "A. Deskripsi",
                    0),

            new Question("2. Perhatikan kalimat berikut!\n\n“Dalam rapat tersebut, kami mencapai mufakat untuk melanjutkan program pemberdayaan masyarakat di daerah terpencil.”\n\nPada kalimat di atas, kata “mufakat” memiliki makna...",
                    "A. Kesepakatan",
                    "B. Pemahaman",
                    "C. Keinginan",
                    "D. Kekacauan",
                    "A. Kesepakatan",
                    0),

            new Question("3. Perhatikan paragraf berikut!\n\n“Sarana transportasi umum yang masih kurang memadai di kota ini menjadi permasalahan serius. Jumlah penumpang yang tinggi dan keterbatasan armada membuat banyak orang kesulitan dalam beraktivitas sehari-hari.”\n\nPernyataan tersebut termasuk jenis paragraf...",
                    "A. Eksposisi",
                    "B. Argumentasi",
                    "C. Narasi",
                    "D. Deskripsi",
                    "A. Eksposisi",
                    0),

            new Question("4. Bacalah kalimat berikut!\n\n“Tolong, jangan tinggalkan saya di sini sendirian!”\n\nKalimat tersebut termasuk jenis kalimat...",
                    "A. Imperatif",
                    "B. Interogatif",
                    "C. Deklaratif",
                    "D. Eksklamatif",
                    "D. Eksklamatif",
                    0),

            new Question("5. Bacalah cerpen pendek berikut!\n\n“Pada suatu hari yang cerah, Lisa pergi ke taman bersama teman-temannya. Mereka bermain bola dan makan bersama di bawah pohon rindang. Saat matahari mulai terbenam, mereka pulang ke rumah dengan senyum di wajah masing-masing.”\n\nTeks di atas termasuk jenis teks...",
                    "A. Narasi",
                    "B. Deskripsi",
                    "C. Eksposisi",
                    "D. Argumentasi",
                    "A. Narasi",
                    0),

    };
    private boolean quizbindo;

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
        quizbindo = sharedPreferences.getBoolean("quizbindo", false);

        if (quizbindo) {
            Intent intent = new Intent(QuizSiswaBindo.this, Menu.class);
            Toast.makeText(QuizSiswaBindo.this, "Anda Telah Melakukan Quiz", Toast.LENGTH_SHORT).show();
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
        editor.putInt("nilaiquizbindo", score);
        editor.putBoolean("quizbindo", true);
        editor.apply();



        Intent intent = new Intent(QuizSiswaBindo.this, LihatJawabanActivity.class);
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
