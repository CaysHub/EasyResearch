package com.example.easyresearch.activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.easyresearch.R;

import io.rong.imkit.RongIM;

public class IMActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im);
        ((Button)findViewById (R.id.im_dialog_test)).setOnClickListener (this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId ()){
            case R.id.im_dialog_test:
                Log.d ("DialogActivity", "onClick: 1哈哈哈");
                RongIM.getInstance ().startPrivateChat (IMActivity.this,"b","b");
                Log.d ("DialogActivity", "onClick: 2哈哈哈");
                break;
            default:break;
        }
    }
}
