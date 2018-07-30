package com.example.easyresearch.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easyresearch.R;
import com.example.easyresearch.Util.HttpUtil;
import com.example.easyresearch.constant.Constant;
import com.example.easyresearch.entity.ResponseInfo;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.TooManyListenersException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.Response;

public class PostQuestionActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText titleText,contentText,payText;
    private Button submitBtn,cancelBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_question);
        if (getSupportActionBar()!=null){getSupportActionBar().hide();}
        titleText=(EditText) findViewById(R.id.post_question_title);
        contentText=(EditText) findViewById(R.id.post_question_content);
        payText=(EditText)findViewById(R.id.post_question_pay);
        ((ImageView)findViewById(R.id.post_question_back)).setOnClickListener(this);
        submitBtn=(Button)findViewById(R.id.post_submit_btn);submitBtn.setOnClickListener(this);
        cancelBtn=(Button)findViewById(R.id.post_cancel_btn);cancelBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.post_submit_btn:
                String title=titleText.getText().toString(),context=contentText.getText().toString();
                String pay=payText.getText().toString();
                if (checkQuestion(title,context,pay)){sendQuestion(Constant.person.getName(),Constant.person.getTime(),
                        title,context,pay);
                }
                else{
                    Toast.makeText(this,"未登录或者问题有误",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.post_cancel_btn:finish();break;
            case R.id.post_question_back:finish();break;
            default:break;
        }
    }
    //发送问题信息
    private void sendQuestion(String name,String time,String title,String content,String reward){
        String url=Constant.URL+"question/post";
        MultipartBody.Builder builder=new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("name",name).addFormDataPart("time",time).addFormDataPart("title",title)
                .addFormDataPart("content",content).addFormDataPart("reward",reward);
        HttpUtil.sendHttpRequest(url, builder, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String info=response.body().string();
                postQuestionSuccess(info);
            }
        });
    }
    //检测合法
    private boolean checkQuestion(String title,String context,String pay){
        try{
            if (Constant.person==null)return false;
            if (title.isEmpty()||context.isEmpty()||pay.isEmpty())return false;
            double p=Double.parseDouble(pay);
            if(p<0||p>Constant.person.getIntegral())return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //发送成功
    private void postQuestionSuccess(String info){
        ResponseInfo info1=Constant.gson.fromJson(info,new TypeToken<ResponseInfo>(){}.getType());
        Toast.makeText(this,info1.getResponseInfo(),Toast.LENGTH_LONG).show();
    }
    //发送失败
    private void postQuestionFailed(String info){

    }
}
