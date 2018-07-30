package com.example.easyresearch.fragment;

import android.content.Intent;
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
import com.example.easyresearch.activity.ClassActivity;
import com.example.easyresearch.activity.ClassInfoActivity;
import com.example.easyresearch.adapter.ClassInfoAdapter;
import com.example.easyresearch.adapter.ClassItemsAdapter;
import com.example.easyresearch.constant.Constant;
import com.example.easyresearch.entity.ClassInfo;
import com.example.easyresearch.entity.ClassItems;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by lenovo on 2018/6/23.
 */

public class ClassesFragment extends BaseFragment
        implements ClassItemsAdapter.ClassItemClickListener, ClassInfoAdapter.ClassInfoClickListener{
    private View view;
    private List<ClassItems> classesLists=new ArrayList<>();
    private List<ClassInfo> classInfoLists=new ArrayList<ClassInfo>();
    private ClassItemsAdapter classItemsAdapter;
    private PtrFrameLayout ptrFrameLayout;
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
        classItemsAdapter.setClassItemClickListener(this);

        RecyclerView recyclerView1=(RecyclerView)view.findViewById(R.id.classes_info_list);
        ClassInfoAdapter infoAdapter=new ClassInfoAdapter(classInfoLists);
        GridLayoutManager layoutManager1=new GridLayoutManager(getActivity(),1);
        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.setAdapter(infoAdapter);
        infoAdapter.setmClassInfoListener(this);
        ptrFrameLayout=(PtrFrameLayout)view.findViewById(R.id.classes_refresh);
        initRefresh();
        return view;
    }
    private void initClasses(){
        classesLists.clear();
        classInfoLists.clear();
        ClassItems []classes={new ClassItems("科研工具",R.drawable.c3),new ClassItems("论文写作",R.drawable.c3)
        ,new ClassItems("办公软件",R.drawable.c3),new ClassItems("智能算法",R.drawable.c3)};
        for(int i=0;i<classes.length;i++)classesLists.add(classes[i]);
        for (int i=0;i<40;i++){
            String name="java",title="java精品课",teacher="王军";
            String instruction="info",time= "88分钟",type="vip";
            double price=999d;String date=String.format("yyMMddhhmmss",new Date());
            ClassInfo info=new ClassInfo(name,title,teacher,instruction,time,
                    type,price,date);
            classInfoLists.add(info);
        }
    }
    public void initRefresh(){
        //这里是一个自定义的头部刷新布局,自带的也有一个布局   new PtrDefaultHandler();
        PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(getActivity());
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
                ptrFrameLayout.refreshComplete();
            }
        });
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

    @Override
    public void classItemClick(int position) {
        Intent intent=new Intent(getActivity(), ClassActivity.class);
        intent.putExtra("classType","");
        startActivity(intent);
    }

    @Override
    public void onClassInfoClick(int position) {
        ClassInfo info=classInfoLists.get(position);
        Intent intent=new Intent(getActivity(), ClassInfoActivity.class);
        intent.putExtra("classInfo",Constant.gson.toJson(info));
        startActivity(intent);
    }
}
