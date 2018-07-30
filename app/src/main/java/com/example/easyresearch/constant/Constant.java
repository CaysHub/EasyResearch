package com.example.easyresearch.constant;

import com.example.easyresearch.entity.Person;
import com.google.gson.Gson;

/**
 * Created by lenovo on 2018/6/23.
 */

public class Constant {
    //fragment
    public static final int btn_seminar_hall = 0;
    public static final int btn_enterprise_project = 1;
    public static final int btn_classes = 2;
    public static final int btn_left_title = 3;
    public static final int btn_middle_title = 4;
    public static final int btn_right_title = 5;
    //Fragment的标识
    public static final String fragment_seminar_hall = "多功能研讨厅";
    public static final String fragment_enterprise_project = "企业项目基地";
    //public static final String fragment_research_information = "科研资讯";
    //public static final String fragment_me = "我";
    public static final String fragment_classes = "简研云课堂";
    //Setting
    public static final String setting_identify="实名认证";
    public static final String setting_question="我的问题";
    public static final String setting_answer="我的回答";
    public static final String setting_exit="退出登录";

    //URL
    public static final String URL="http://5609c632.nat123.cc/";
    //Gson
    public static final Gson gson=new Gson();
    //Person
    public static Person person=null;
}
