package com.example.easyresearch.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easyresearch.MainActivity;
import com.example.easyresearch.MySetting.MySetting;
import com.example.easyresearch.R;
import com.example.easyresearch.adapter.ClassItemsAdapter;
import com.example.easyresearch.constant.Constant;
import com.example.easyresearch.entity.ClassItems;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2018/6/23.
 */

public class ClassesFragment extends BaseFragment{
    private View view;
    private List<ClassItems> classesLists=new ArrayList<>();
    private ClassItemsAdapter classItemsAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view==null){
            view=inflater.inflate(R.layout.classes_layout,container,false);
        }
        initClasses();
        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),4);
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.classes_class_list1);
        recyclerView.setLayoutManager(layoutManager);
        classItemsAdapter=new ClassItemsAdapter(classesLists);
        recyclerView.setAdapter(classItemsAdapter);
        return view;
    }
    private void initClasses(){
        classesLists.clear();
        ClassItems []classes={new ClassItems("科研工具",R.drawable.c3),new ClassItems("论文写作",R.drawable.c3)
        ,new ClassItems("办公软件",R.drawable.c3),new ClassItems("智能算法",R.drawable.c3)};
        for(int i=0;i<classes.length;i++)classesLists.add(classes[i]);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.currTag= Constant.fragment_classes;
    }
}
