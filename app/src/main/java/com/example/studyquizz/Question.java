package com.example.studyquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Question {
    private String question;
    private String[] options;
    private String correctAnswer;

    public Question(String question, String option1, String option2, String option3, String correctAnswer) {
        this.question = question;
        this.options = new String[]{option1, option2, option3};
        this.correctAnswer = correctAnswer;
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

