package com.example.easyresearch.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.easyresearch.R;

public class ProjectInfoActivity extends AppCompatActivity {
    private TextView projectTestText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_project_info);
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
        projectTestText=(TextView)findViewById (R.id.project_info_test);
        String cp=getIntent ().getStringExtra ("company_project");
        projectTestText.setText (cp);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId ()){
            case android.R.id.home:finish ();break;
            default:break;
        }
        return super.onOptionsItemSelected (item);
    }
}
