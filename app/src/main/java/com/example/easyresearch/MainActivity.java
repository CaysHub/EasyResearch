package com.example.easyresearch;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.easyresearch.activity.MyActivity;
import com.example.easyresearch.constant.Constant;
import com.example.easyresearch.fragment.BaseFragment;
import com.example.easyresearch.ui.BottomControlPanel;
import com.example.easyresearch.ui.HeadControlPanel;

import static com.example.easyresearch.constant.Constant.*;

public class MainActivity extends AppCompatActivity implements BottomControlPanel.BottomPanelCallback,HeadControlPanel.HeadPanelCallback{

    BottomControlPanel bottomPanel;
    HeadControlPanel headPanel;
    private FragmentManager fragmentManager=null;
    private FragmentTransaction fragmentTransaction=null;
    public static String currTag="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        fragmentManager = getFragmentManager();
        setDefaultFirstFragment(fragment_seminar_hall);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
    }

    private void initUI(){
        if(bottomPanel == null){
            bottomPanel = (BottomControlPanel)findViewById(R.id.bottom_layout);
            bottomPanel.initBottomPanel();
            bottomPanel.setBottomCallback(this);
        }
        if(headPanel==null){
            headPanel=(HeadControlPanel)findViewById(R.id.head_layout);
            headPanel.initHeadPanel();
            headPanel.setHeadPanelCallback(this);
        }
    }

    private void setDefaultFirstFragment(String tag){
        setTabSelection(tag);
        bottomPanel.defaultBtnChecked();
    }

    private void commitTransactions(String tag){
        if (fragmentTransaction != null && !fragmentTransaction.isEmpty()) {
            fragmentTransaction.commit();
            currTag = tag;
            fragmentTransaction = null;
        }
    }

    private FragmentTransaction ensureTransaction( ){
        if(fragmentTransaction == null){
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        }
        return fragmentTransaction;
    }
    private void attachFragment(int layout, Fragment f, String tag){
        if(f != null){
            if(f.isDetached()){
                ensureTransaction();
                fragmentTransaction.attach(f);
            }else if(!f.isAdded()){
                ensureTransaction();
                fragmentTransaction.add(layout, f, tag);
            }
        }
    }
    private Fragment getFragment(String tag){

        Fragment f = fragmentManager.findFragmentByTag(tag);

        if(f==null){
            f = BaseFragment.newInstance(getApplicationContext(), tag);
        }
        return f;
    }
    private void detachFragment(Fragment f){

        if(f != null && !f.isDetached()){
            ensureTransaction();
            fragmentTransaction.detach(f);
        }
    }
    /**切换fragment
     * @param tag
     */
    private  void switchFragment(String tag){
        if(TextUtils.equals(tag, currTag)){
            return;
        }
        //把上一个fragment detach掉
        if(currTag != null && !currTag.equals("")){
            detachFragment(getFragment(currTag));
        }
        attachFragment(R.id.fragment_content, getFragment(tag), tag);
        commitTransactions(tag);
    }
    /**设置选中的Tag
     * @param tag
     */
    public  void setTabSelection(String tag) {
        // 开启一个Fragment事务
        fragmentTransaction = fragmentManager.beginTransaction();
        switchFragment(tag);
    }
    @Override
    protected void onStop() {
        super.onStop();
        currTag = "";
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onBottomPanelClick(int itemId) {
        String tag = "";
        if(itemId== btn_classes){
            tag = fragment_classes;
            Toast.makeText(getApplicationContext(), "classes", Toast.LENGTH_SHORT).show();
        }else if(itemId== btn_seminar_hall){
            tag = fragment_seminar_hall;
            Toast.makeText(getApplicationContext(), "hall", Toast.LENGTH_SHORT).show();
        }else if(itemId== btn_enterprise_project){
            tag = fragment_enterprise_project;
            Toast.makeText(getApplicationContext(), "project", Toast.LENGTH_SHORT).show();
        }
        setTabSelection(tag); //切换Fragment
        if(headPanel!=null){
            headPanel.setMiddleTitle(tag);
            headPanel.setRightTitle("");
            if (tag.equals(Constant.fragment_seminar_hall)){
                headPanel.setRightTitle("更多");
            }
        }
    }

    @Override
    public void onHeadPanelClick(int itemId) {
        switch (itemId){
            case Constant.btn_left_title:
                Intent intent=new Intent(MainActivity.this, MyActivity.class);
                startActivity(intent);
                break;
            case Constant.btn_middle_title:
                break;
            case Constant.btn_right_title:
                break;
            default:break;
        }
    }
}
