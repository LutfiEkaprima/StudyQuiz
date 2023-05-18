package com.example.studyquizz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String username = "admin";
    String password = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText usernamefl = findViewById(R.id.usernamefl);
        EditText passwordfl = findViewById(R.id.passwordfl);
        Button logbt = findViewById(R.id.logbt);

        logbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (usernamefl.getText().toString().equalsIgnoreCase(username)&& passwordfl.getText().toString().equalsIgnoreCase(password)){
                    startActivity(new Intent(MainActivity.this, Menu.class));
                    logbt.setBackgroundColor(Color.BLUE);
                    Toast.makeText(MainActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                }else{
                    logbt.setBackgroundColor(Color.RED);
                    Toast.makeText(MainActivity.this, "Username atau Password Salah", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}