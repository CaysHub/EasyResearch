package com.example.easyresearch.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.easyresearch.R;
import com.example.easyresearch.Util.HttpUtil;
import com.example.easyresearch.action.LoginAction;
import com.example.easyresearch.action.LoginManager;
import com.example.easyresearch.constant.Constant;
import com.example.easyresearch.entity.Person;
import com.example.easyresearch.entity.ResponseInfo;
import com.example.easyresearch.entity.UserInfo;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText nameEditView,passwordEditView;
    private Button loginBtn,registerBtn;
    private CheckBox autoLoginCheck;
    private ImageView loginBack;
    private ProgressDialog dialog;
    private LoginManager loginManager=LoginManager.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        nameEditView=(EditText)findViewById(R.id.login_user_name);
        passwordEditView=(EditText)findViewById(R.id.login_user_password);
        loginBtn=(Button)findViewById(R.id.login_login_btn);
        registerBtn=(Button)findViewById(R.id.login_register_btn);
        autoLoginCheck=(CheckBox)findViewById(R.id.login_check_autologin);
        loginBack=(ImageView)findViewById(R.id.login_back);

        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        loginBack.setOnClickListener(this);
        initUserInfo();
    }
    private void initUserInfo(){
        UserInfo userInfo=loginManager.getUserInfo(getApplicationContext());
        if(userInfo==null)return;
        if(userInfo.isAutoLogin()) {
            nameEditView.setText(userInfo.getName());
            passwordEditView.setText(userInfo.getPassword());
            autoLoginCheck.setChecked(true);
            //login(userInfo.getName(),userInfo.getPassword());
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_back:
                finish();
                break;
            case R.id.login_register_btn:
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login_login_btn:
                String name=nameEditView.getText().toString();
                String password=passwordEditView.getText().toString();
                if(LoginAction.checkLogin(name,password)){
                    if (autoLoginCheck.isChecked()){
                        loginManager.saveUserInfo(getApplicationContext(),name,password,true);
                    }else{
                        loginManager.saveUserInfo(getApplicationContext(),name,"",false);
                    }
                    login(name,password);
                }
                break;
            default:
                break;
        }
    }/**
     * 监听Back键按下事件,方法1:
     * 注意:
     * super.onBackPressed()会自动调用finish()方法,关闭
     * 当前Activity.
     * 若要屏蔽Back键盘,注释该行代码即可
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LoginActivity.this, MyActivity.class);
        startActivity(intent);
        super.onBackPressed();
        Toast.makeText(getApplicationContext(),"按下了back键   onBackPressed()",Toast.LENGTH_LONG).show();
    }
    /**
     * 监听Back键按下事件,方法2:
     * 注意:
     * 返回值表示:是否能完全处理该事件
     * 在此处返回false,所以会继续传播该事件.
     * 在具体项目中此处的返回值视情况而定.
     */
    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            System.out.println("按下了back键   onKeyDown()");
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }*/

    private void login(String name,String password){
        final String url= Constant.URL+"login/login";
        Person person=new Person();
        person.setName(name);person.setNick(name);
        person.setPassword(password);
        String p=Constant.gson.toJson(person);
        final MultipartBody.Builder builder=new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("person",p);
        dialog=getProgressDialog("Login...");
        dialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtil.sendHttpRequest(url, builder, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        loginFailed("连接失败");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String s=response.body().string(),info="";
                        if (s.contains("~")) {
                            info=s.split("~")[0];
                            Constant.person=Constant.gson.fromJson(s.split("~")[1],new TypeToken<Person>(){}.getType());
                        }else{
                            info=s;
                        }
                        loginSuccess(info);
                    }
                });
            }
        });

    }
    private void loginSuccess(final String info){
        if (dialog.isShowing()){
            dialog.cancel();
        }
        final ResponseInfo info1=Constant.gson.fromJson(info,new TypeToken<ResponseInfo>(){}.getType());
        if (info1==null)return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),info1.getResponseInfo(),Toast.LENGTH_LONG).show();
            }
        });
        if(info1.getResponseCode()==200){
            Intent intent=new Intent(LoginActivity.this,MyActivity.class);
            startActivity(intent);
            finish();
        }
    }
    private void loginFailed(final String info){
        if (dialog.isShowing()){
            dialog.cancel();
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),""+info,Toast.LENGTH_LONG).show();
            }
        });
    }
    public ProgressDialog getProgressDialog(String msg) {

        ProgressDialog progressDialog = new ProgressDialog(this);

        progressDialog.setIndeterminate(true);

        progressDialog.setMessage(msg);

        progressDialog.setCancelable(true);

        return progressDialog;

    }
}
