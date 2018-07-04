package com.example.easyresearch.MySetting;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.easyresearch.R;

import java.util.List;


/**
 * Created by lenovo on 2018/6/24.
 */

public class MySettingAdapter extends ArrayAdapter<MySetting>{
    private int resourceId;

    public MySettingAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<MySetting> objects) {
        super(context, resource, objects);resourceId=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        MySetting mySetting=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView settingName=(TextView)view.findViewById(R.id.my_setting_text);
        settingName.setText(mySetting.getName());
        return view;
    }
}
