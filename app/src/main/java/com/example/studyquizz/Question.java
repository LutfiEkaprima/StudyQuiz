package com.example.studyquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Question {
    private String question;
    private String[] options;
    private String correctAnswer;
    private int imageResource;

    public Question(String question, String option1, String option2, String option3, String option4, String correctAnswer, int imageResource) {
        this.question = question;
        this.options = new String[]{option1, option2, option3, option4};
        this.correctAnswer = correctAnswer;
        this.imageResource = imageResource;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}



