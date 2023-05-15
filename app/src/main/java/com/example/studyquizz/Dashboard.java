package com.example.studyquizz;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Dashboard extends Fragment {
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_dashboard, container, false);

        imageView1 = (ImageView) view.findViewById(R.id.quiz);
        imageView2 = (ImageView) view.findViewById(R.id.ujian);
        imageView3 = (ImageView) view.findViewById(R.id.nilai);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Quiz.class);
                startActivity(intent);

            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Ujian.class);
                startActivity(intent);

            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Nilai.class);
                startActivity(intent);

            }
        });

        return view;
    }
}