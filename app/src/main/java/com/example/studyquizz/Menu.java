package com.example.studyquizz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Menu extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    private BottomNavigationView bottomNavigationView;
    private Dashboard dashboard = new Dashboard();
    private Jadwal jadwal = new Jadwal();
    private Profile profile = new Profile();

    private profile_mahasiswa profilemahasiswa = new profile_mahasiswa();

    private String loggedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnItemSelectedListener(this);

        // Set Dashboard sebagai halaman pertama yang terbuka
        getSupportFragmentManager().beginTransaction().replace(R.id.frlayout, dashboard).commit();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        loggedInUser = getIntent().getStringExtra("username");

        switch (item.getItemId()){
            case R.id.dashboard:
                getSupportFragmentManager().beginTransaction().replace(R.id.frlayout, dashboard).commit();
                return true;
            case R.id.jadwal:
                getSupportFragmentManager().beginTransaction().replace(R.id.frlayout, jadwal).commit();
                return true;
            case R.id.profile:
                if (loggedInUser.equals("ridhuan")) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frlayout, profilemahasiswa).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frlayout, profile).commit();
                }
                return true;
        }
        return false;
    }
}