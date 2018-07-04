package com.example.easyresearch.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.easyresearch.R;
import com.example.easyresearch.entity.HallSetting;

import java.util.List;


/**
 * Created by lenovo on 2018/6/24.
 */

public class HallSettingAdapter extends RecyclerView.Adapter<HallSettingAdapter.ViewHolder>{

    private List<HallSetting> mHallSettings;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.hall_image_text_layout,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HallSetting setting=mHallSettings.get(position);
        holder.hallSettingImage.setImageResource(setting.getResourceId());
        holder.hallSettingName.setText(setting.getName());
    }

    @Override
    public int getItemCount() {
        return mHallSettings.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView hallSettingImage;
        TextView hallSettingName;

        public ViewHolder(View itemView) {
            super(itemView);
            hallSettingImage=(ImageView)itemView.findViewById(R.id.hall_image_image_text);
            hallSettingName=(TextView)itemView.findViewById(R.id.hall_text_image_text);
        }
    }
    public HallSettingAdapter(List<HallSetting> hallSettings){
        this.mHallSettings=hallSettings;
    }

}
