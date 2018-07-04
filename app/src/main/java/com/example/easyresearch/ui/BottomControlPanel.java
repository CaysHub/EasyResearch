package com.example.easyresearch.ui;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.example.easyresearch.R;
import com.example.easyresearch.constant.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2018/2/20.
 */

public class BottomControlPanel extends RelativeLayout implements View.OnClickListener {
    private Context mContext=null;
    private ImageText classesBtn=null,hallBtn=null,projectBtn=null;
    private int background_color= Color.rgb(243,243,243);
    private BottomPanelCallback bottomCallback=null;
    private List<ImageText> viewList=new ArrayList<ImageText>();
    public interface BottomPanelCallback{
        public void onBottomPanelClick(int itemId);
    }
    public BottomControlPanel(Context context){
        super(context);
    }
    public BottomControlPanel(Context context, AttributeSet attrs){
        super(context,attrs);
    }
    public BottomControlPanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        classesBtn=(ImageText)findViewById(R.id.bottom_classes_btn);
        hallBtn=(ImageText)findViewById(R.id.bottom_hall_btn);
        projectBtn=(ImageText)findViewById(R.id.bottom_project_btn);
        setBackgroundColor(background_color);
        viewList.add(hallBtn);viewList.add(projectBtn);
        viewList.add(classesBtn);
    }
    public void initBottomPanel(){
        if(classesBtn != null){
            classesBtn.setImage(R.drawable.ic_assessment_light_blue_200_24dp);
            classesBtn.setText(Constant.fragment_classes);
        }
        if(hallBtn != null){
            hallBtn.setImage(R.drawable.ic_home_light_blue_200_24dp);
            hallBtn.setText(Constant.fragment_seminar_hall);
        }
        if(projectBtn != null){
            projectBtn.setImage(R.drawable.ic_settings_ethernet_light_blue_200_24dp);
            projectBtn.setText(Constant.fragment_enterprise_project);
        }
        setBtnListener();
    }
    private void setBtnListener(){
        int num = this.getChildCount();
        for(int i = 0; i < num; i++){
            View v = getChildAt(i);
            if(v != null){
                v.setOnClickListener(this);
            }
        }
    }
    public void setBottomCallback(BottomPanelCallback mBottomCallback){
        bottomCallback = mBottomCallback;
    }
    @Override
    public void onClick(View v) {
        initBottomPanel();
        int index = -1;
        switch(v.getId()){
            case R.id.bottom_classes_btn:
                index = Constant.btn_classes;
                classesBtn.setChecked(Constant.btn_classes);
                break;
            case R.id.bottom_hall_btn:
                index = Constant.btn_seminar_hall;
                hallBtn.setChecked(Constant.btn_seminar_hall);
                break;
            case R.id.bottom_project_btn:
                index = Constant.btn_enterprise_project;
                projectBtn.setChecked(Constant.btn_enterprise_project);
                break;
            default:break;
        }
        if(bottomCallback != null){
            bottomCallback.onBottomPanelClick(index);
        }
    }
    public void defaultBtnChecked(){
        if(hallBtn != null){
            hallBtn.setChecked(Constant.btn_seminar_hall);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        layoutItems(l,t,r,b);
    }
    private void layoutItems(int left, int top, int right, int bottom){
        int n = getChildCount();
        if(n == 0){
            return;
        }
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int width = right - left;
        int height = bottom - top;
        int allViewWidth = 0;
        for(int i = 0; i< n; i++){
            View v = getChildAt(i);
            allViewWidth += v.getWidth();
        }
        int blankWidth = (width - allViewWidth - paddingLeft - paddingRight) / (n + 1);
        int i;
        for(i=0;i<n-1;i++) {
            LayoutParams params1 = (LayoutParams) viewList.get(i).getLayoutParams();
            params1.leftMargin = blankWidth;
            viewList.get(i).setLayoutParams(params1);
        }

        LayoutParams params2 = (LayoutParams) viewList.get(i).getLayoutParams();
        params2.leftMargin = blankWidth;
        viewList.get(i).setLayoutParams(params2);
    }
}
