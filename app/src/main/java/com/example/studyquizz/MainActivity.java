package com.example.studyquizz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    long exittime = 0;
    Map<String, String> userMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userMap.put("admin", "1234");
        userMap.put("dafa", "1234");
        userMap.put("ridhuan", "1234");

        EditText usernamefl = findViewById(R.id.usernamefl);
        EditText passwordfl = findViewById(R.id.passwordfl);
        Button logbt = findViewById(R.id.logbt);

        logbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredUsername = usernamefl.getText().toString();
                String enteredPassword = passwordfl.getText().toString();

                if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Username atau Password belum diisi", Toast.LENGTH_SHORT).show();
                } else {
                    String capitalizedUsername = enteredUsername.substring(0, 1).toUpperCase() + enteredUsername.substring(1);
                    if (userMap.containsKey(enteredUsername) && userMap.get(enteredUsername).equals(enteredPassword)) {
                        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("quizCompleted");
                        editor.remove("quizpboCompleted");
                        editor.remove("quizmtkCompleted");
                        editor.remove("quizDiskritCompleted");
                        editor.remove("ujianmtkCompleted");
                        editor.remove("ujianpboCompleted");
                        editor.remove("ujianCompleted");
                        editor.remove("ujiandiskritCompleted");
                        editor.remove("userAnswers");
                        editor.remove("quizsejarahCompleted");
                        editor.remove("ujianindoCompleted");
                        editor.remove("questionAnswered");
                        editor.remove("quizagama");
                        editor.remove("quizabindo");
                        editor.remove("quizabing");
                        editor.remove("quizbiologi");
                        editor.remove("quizkimia");
                        editor.remove("quizmtk");
                        editor.remove("quizpjok");
                        editor.remove("quizsbk");
                        editor.remove("quizpkn");
                        editor.remove("quizekonomi");
                        editor.remove("ujianagama");
                        editor.remove("ujianbing");
                        editor.remove("ujianbiologi");
                        editor.remove("ujianekonomi");
                        editor.remove("ujianfisika");
                        editor.remove("ujianmtk");
                        editor.remove("ujianpjok");
                        editor.remove("ujianpkn");
                        editor.remove("ujiansbk");
                        editor.remove("ujiansejarah");
                        editor.remove("quizaorkom");
                        editor.remove("quizimk");
                        editor.remove("quizstatistika");
                        editor.remove("quizstrukdat");
                        editor.remove("ujianaorkom");
                        editor.remove("ujianimk");
                        editor.remove("ujianstatistika");
                        editor.remove("ujianstrukdat");
                        editor.apply();

                        Intent intent = new Intent(MainActivity.this, Menu.class);
                        if (enteredUsername.equals("ridhuan")) {
                            intent = new Intent(MainActivity.this, MenuMahasiswa.class);
                        } else {
                            intent = new Intent(MainActivity.this, Menu.class);
                        }
                        intent.putExtra("username", enteredUsername); // Mengirimkan username yang berhasil masuk ke Menu
                        startActivity(intent);

                        logbt.setBackgroundColor(Color.BLUE);
                        Toast.makeText(MainActivity.this, "Selamat Datang " + capitalizedUsername, Toast.LENGTH_SHORT).show();
                    } else {
                        logbt.setBackgroundColor(Color.RED);
                        Toast.makeText(MainActivity.this, "Username atau Password Salah", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("totalScore");
        editor.remove("nilai");
        editor.remove("totalScoresejarah");
        editor.remove("totalScoreindo");
        editor.remove("quizmtk");
        editor.remove("ujianmtk");
        editor.remove("quizdiskrit");
        editor.remove("ujiandiskrit");
        editor.remove("quizpbo");
        editor.remove("ujianpbo");
        editor.remove("nilaiquizagama");
        editor.remove("nilaiquizabindo");
        editor.remove("nilaiquizabing");
        editor.remove("nilaiquizbiologi");
        editor.remove("nilaiquizkimia");
        editor.remove("nilaiquizmtk");
        editor.remove("nilaiquizpjok");
        editor.remove("nilaiquizsbk");
        editor.remove("nilaiquizpkn");
        editor.remove("nilaiquizekonomi");
        editor.remove("nilaiujianagama");
        editor.remove("nilaiujianbing");
        editor.remove("nilaiujianbiologi");
        editor.remove("nilaiujianekonomi");
        editor.remove("nilaiujianfisika");
        editor.remove("nilaiujianmtk");
        editor.remove("nilaiujianpjok");
        editor.remove("nilaiujianpkn");
        editor.remove("nilaiujiansbk");
        editor.remove("nilaiujiansejarah");
        editor.remove("nilaiquizaorkom");
        editor.remove("nilaiquizimk");
        editor.remove("nilaiquizstatistika");
        editor.remove("nilaiquizstrukdat");
        editor.remove("nilaiujianaorkom");
        editor.remove("nilaiujianimk");
        editor.remove("nilaiujianstatistika");
        editor.remove("nilaiujianstrukdat");
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exittime) > 2000) {
            Toast.makeText(this, "Tekan lagi untuk keluar", Toast.LENGTH_SHORT).show();
            exittime = System.currentTimeMillis();
        } else {
            finishAffinity();
        }
    }
}
