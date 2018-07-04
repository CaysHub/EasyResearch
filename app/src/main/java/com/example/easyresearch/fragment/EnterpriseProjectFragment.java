package com.example.easyresearch.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easyresearch.MainActivity;
import com.example.easyresearch.R;
import com.example.easyresearch.constant.Constant;

/**
 * Created by lenovo on 2018/6/23.
 */

public class EnterpriseProjectFragment extends BaseFragment{
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view==null){
            view=inflater.inflate(R.layout.enterprise_project_layout,container,false);
        }
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.currTag= Constant.fragment_enterprise_project;
    }
}
