package com.example.easyresearch.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.easyresearch.R;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.NetUtils;

import java.util.Iterator;
import java.util.List;

public class IMActivity extends AppCompatActivity implements View.OnClickListener{
    private String username="1",pwd="1";
    private Button registerBtn,loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im);
        MultiDex.install(this);
        if (getSupportActionBar()!=null){getSupportActionBar().hide();}
        imOptions ();
        registerBtn=(Button)findViewById (R.id.im_register);
        loginBtn=(Button)findViewById (R.id.im_login);
        ((Button)findViewById (R.id.im_logout)).setOnClickListener (this);
        //registerIM(username,pwd);
    }
    //环信sdk初始化
    private void imOptions(){
        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        // 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
        options.setAutoTransferMessageAttachments(true);
        // 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
        options.setAutoDownloadThumbnail(true);
        //初始化
        Context appContext = this;
        int pid = android.os.Process.myPid();
        String processAppName = getAppName (pid);
        // 如果APP启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回
        /*if (processAppName == null ||!processAppName.equalsIgnoreCase(appContext.getPackageName())) {
            Log.e("IMActivity", "enter the service process!");
            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }*/
        EMClient.getInstance().init(getApplicationContext (), options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
        //注册一个监听连接状态的listener
        EMClient.getInstance().addConnectionListener(new MyConnectionListener());
    }
    //获取app名称
    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }
    //注册环信账号
    private void registerIM(String username,String pwd){
        //注册失败会抛出HyphenateException
        try {
            EMClient.getInstance ().createAccount (username, pwd);//同步方法
        }catch (HyphenateException e){
            Toast.makeText (this,"注册失败！",Toast.LENGTH_LONG).show ();
            return;
        }
        Toast.makeText (this,"注册成功！",Toast.LENGTH_LONG).show ();
    }
    //登录
    private void loginIM(String name,String password){
        EMClient.getInstance ().login (name, password, new EMCallBack () {
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                Toast.makeText (getApplicationContext (),"登录成功！",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(int i, String s) {
                Toast.makeText (getApplicationContext (),"登录失败",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onProgress(int i, String s) {
                Toast.makeText (getApplicationContext (),"登录失败",Toast.LENGTH_LONG).show();
            }
        });
    }
    //退出环信
    private void logoutIM(String username,String pwd){
        EMClient.getInstance().logout(true, new EMCallBack() {
            @Override
            public void onSuccess() {
                Toast.makeText (getApplicationContext (),"退出登录成功",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onProgress(int progress, String status) {
                Toast.makeText (getApplicationContext (),"退出登录中",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onError(int code, String message) {
                Toast.makeText (getApplicationContext (),"退出登录失败",Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onClick(View view) {

        switch (view.getId ()){
            case R.id.im_register:registerIM (username,pwd);break;
            case R.id.im_login:loginIM (username,pwd);break;
            case R.id.im_logout:logoutIM(username,pwd);break;
            default:break;
        }
    }
    //实现ConnectionListener接口
    private class MyConnectionListener implements EMConnectionListener {
        @Override
        public void onConnected() {
        }
        @Override
        public void onDisconnected(final int error) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if(error == EMError.USER_REMOVED){
                        // 显示帐号已经被移除
                    }else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                        // 显示帐号在其他设备登录
                    } else {
                        if (NetUtils.hasNetwork(IMActivity.this)) {
                            //连接不到聊天服务器
                        } else{
                            //当前网络不可用，请检查网络设置
                        }

                    }
                }
            });
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext (newBase);
        MultiDex.install(this);
    }
}
