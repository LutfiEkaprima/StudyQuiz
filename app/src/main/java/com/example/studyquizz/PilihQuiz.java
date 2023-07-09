package com.example.studyquizz;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class PilihQuiz extends AppCompatActivity {

    Button buttonmtk;
    Button buttonfsk;
    Button buttonbio;
    Button buttoneko;
    Button buttonsjr;
    Button buttoning;
    Button buttonind;
    Button buttonsbk;
    Button buttonkimia;
    Button buttonpai;
    Button buttonpjok;
    Button buttonpkn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_quiz);

        buttonmtk = (Button) findViewById(R.id.mtk);
        buttonfsk = (Button) findViewById(R.id.fisika);
        buttonbio = (Button) findViewById(R.id.biologi);
        buttoneko = (Button) findViewById(R.id.ekonomi);
        buttonsjr = (Button) findViewById(R.id.sejarah);
        buttoning = (Button) findViewById(R.id.bhsing);
        buttonind = (Button) findViewById(R.id.bhsindo);
        buttonsbk = (Button) findViewById(R.id.sbk);
        buttonkimia = (Button) findViewById(R.id.kimia);
        buttonpai = (Button) findViewById(R.id.pai);
        buttonpjok = (Button) findViewById(R.id.pjok);
        buttonpkn = (Button) findViewById(R.id.pkn);


        buttonmtk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PilihQuiz.this);
                builder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda ingin memulai Quiz?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(PilihQuiz.this, QuizSiswaMTK.class);
                                startActivity(intent);
                                buttonmtk.setBackgroundColor(Color.BLUE);
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .create()
                        .show();
            }
        });

        buttonfsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PilihQuiz.this);
                builder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda ingin memulai Quiz?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(PilihQuiz.this, Quiz.class);
                                startActivity(intent);
                                buttonfsk.setBackgroundColor(Color.BLUE);
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .create()
                        .show();
            }
        });


        buttonkimia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PilihQuiz.this);
                builder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda ingin memulai Quiz?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(PilihQuiz.this, QuizSiswaKimia.class);
                                startActivity(intent);
                                buttonkimia.setBackgroundColor(Color.BLUE);
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .create()
                        .show();
            }
        });

        buttonbio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PilihQuiz.this);
                builder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda ingin memulai Quiz?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(PilihQuiz.this, QuizSiswaBiologi.class);
                                startActivity(intent);
                                buttonbio.setBackgroundColor(Color.BLUE);
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .create()
                        .show();
            }
        });

        buttoneko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PilihQuiz.this);
                builder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda ingin memulai Quiz?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(PilihQuiz.this, QuizSiswaEkonomi.class);
                                startActivity(intent);
                                buttoneko.setBackgroundColor(Color.BLUE);
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .create()
                        .show();
            }
        });

        buttonsjr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PilihQuiz.this);
                builder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda ingin memulai Quiz?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(PilihQuiz.this, QuizSejarah.class);
                                startActivity(intent);
                                buttonsjr.setBackgroundColor(Color.BLUE);
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .create()
                        .show();
            }
        });

        buttoning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PilihQuiz.this);
                builder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda ingin memulai Quiz?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(PilihQuiz.this, QuizSiswaBing.class);
                                startActivity(intent);
                                buttoning.setBackgroundColor(Color.BLUE);
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .create()
                        .show();
            }
        });

        buttonind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PilihQuiz.this);
                builder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda ingin memulai Quiz?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(PilihQuiz.this, QuizSiswaBindo.class);
                                startActivity(intent);
                                buttonind.setBackgroundColor(Color.BLUE);
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .create()
                        .show();
            }
        });

        buttonsbk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PilihQuiz.this);
                builder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda ingin memulai Quiz?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(PilihQuiz.this, QuizSiswaSbk.class);
                                startActivity(intent);
                                buttonsbk.setBackgroundColor(Color.BLUE);
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .create()
                        .show();
            }
        });

        buttonpai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PilihQuiz.this);
                builder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda ingin memulai Quiz?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(PilihQuiz.this, QuizSiswaAgama.class);
                                startActivity(intent);
                                buttonpai.setBackgroundColor(Color.BLUE);
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .create()
                        .show();
            }
        });

        buttonpjok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PilihQuiz.this);
                builder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda ingin memulai Quiz?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(PilihQuiz.this, QuizSiswaPjok.class);
                                startActivity(intent);
                                buttonpjok.setBackgroundColor(Color.BLUE);
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .create()
                        .show();
            }
        });

        buttonpkn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PilihQuiz.this);
                builder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda ingin memulai Quiz?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(PilihQuiz.this, QuizSiswaPkn.class);
                                startActivity(intent);
                                buttonpkn.setBackgroundColor(Color.BLUE);
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .create()
                        .show();
            }
        });

    }
}