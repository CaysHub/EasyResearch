package com.example.easyresearch.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.easyresearch.R;
import com.example.easyresearch.adapter.QuestionAdapter;
import com.example.easyresearch.constant.Constant;
import com.example.easyresearch.entity.Question;
import com.example.easyresearch.ui.HeadControlPanel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class QuestionActivity extends AppCompatActivity implements
        QuestionAdapter.QuestionItemClickListener,HeadControlPanel.HeadPanelCallback{
    private List<Question> questions=new ArrayList<Question>();
    private PtrFrameLayout ptrFrameLayout;
    private HeadControlPanel headControlPanel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        initQuestion();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.question_list);
        ptrFrameLayout=(PtrFrameLayout)findViewById(R.id.question_refresh);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getApplicationContext(),1);
        recyclerView.setLayoutManager(gridLayoutManager);
        QuestionAdapter adapter=new QuestionAdapter(questions);
        recyclerView.setAdapter(adapter);adapter.setItemClickListener(this);
        //这里是一个自定义的头部刷新布局,自带的也有一个布局   new PtrDefaultHandler();
        PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(this);
        ptrFrameLayout.addPtrUIHandler(header);
        //不仅仅是添加头布局,还需要设置到控件中 注:特别重要,不然没显示
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                //执行调用下拉刷新监听
                ptrFrameLayout.autoRefresh(true);
            }
        },200);
        //设置下拉刷新监听
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                // 默认实现，根据实际情况做改动
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                //在这里写自己下拉刷新数据的请求
                //需要结束刷新头
                refreshQuestion();
                ptrFrameLayout.refreshComplete();
            }
        });
    }
    //初始化界面问题
    private void initQuestion(){
        headControlPanel=(HeadControlPanel)findViewById(R.id.question_head_layout);
        headControlPanel.initHeadPanel();
        headControlPanel.setLeftImage(R.drawable.ic_back);headControlPanel.setMiddleTitle("");
        headControlPanel.setRightTitle("发布");headControlPanel.setHeadPanelCallback(this);
        questions.clear();
        for(int i=0;i<20;i++){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
            String time=sdf.format(new Date());
            long id=0;
            String title="你知道的风景？",name="cays",content="描述你知道的风景";
            long reward=20,anserNum=100,lookNum=256,zanNum=163;
            Question question=new Question(id,title,name,content,time,reward,anserNum,lookNum,zanNum);
            questions.add(question);
        }
    }
    //下拉刷新事件
    private void refreshQuestion(){
        Toast.makeText(getApplicationContext(),"下拉刷新",Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    //当问题点击时
    @Override
    public void questionItemClick(int position) {
        Question question=questions.get(position);
        String q= Constant.gson.toJson(question);
        Intent intent=new Intent(QuestionActivity.this,QuestionInfoActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("question",q);
        intent.putExtra("question",q);
        startActivity(intent);
    }
    //head点击事件
    @Override
    public void onHeadPanelClick(int itemId) {
        Toast.makeText(this,"fdsuhds",Toast.LENGTH_LONG).show();
        switch (itemId){
            case Constant.btn_left_title:finish();break;
            case Constant.btn_right_title:
                Intent intent=new Intent(this,PostQuestionActivity.class);
                startActivity(intent);break;
            default:break;
        }
    }
}
