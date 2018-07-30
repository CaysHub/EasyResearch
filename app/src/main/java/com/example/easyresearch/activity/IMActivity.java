package com.example.easyresearch.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.easyresearch.R;

public class IMActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im);
        if (getSupportActionBar()!=null){getSupportActionBar().hide();}
    }
}
