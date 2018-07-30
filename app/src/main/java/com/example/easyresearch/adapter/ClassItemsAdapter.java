package com.example.easyresearch.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.easyresearch.R;
import com.example.easyresearch.entity.ClassItems;

import java.util.List;

/**
 * Created by lenovo on 2018/6/24.
 */

public class ClassItemsAdapter extends RecyclerView.Adapter<ClassItemsAdapter.ViewHolder>{
    private List<ClassItems> mClassItemses;

    public ClassItemsAdapter(List<ClassItems> classItemses){
        this.mClassItemses=classItemses;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.hall_image_text_layout,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ClassItems classItems=mClassItemses.get(position);
        holder.hallSettingImage.setImageResource(classItems.getResourceId());
        holder.hallSettingName.setText(classItems.getName());
        if (mListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.classItemClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mClassItemses.size();
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
    //添加点击事件
    public interface ClassItemClickListener{
        public void classItemClick(int position);
    }
    public ClassItemClickListener mListener;
    public void setClassItemClickListener(ClassItemClickListener itemClickListener){
        this.mListener=itemClickListener;
    }
}
