package com.example.easyresearch.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easyresearch.MainActivity;
import com.example.easyresearch.R;
import com.example.easyresearch.activity.AskDanielActivity;
import com.example.easyresearch.activity.QuestionActivity;
import com.example.easyresearch.adapter.HallSettingAdapter;
import com.example.easyresearch.constant.Constant;
import com.example.easyresearch.entity.HallSetting;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2018/6/23.
 */

public class SeminarHallFragment extends BaseFragment implements View.OnClickListener,HallSettingAdapter.ItemClickListener{
    private View view;
    private List<HallSetting> hallSettings=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view==null){
            view=inflater.inflate(R.layout.seminar_hall_layout,container,false);
        }
        intiHallSetting();
        RecyclerView recyclerView=(RecyclerView) view.findViewById(R.id.hall_lists);
        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),2);
        //layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        HallSettingAdapter adapter=new HallSettingAdapter(hallSettings);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        return view;
    }
    private void intiHallSetting(){
        hallSettings.clear();
        HallSetting hallSetting1=new HallSetting("问大牛",R.drawable.c3);
        hallSettings.add(hallSetting1);
        /*HallSetting hallSetting2=new HallSetting("数据小助手",R.drawable.c2);
        hallSettings.add(hallSetting2);*/
        HallSetting hallSetting3=new HallSetting("科研论坛",R.drawable.c3);
        hallSettings.add(hallSetting3);
        /*HallSetting hallSetting4=new HallSetting("科研资讯",R.drawable.c2);
        hallSettings.add(hallSetting4);*/
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
}
