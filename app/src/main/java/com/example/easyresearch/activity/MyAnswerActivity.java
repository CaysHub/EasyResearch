package com.example.easyresearch.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.easyresearch.R;

public class MyAnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_answer);
        if (getSupportActionBar()!=null){getSupportActionBar().hide();}
    }
}
