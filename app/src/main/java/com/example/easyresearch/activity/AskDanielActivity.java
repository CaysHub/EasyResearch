package com.example.easyresearch.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.easyresearch.R;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class AskDanielActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_daniel);
        if(getSupportActionBar()!=null){getSupportActionBar().hide();}
        Log.d ("MainActivity", "onCreate: 1哈哈哈");
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {
            Log.d ("MainActivity", "onCreate: 2哈哈哈");
            RongIM.init (this);
            Log.d ("MainActivity", "onCreate: 3哈哈哈");
        }
        ((Button)findViewById (R.id.ask_im_register)).setOnClickListener (this);
        ((Button)findViewById (R.id.ask_im_login)).setOnClickListener (this);
        ((Button)findViewById (R.id.ask_im_logout)).setOnClickListener (this);
        test=(TextView)findViewById (R.id.ask_im_test);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    private void connect(String token) {
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {
            RongIM.connect(token, new RongIMClient.ConnectCallback() {
                /**
                 * Token 错误。可以从下面两点检查
                 * 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 * 2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {
                }
                @Override
                public void onSuccess(String userid) {
                    Log.d("MainActivity", "连接融云成功--onSuccess" + userid);
                    startActivity(new Intent (AskDanielActivity.this, IMActivity.class));
                    finish();
                }
                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Log.d("MainActivity", "连接融云失败");
                }
            });
        }
    }
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId ()){
            case R.id.ask_im_register:break;
            case R.id.ask_im_login:
                connect ("9eavEC0BReCnzKSd+h4kvbU/4i10cXUVF9dPLD20lrzOUQ7uBY1v0L+2tklQJoZyIPvLYZsp1aiBj0G3a2gvJQ==");
                break;
            case R.id.ask_im_logout:break;
            default:break;
        }
    }
}
