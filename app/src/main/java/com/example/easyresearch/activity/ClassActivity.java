package com.example.easyresearch.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.easyresearch.R;
import com.example.easyresearch.adapter.ClassInfoAdapter;
import com.example.easyresearch.constant.Constant;
import com.example.easyresearch.entity.ClassInfo;
import com.example.easyresearch.entity.ClassItems;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClassActivity extends AppCompatActivity implements ClassInfoAdapter.ClassInfoClickListener{
    private List<ClassInfo> classInfoLists=new ArrayList<ClassInfo>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        initClasses();
        RecyclerView recyclerView1=(RecyclerView)findViewById(R.id.class_info_list);
        GridLayoutManager layoutManager=new GridLayoutManager(ClassActivity.this,1);
        recyclerView1.setLayoutManager(layoutManager);
        ClassInfoAdapter infoAdapter=new ClassInfoAdapter(classInfoLists);
        recyclerView1.setAdapter(infoAdapter);
        infoAdapter.setmClassInfoListener(this);
    }
    private void initClasses(){
        classInfoLists.clear();
        for (int i=0;i<40;i++){
            String name="java",title="java精品课",teacher="王军";
            String instruction="info",time= "88分钟",type="vip";
            double price=999d;String date=String.format("yyMMddhhmmss",new Date());
            ClassInfo info=new ClassInfo(name,title,teacher,instruction,time,
                    type,price,date);
            classInfoLists.add(info);
        }
    }
    @Override
    public void onClassInfoClick(int position) {
        ClassInfo info=classInfoLists.get(position);
        Intent intent=new Intent(ClassActivity.this, ClassInfoActivity.class);
        intent.putExtra("classInfo", Constant.gson.toJson(info));
        startActivity(intent);
    }
}
