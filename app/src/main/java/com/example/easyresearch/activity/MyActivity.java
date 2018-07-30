package com.example.easyresearch.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.easyresearch.MySetting.MySetting;
import com.example.easyresearch.MySetting.MySettingAdapter;
import com.example.easyresearch.R;
import com.example.easyresearch.Util.HttpUtil;
import com.example.easyresearch.action.HeadImgAction;
import com.example.easyresearch.constant.Constant;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.Response;

public class MyActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
    private CircleImageView myImage;
    private TextView myName,myLevel,myCollection,myFollow;
    private List<MySetting> settingList=new ArrayList<>();
    private ListView listView;
    private ImageView myBack;
    private Bitmap head;// 头像Bitmap
    private static String path = "/sdcard/easyresearch/";// sd路径

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        initSettings();init();
        myImage.setOnClickListener(this);
        myName.setOnClickListener(this);
        myLevel.setOnClickListener(this);
        myFollow.setOnClickListener(this);
        myCollection.setOnClickListener(this);
        myBack.setOnClickListener(this);
    }
    private void init(){
        myImage=(CircleImageView)findViewById(R.id.my_image);
        myName=(TextView)findViewById(R.id.my_name);
        myLevel=(TextView)findViewById(R.id.my_level);
        myFollow=(TextView)findViewById(R.id.my_follow);
        myCollection=(TextView)findViewById(R.id.my_collection);
        myBack=(ImageView)findViewById(R.id.my_setting_back);
        MySettingAdapter adapter=new MySettingAdapter(MyActivity.this,R.layout.my_setting_items,settingList);
        //String []data={"a","b"};ArrayAdapter<String> adapter1=new ArrayAdapter<String>(MyActivity.this,R.layout.support_simple_spinner_dropdown_item,data);
        listView=(ListView)findViewById(R.id.my_setting_listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        Glide.with(getApplicationContext()).load(R.drawable.c3).centerCrop()
                .dontAnimate()//防止设置placeholder导致第一次不显示网络图片,只显示默认图片的问题
                .error(R.drawable.c3).placeholder(R.drawable.c3).into(myImage);
        //myImage.setImageResource(R.drawable.c3);
        if(Constant.person!=null) {
            myName.setText(Constant.person.getName());
        }else{
            myName.setText("未登陆");
        }
        myLevel.setText("青铜学者");myFollow.setText("123");myCollection.setText("9");
        initHeadImg();
    }
    //初始化头像
    private void initHeadImg(){
        final String url=Constant.URL+"picture/selectHead";
        final String name="cays",time="20180727";
        new Thread(new Runnable() {
            @Override
            public void run() {
                MultipartBody.Builder builder=new MultipartBody.Builder();
                builder.setType(MultipartBody.FORM);
                builder.addFormDataPart("name",name);
                builder.addFormDataPart("time",time);
                HttpUtil.sendHttpRequest(url, builder, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        byte []s=response.body().bytes();
                        getHeadImgSuccess(s);
                    }
                });
            }
        }).start();
    }
    //获取头像成功
    private void getHeadImgSuccess(final  byte []info){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //使用BitmapFactory工厂，把字节数组转化为bitmap
                //Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                //通过imageview，设置图片
                //myImage.setImageBitmap(bitmap);
                Glide.with(getApplicationContext()).load(info).centerCrop()
                        .dontAnimate()//防止设置placeholder导致第一次不显示网络图片,只显示默认图片的问题
                        .error(R.drawable.c3).placeholder(R.drawable.c3).into(myImage);
            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_image: showTypeDialog();break;
            case R.id.my_name:
                Intent intent=new Intent(MyActivity.this,LoginActivity.class);
                startActivity(intent);finish();break;
            case R.id.my_setting_back:finish(); break;
            default: Toast.makeText(this,"暂未开放",Toast.LENGTH_SHORT).show();break;
        }
    }
    //更换头像/**
    //如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
    private void showTypeDialog(){
        //显示对话框
        AlertDialog.Builder builder=new AlertDialog.Builder(MyActivity.this);
        final AlertDialog dialog=builder.create();
        View view=View.inflate(MyActivity.this,R.layout.dialog_select_photo,null);
        TextView tv_select_gallery=(TextView)view.findViewById(R.id.tv_select_gallery);
        TextView tc_select_camera=(TextView)view.findViewById(R.id.tv_select_camera);
        tv_select_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                //打开文件
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, 1);
                dialog.dismiss();
            }
        });
        tc_select_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "head.jpg")));
                startActivityForResult(intent2, 2);// 采用ForResult打开
                dialog.dismiss();
            }
        });
        dialog.setView(view);dialog.show();
    }
    //设置item点击时
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:Intent intent=new Intent(this,RealIdentityActivity.class);
                startActivity(intent);break;
            case 1:Intent intent1=new Intent(this,QuestionActivity.class);
                startActivity(intent1);break;
            case 2:Intent intent2=new Intent(this,MyAnswerActivity.class);
                startActivity(intent2);break;
            case 3:
                if(Constant.person!=null){
                    Constant.person=null;Intent intent3=new Intent(this,MyActivity.class);
                    startActivity(intent3);finish();
                }
                break;
            default:break;
        }
        //Toast.makeText(getApplicationContext(),""+settingList.get(position).getName(),Toast.LENGTH_SHORT).show();
    }
    //初始化setting设置
    private void initSettings(){
        settingList.clear();
        MySetting mySetting1=new MySetting(Constant.setting_identify);
        MySetting mySetting2=new MySetting(Constant.setting_question);
        MySetting mySetting3=new MySetting(Constant.setting_answer);
        MySetting mySetting4=new MySetting(Constant.setting_exit);
        settingList.add(mySetting1);settingList.add(mySetting2);
        settingList.add(mySetting3);settingList.add(mySetting4);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("按下了back键   onBackPressed()");
        Toast.makeText(getApplicationContext(),"按下了back键   onBackPressed()",Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    HeadImgAction.cropPhoto(MyActivity.this,data.getData());// 裁剪图片
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
                    HeadImgAction.cropPhoto(MyActivity.this,Uri.fromFile(temp));// 裁剪图片
                }
                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if (head != null) {
                        /**
                         * 上传服务器代码
                         */
                        HeadImgAction.setPicToView(path,head);// 保存在SD卡中
                        //myImage.setImageBitmap(head);// 用ImageButton显示出来
                        //Glide加载bitmap
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        head.compress(Bitmap.CompressFormat.PNG, 100, baos);
                        byte[] bytes=baos.toByteArray();
                        Glide.with(getApplicationContext()).load(bytes).centerCrop()
                                .dontAnimate()//防止设置placeholder导致第一次不显示网络图片,只显示默认图片的问题
                                .error(R.drawable.c3).placeholder(R.drawable.c3).into(myImage);
                    }
                }
                break;
            default: break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    //
}
