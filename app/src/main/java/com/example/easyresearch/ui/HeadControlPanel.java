package com.example.easyresearch.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.easyresearch.R;
import com.example.easyresearch.constant.Constant;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lenovo on 2018/6/23.
 */

public class HeadControlPanel extends RelativeLayout implements View.OnClickListener{
    private Context mContext=null;
    private TextView middleTitle,rightTitle;
    private CircleImageView leftImage;
    private HeadPanelCallback headPanelCallback=null;


    public interface HeadPanelCallback{
        public void onHeadPanelClick(int itemId);
    }
    public HeadControlPanel(Context context) {
        super(context);
    }

    public HeadControlPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeadControlPanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onFinishInflate() {
        leftImage=(CircleImageView) findViewById(R.id.backward);
        middleTitle=(TextView) findViewById(R.id.title);
        rightTitle=(TextView) findViewById(R.id.forward);
    }
    public void initHeadPanel(){
        if(middleTitle != null){
            setMiddleTitle(Constant.fragment_seminar_hall);
        }
        if(leftImage!=null){
            setLeftImage(R.drawable.c4yun);
        }
        if(rightTitle!=null){
            setRightTitle("更多");
        }
        int num = this.getChildCount();
        for(int i = 0; i < num; i++){
            View v = getChildAt(i);
            if(v != null){
                v.setOnClickListener(this);
            }
        }
    }
    public void setMiddleTitle(String title){
        middleTitle.setText(title);
    }
    public void setRightTitle(String right_title){
        if(rightTitle!=null)rightTitle.setText(right_title);
    }
    public void setLeftImage(int imageId){
        if(leftImage!=null){
            Glide.with(getContext())
                    .load(imageId)
                    .centerCrop()
                    .dontAnimate()//防止设置placeholder导致第一次不显示网络图片,只显示默认图片的问题
                    .error(imageId)
                    .placeholder(imageId)
                    .into(leftImage);
        }
    }
    public void setHeadPanelCallback(HeadPanelCallback mHeadPanelCallback){
        headPanelCallback=mHeadPanelCallback;
    }
    @Override
    public void onClick(View v) {
        int index=-1;
        switch (v.getId()){
            case R.id.backward:
                index=Constant.btn_left_title;
                break;
            case R.id.title:
                index=Constant.btn_middle_title;
                break;
            case R.id.forward:
                index=Constant.btn_right_title;
                break;
            default:break;
        }
        if(headPanelCallback!=null){
            headPanelCallback.onHeadPanelClick(index);
        }
    }
}
