package com.example.studyquizz;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class Profile extends Fragment {

    long exittime = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ...

        // Mengatur listener untuk tombol back
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Kode yang akan dijalankan saat tombol back ditekan

                if ((System.currentTimeMillis() - exittime) > 2000) {
                    Toast.makeText(requireContext(), "Tekan lagi untuk keluar", Toast.LENGTH_SHORT).show();
                    exittime = System.currentTimeMillis();
                } else {
                    requireActivity().finishAffinity();
                }
            }
        });
    }
}