package com.example.studyquizz;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class PilihUjianMahasiswa extends AppCompatActivity {

    Button buttonmtk2;
    Button buttonmtkdiskrit;
    Button buttonstatistika;
    Button buttonaorkom;
    Button buttonimk;
    Button buttonstrukdat;
    Button buttonpbo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_ujian_mahasiswa);

        buttonmtk2 = (Button) findViewById(R.id.mtk2);
        buttonmtkdiskrit = (Button) findViewById(R.id.mtkdiskrit);
        buttonstatistika = (Button) findViewById(R.id.stk2);
        buttonaorkom = (Button) findViewById(R.id.aorkom);
        buttonimk = (Button) findViewById(R.id.imk);
        buttonstrukdat = (Button) findViewById(R.id.strukdat);
        buttonpbo = (Button) findViewById(R.id.pbo);

        buttonmtk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PilihUjianMahasiswa.this);
                builder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda ingin memulai Ujian?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(PilihUjianMahasiswa.this, UjianMTK.class);
                                startActivity(intent);
                                buttonmtk2.setBackgroundColor(Color.BLUE);
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

        buttonmtkdiskrit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PilihUjianMahasiswa.this);
                builder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda ingin memulai Ujian?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(PilihUjianMahasiswa.this, UjianDiskrit.class);
                                startActivity(intent);
                                buttonmtkdiskrit.setBackgroundColor(Color.BLUE);
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


        buttonstatistika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PilihUjianMahasiswa.this);
                builder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda ingin memulai Ujian?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(PilihUjianMahasiswa.this, UjianMahasiswaStatistika.class);
                                startActivity(intent);
                                buttonstatistika.setBackgroundColor(Color.BLUE);
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

        buttonaorkom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PilihUjianMahasiswa.this);
                builder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda ingin memulai Ujian?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(PilihUjianMahasiswa.this, UjianMahasiswaAorkom.class);
                                startActivity(intent);
                                buttonaorkom.setBackgroundColor(Color.BLUE);
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

        buttonimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PilihUjianMahasiswa.this);
                builder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda ingin memulai Ujian?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(PilihUjianMahasiswa.this, UjianMahasiswaImk.class);
                                startActivity(intent);
                                buttonimk.setBackgroundColor(Color.BLUE);
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

        buttonstrukdat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PilihUjianMahasiswa.this);
                builder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda ingin memulai Ujian?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(PilihUjianMahasiswa.this, UjianMahasiswaStrukdat.class);
                                startActivity(intent);
                                buttonstrukdat.setBackgroundColor(Color.BLUE);
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

        buttonpbo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PilihUjianMahasiswa.this);
                builder.setTitle("Konfirmasi")
                        .setMessage("Apakah Anda ingin memulai Ujian?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(PilihUjianMahasiswa.this, UjianPBO.class);
                                startActivity(intent);
                                buttonpbo.setBackgroundColor(Color.BLUE);
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