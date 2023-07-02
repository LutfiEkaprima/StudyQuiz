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

public class UjianBindo extends AppCompatActivity {

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
            new Question("1. Berikut ini yang tidak termasuk unsur-unsur dalam surat lamaran pekerjaan adalah ….",
                    "A. tanggal surat ",
                    "B. alamat surat",
                    "C. ucapan terimakasih",
                    "D. isi surat",
                    "C. ucapan terimakasih",
                    0),

            new Question("2. Langkah yang tepat untuk meyakinkan pendengar atas pendapat seseorang pembicara adalah ….",
                    "A. Tujuan jelas, mengemukakan fakta, tidak berbelit-belit (satu tema) ",
                    "B. Tujuan jelas, menggunakan ilustrasi, wajah serius.",
                    "C. Tujuan jelas, berwajah serius, berterus terang.",
                    "D. Tujuan jelas, memakai kata-kata ilmiah, menggunakan teks.",
                    "A. Tujuan jelas, mengemukakan fakta, tidak berbelit-belit (satu tema) ",
                    0),

            new Question("3. Karena pengaruh globalisasi dan tuntutan kehidupan, masyarakat akan mengalami pergeseran apresiasi dan cara pandang terhadap berbagai aspek kehidup. Berbagai aspek kehidupan itu sendiri juga mengalami perubahan-perubahan yang berarti. Untuk menjelaskan bagaimana pendidikan masa depan harus dilaksanakan, berbagai tuntutan kebutuhan dan fenomena yang terjadi dalam masyarakat perlu dicermati secara seksama. Berbagai tuntutan itulah nantinya akan memaksa dunia pendidikan untuk berbenah diri. Dunia kerja, misalnya, dapat dipastikan akan menuju kepada spesialisasi dan profesionalisasi.\n\n\t\t\t\tSementara itu, ada geseran apresiasi masarakat terhadap nilai-nilai kehidupan sehingga masyarakat menjadi longgar terhadap berperilaku yang kurang lazim dalam budaya Indonesia. Misalnya, mencium pipi antara dua insan yang berlainan jenis di remaja. Itu semua menjurus kepada penyimpangan norma agama. \n\nKutipan di atas menyatakan ….",
                    "A. fakta ",
                    "B. pendapat",
                    "C. analisi",
                    "D. deskripsi",
                    "A. fakta ",
                    0),

            new Question("4. Bahasa laporan hendaknya tidak memenuhi kriteria berikut ….",
                    "A. logis ",
                    "B. baku",
                    "C. jelas",
                    "D. rancu",
                    "D. rancu",
                    0),

            new Question("5. Bacalah teks berikut dengan seksama!\n\nSemburan Baru Muncul di Mindi Semburan lumpur, air, dan gas baru keluar dari halaman belakang rumah salah seorang penduduk warga Desa Mendi, Kecamatan Porong, Kabupaten Sidoarjo. Semburan ini merupakan semburan ke-59 yang muncul di sekitaran pusat semburan utama. Menurut seorang ahli dari Leader Team Frgaco, perusahaan yang mengawasi gas-gas berbahaya disekitar pusat semburan, semburan sama dengan 58 semburan liar sebelumnya. Semburan liar itu juga tidak berbahaya dan akan membesar. Kalau dibiarkan semburan itu akan mengecil sendiri. Untuk menutup semburan, hari ini akan dimasukkan 100 kilogram semen ke dalam lubang asal semburan.",
                    "A. Mengecilnya semburan liar ",
                    "B. Pendapat tentang semburan liar",
                    "C. Munculnya semburan liar",
                    "D. Penutupan lubang semburan",
                    "B. Pendapat tentang semburan liar",
                    0),

            };

    private boolean ujianindoCompleted;

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
        ujianindoCompleted = sharedPreferences.getBoolean("ujianindoCompleted", false);

        if (ujianindoCompleted) {
            Intent intent = new Intent(UjianBindo.this, Menu.class);
            Toast.makeText(UjianBindo.this, "Anda Telah Melakukan Ujian", Toast.LENGTH_SHORT).show();
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
        editor.putInt("totalScoreindo", score);
        editor.putBoolean("ujianindoCompleted", true);
        editor.apply();


        Intent intent = new Intent(UjianBindo.this, Menu.class);
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
