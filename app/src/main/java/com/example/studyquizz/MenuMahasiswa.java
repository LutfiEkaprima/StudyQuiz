package com.example.studyquizz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MenuMahasiswa extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    private BottomNavigationView bottomNavigationView;
    private DashboardMahasiswa dashboard = new DashboardMahasiswa();
    private Jadwal_Mahasiswa jadwal = new Jadwal_Mahasiswa();
    private profile_mahasiswa profile = new profile_mahasiswa();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_mahasiswa);

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnItemSelectedListener(this);


        getSupportFragmentManager().beginTransaction().replace(R.id.frlayout, dashboard).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.dashboard:
                getSupportFragmentManager().beginTransaction().replace(R.id.frlayout, dashboard).commit();
                return true;
            case R.id.jadwal:
                getSupportFragmentManager().beginTransaction().replace(R.id.frlayout, jadwal).commit();
                return true;
            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.frlayout, profile).commit();
                return true;
        }
        return false;
    }

}