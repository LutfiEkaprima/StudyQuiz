package com.example.studyquizz;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class Ujian extends AppCompatActivity {

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
        setContentView(R.layout.activity_ujian);

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
                AlertDialog.Builder builder = new AlertDialog.Builder(Ujian.this);
                builder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda ingin memulai Ujian?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Ujian.this, UjianSiswaMtk.class);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Ujian.this);
                builder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda ingin memulai Ujian?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Ujian.this, UjianSiswaFisika.class);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Ujian.this);
                builder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda ingin memulai Ujian?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Ujian.this, QuizKimia.class);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Ujian.this);
                builder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda ingin memulai Ujian?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Ujian.this, UjianSiswaBiologi.class);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Ujian.this);
                builder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda ingin memulai Ujian?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Ujian.this, UjianSiswaEkonomi.class);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Ujian.this);
                builder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda ingin memulai Ujian?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Ujian.this, UjianSiswaSejarah.class);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Ujian.this);
                builder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda ingin memulai Ujian?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Ujian.this, UjianSiswaBing.class);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Ujian.this);
                builder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda ingin memulai Ujian?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Ujian.this, UjianBindo.class);
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

        buttonsbk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Ujian.this);
                builder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda ingin memulai Ujian?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Ujian.this, UjianSiswaSbk.class);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Ujian.this);
                builder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda ingin memulai Ujian?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Ujian.this, UjianSiswaAgama.class);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Ujian.this);
                builder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda ingin memulai Ujian?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Ujian.this, UjianSiswaPjok.class);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Ujian.this);
                builder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda ingin memulai Ujian?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Ujian.this, UjianSiswaPkn.class);
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