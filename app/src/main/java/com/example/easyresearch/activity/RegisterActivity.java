package com.example.easyresearch.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.easyresearch.R;
import com.example.easyresearch.Util.HttpUtil;
import com.example.easyresearch.constant.Constant;
import com.example.easyresearch.entity.Person;
import com.example.easyresearch.entity.ResponseInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText nameEdit,password1Edit,password2Edit;
    private Button submitBtn,cancelBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nameEdit=(EditText)findViewById(R.id.register_user_name);
        password1Edit=(EditText)findViewById(R.id.register_user_password1);
        password2Edit=(EditText)findViewById(R.id.register_user_password2);
        submitBtn=(Button)findViewById(R.id.register_submit);
        cancelBtn=(Button)findViewById(R.id.register_cancel);
        submitBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register_submit:
                String name=nameEdit.getText().toString();
                String password1=password1Edit.getText().toString();
                String password2=password2Edit.getText().toString();
                if(checkPerson(name,password1,password2)){
                    registerPerson(name,password1);
                }
                break;
            case R.id.register_cancel:
                finish();
                break;
            default:break;
        }
    }
    private void registerPerson(String name,String password){
        final String url= Constant.URL+"login/register";
        Person person=new Person();
        person.setName(name);person.setNick(name);person.setPassword(password);
        String p=Constant.gson.toJson(person);
        final MultipartBody.Builder builder=new MultipartBody.Builder();
        //设置类型
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("person",p);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtil.sendHttpRequest(url, builder, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        registerFailed("连接失败");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String s=response.body().string();
                        registerSuccess(s);
                    }
                });
            }
        });

    }
    private void registerFailed(final String info){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),"aaa"+info,Toast.LENGTH_LONG).show();
            }
        });
    }
    private void registerSuccess(final String info){
        final ResponseInfo info1=Constant.gson.fromJson(info,new TypeToken<ResponseInfo>(){}.getType());
        if(info1==null)return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "" + info1.getResponseInfo(), Toast.LENGTH_LONG).show();
            }
        });
        if(info1.getResponseCode()==200) {
            finish();
        }
    }
    private boolean checkPerson(String name,String password1,String password2){
        if(name==null||name.equals("")){
            Toast.makeText(this,"用户名为空",Toast.LENGTH_LONG).show();
            return false;
        }
        if(password1==null||password1.equals("")||password2==null||password2.equals("")){
            Toast.makeText(this,"密码为空",Toast.LENGTH_LONG).show();
            return false;
        }
        if (name.length()>20){
            Toast.makeText(this,"用户名长度必须不大于20字符",Toast.LENGTH_LONG).show();
            return false;
        }
        if(password1.length()<6||password1.length()>16){
            Toast.makeText(this,"密码长度必须不小于6字符且不大于16字符",Toast.LENGTH_LONG).show();
            return false;
        }
        if(!password1.equals(password2)){
            Toast.makeText(this,"两次密码不一致",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
