package com.example.easyresearch.activity;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.easyresearch.MySetting.MySetting;
import com.example.easyresearch.MySetting.MySettingAdapter;
import com.example.easyresearch.R;
import com.example.easyresearch.constant.Constant;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
    private CircleImageView myImage;
    private TextView myName,myLevel,myCollection,myFollow;
    private List<MySetting> settingList=new ArrayList<>();
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        initSettings();
        init();
        myImage.setOnClickListener(this);
        myName.setOnClickListener(this);
        myLevel.setOnClickListener(this);
        myFollow.setOnClickListener(this);
        myCollection.setOnClickListener(this);
    }
    private void init(){
        myImage=(CircleImageView)findViewById(R.id.my_image);
        myName=(TextView)findViewById(R.id.my_name);
        myLevel=(TextView)findViewById(R.id.my_level);
        myFollow=(TextView)findViewById(R.id.my_follow);
        myCollection=(TextView)findViewById(R.id.my_collection);
        MySettingAdapter adapter=new MySettingAdapter(MyActivity.this,R.layout.my_setting_items,settingList);
        //String []data={"a","b"};ArrayAdapter<String> adapter1=new ArrayAdapter<String>(MyActivity.this,R.layout.support_simple_spinner_dropdown_item,data);
        listView=(ListView)findViewById(R.id.my_setting_listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        Glide.with(getApplicationContext())
                .load(R.drawable.c3)
                .centerCrop()
                .dontAnimate()//防止设置placeholder导致第一次不显示网络图片,只显示默认图片的问题
                .error(R.drawable.c3)
                .placeholder(R.drawable.c3)
                .into(myImage);
        //myImage.setImageResource(R.drawable.c3);
        myName.setText("未登陆");
        myLevel.setText("青铜学者");
        myFollow.setText("123");
        myCollection.setText("9");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            default:
                Toast.makeText(this,"暂未开放",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),""+settingList.get(position).getName(),Toast.LENGTH_SHORT).show();
    }
    private void initSettings(){
        settingList.clear();
        MySetting mySetting1=new MySetting(Constant.setting_identify);
        MySetting mySetting2=new MySetting(Constant.setting_question);
        MySetting mySetting3=new MySetting(Constant.setting_answer);
        settingList.add(mySetting1);settingList.add(mySetting2);
        settingList.add(mySetting3);
    }
}
