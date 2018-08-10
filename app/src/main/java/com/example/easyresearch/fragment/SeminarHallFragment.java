package com.example.easyresearch.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.easyresearch.MainActivity;
import com.example.easyresearch.R;
import com.example.easyresearch.activity.AskDanielActivity;
import com.example.easyresearch.activity.IMActivity;
import com.example.easyresearch.activity.QuestionActivity;
import com.example.easyresearch.adapter.DanielItemAdapter;
import com.example.easyresearch.adapter.HallSettingAdapter;
import com.example.easyresearch.adapter.QuestionAdapter;
import com.example.easyresearch.constant.Constant;
import com.example.easyresearch.entity.HallSetting;
import com.example.easyresearch.entity.Question;
import com.example.easyresearch.entity.Teacher;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2018/6/23.
 */

public class SeminarHallFragment extends BaseFragment implements View.OnClickListener,HallSettingAdapter.ItemClickListener
        ,QuestionAdapter.QuestionItemClickListener,DanielItemAdapter.DanielItemClickListener{
    private View view;
    private RecyclerView danielRV,questionRV,hallSettingRv;
    private HallSettingAdapter hallSettingAdapter;
    private QuestionAdapter questionAdapter;
    private DanielItemAdapter danielItemAdapter;
    private List<HallSetting> hallSettings=new ArrayList<>();
    private List<Question> questionList=new ArrayList<Question> ();
    private List<Teacher> teacherList=new ArrayList<Teacher> ();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view==null){
            view=inflater.inflate(R.layout.seminar_hall_layout,container,false);
        }
        hallSettingRv=(RecyclerView) view.findViewById(R.id.hall_lists);
        danielRV=(RecyclerView)view.findViewById (R.id.hall_daniel_rv);
        questionRV=(RecyclerView)view.findViewById (R.id.hall_question_rv);
        intiHallSetting();initQuestion ();initTeacher ();
        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),2);
        hallSettingRv.setLayoutManager(layoutManager);
        hallSettingAdapter=new HallSettingAdapter(hallSettings);
        hallSettingRv.setAdapter(hallSettingAdapter);
        hallSettingAdapter.setOnItemClickListener(this);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager (getActivity ());
        linearLayoutManager.setOrientation (LinearLayout.VERTICAL);
        questionAdapter=new QuestionAdapter (questionList);
        questionRV.setLayoutManager (linearLayoutManager);
        questionRV.setAdapter (questionAdapter);
        questionAdapter.setItemClickListener (this);

        LinearLayoutManager linearLayoutManager2=new LinearLayoutManager (getActivity ());
        linearLayoutManager2.setOrientation (LinearLayout.VERTICAL);
        danielItemAdapter=new DanielItemAdapter (teacherList);
        danielRV.setLayoutManager (linearLayoutManager2);
        danielRV.setAdapter (danielItemAdapter);
        danielItemAdapter.setListener (this);
        return view;
    }
    private void intiHallSetting(){
        hallSettings.clear();
        HallSetting hallSetting1=new HallSetting("问大牛",R.drawable.c3);
        hallSettings.add(hallSetting1);
        HallSetting hallSetting3=new HallSetting("科研论坛",R.drawable.c3);
        hallSettings.add(hallSetting3);
    }
    private void initQuestion(){
        questionList.clear ();
        for(int i=0;i<5;i++){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
            String time=sdf.format(new Date ());
            long id=0;
            String title="你知道的风景？",name="cays",content="描述你知道的风景";
            long reward=20,anserNum=100,lookNum=256,zanNum=163;
            Question question=new Question(id,title,name,content,time,reward,anserNum,lookNum,zanNum);
            questionList.add(question);
        }
    }
    private void initTeacher(){
        teacherList.clear ();
        for(int i=0;i<5;i++){
            Teacher teacher=new Teacher ("cays",2889,9,"计算机","nuaa",1000,100,
                    "计算机系");
            teacherList.add (teacher);
        }

    }
    @Override
    public void onResume() {
        super.onResume();
        MainActivity.currTag= Constant.fragment_seminar_hall;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(int position) {
        switch (position){
            case 0:Intent intent1=new Intent(getActivity(), AskDanielActivity.class);
                startActivity(intent1);break;
            case 1: Intent intent=new Intent(getActivity(), QuestionActivity.class);
                startActivity(intent);break;
            default:break;
        }
    }

    @Override
    public void onDanielItemClick(View item, int position) {

    }

    @Override
    public void questionItemClick(int position) {

    }
}
