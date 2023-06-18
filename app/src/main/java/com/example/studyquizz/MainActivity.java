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

        EditText usernamefl = findViewById(R.id.usernamefl);
        EditText passwordfl = findViewById(R.id.passwordfl);
        Button logbt = findViewById(R.id.logbt);

        logbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredUsername = usernamefl.getText().toString();
                String enteredPassword = passwordfl.getText().toString();
                String capitalizedUsername = enteredUsername.substring(0, 1).toUpperCase() + enteredUsername.substring(1);

                if (userMap.containsKey(enteredUsername) && userMap.get(enteredUsername).equals(enteredPassword)) {
                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("quizCompleted");
                    editor.remove("ujianCompleted");
                    editor.remove("userAnswers");
                    editor.remove("quizsejarahCompleted");
                    editor.remove("ujianindoCompleted");
                    editor.remove("questionAnswered");
                    editor.apply();

                    startActivity(new Intent(MainActivity.this, Menu.class));
                    logbt.setBackgroundColor(Color.BLUE);
                    Toast.makeText(MainActivity.this, "Selamat Datang " + capitalizedUsername, Toast.LENGTH_SHORT).show();
                } else {
                    logbt.setBackgroundColor(Color.RED);
                    Toast.makeText(MainActivity.this, "Username atau Password Salah", Toast.LENGTH_SHORT).show();
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