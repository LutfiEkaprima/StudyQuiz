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

public class UjianSiswaEkonomi extends AppCompatActivity {

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

            new Question("1. Pada musim penghujan yang menimbulkan banjir, akan mendorong harga beras mengalami kenaikan , dan diikuti kenaikan harga barang kebutuhan pokok. Keadaan ini yang langsung terkena imbasnya adalah pengusaha makanan/restoran.\n\nDampak negatif dari kelangkaan barang kebutuhan pokok tersebut adalah ..... ",
                    "A. pengusaha menaikkan harga jual dari makanan tersebut",
                    "B. mengurangi porsi makanan yang dijual dengan harga tetap",
                    "C. beralih profesi menjadi pengusaha selain makanan/restoran ",
                    "D. omzet penjualan berkurang sehingga keuntungan berkurang",
                    "D. omzet penjualan berkurang sehingga keuntungan berkurang",
                    0),

            new Question("2.Pak Herman seorang pengusaha sebelum memulai proses produksi suatu barang, harus cermat menentukan target pasar yang akan dituju, seperti anak-anak, remaja, ataukah dewasa.\n\nMasalah pokok ekonomi modern yang terjawab dari pernyataan di atas adalah",
                    "A. barang apa yang akan diproduksi",
                    "B. bagaimana cara memproduksi",
                    "C. untuk siapa barang diproduksi",
                    "D. berapa banyak barang yang akan diproduksi",
                    "C. untuk siapa barang diproduksi",
                    0),

            new Question("3.Kegiatan yang dilakukan oleh masyarakat sebagai berikut:\n\n(1) Bu Susan setiap hari belanja untuk keperluan sehari-hari\n\n(2) Pak Santoso membeli pulsa untuk dijual lagi\n\n(3) Pak Jamal membayar sewa kios tempatnya usahanya\n\n(4) Pak Jamin membeli bensin motornya untuk pergi ke undangan\n\n(5) Pak Slamet membeli mie untuk bahan baku kios basonya\n\nDari pernyataan di atas yang termasuk perilaku produsen adalah .....",
                    "A. (1), (2), dan (3)",
                    "B. (1), (3), dan (5)",
                    "C. (2), (3), dan (5)",
                    "D. (3), (4), dan (5)",
                    "C. (2), (3), dan (5)",
                    0),

            new Question("4.Kelangkaan sumber daya alam contohnya minyak bumi bisa diatasi melalui ...",

                    "A. memperbanyak jumlah produksi minyak yang ada di seluruh dunia",
                    "B. mencari sumber daya atau energi alternatif",
                    "C. penjualan kendaraan bermotor dihentikan",
                    "D. menghambat laju pertumbuhan penduduk",
                    "B. mencari sumber daya atau energi alternatif",
                    0),

            new Question("5.Salah satu masalah pemerintah di bidang ekonomi adalah harga bahan makanan/pokok yang terus naik. Keadaan ini sangat dirasakan oleh masyarakat terutama yang berpenghasilan rendah.\n\nUntuk mengatasi masalah tersebut, upaya yang paling tepat dilakukan pemerintah adalah .....",

                     "A. Menstabilkan harga barang kebutuhan pokok dengan kebijakan harga dan produksi", "B. Mengkampanyekan program hemat belanja dan mencintai produk dalam negeri",
                     "C. Memberikan subsidi kepada produsen yang mampu memeroduksi kebutuhan masyarakat",
                     "D. Melalui BUMN turut berpartisipasi memproduksi kebutuhan pokok masyarakat",
                     "A. Menstabilkan harga barang kebutuhan pokok dengan kebijakan harga dan produksi",
                     0),

    };

    private boolean ujianekonomi;

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
        ujianekonomi = sharedPreferences.getBoolean("ujianekonomi", false);

        if (ujianekonomi) {
            Intent intent = new Intent(UjianSiswaEkonomi.this, Menu.class);
            Toast.makeText(UjianSiswaEkonomi.this, "Anda Telah Melakukan Ujian", Toast.LENGTH_SHORT).show();
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
        editor.putInt("nilaiujianekonomi", score);
        editor.putBoolean("ujianekonomi", true);
        editor.apply();


        Intent intent = new Intent(UjianSiswaEkonomi.this, Menu.class);
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
