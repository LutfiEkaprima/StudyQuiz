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

    long exittime = 0;
    private BottomNavigationView bottomNavigationView;
    private Dashboard dashboard = new Dashboard();
    private Jadwal jadwal = new Jadwal();
    private Profile profile = new Profile();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.dashboard);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
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