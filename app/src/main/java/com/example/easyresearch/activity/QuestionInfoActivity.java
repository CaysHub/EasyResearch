package com.example.easyresearch.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.easyresearch.R;

public class QuestionInfoActivity extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_info);
        textView=(TextView)findViewById(R.id.question_info_text);
        Intent intent=getIntent();
        String s=intent.getStringExtra("question");
        textView.setText(s);
    }
}
