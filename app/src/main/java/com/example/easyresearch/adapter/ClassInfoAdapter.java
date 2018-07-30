package com.example.easyresearch.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.easyresearch.R;
import com.example.easyresearch.entity.ClassInfo;

import java.util.ArrayList;
import java.util.List;

public class ClassInfoAdapter extends RecyclerView.Adapter<ClassInfoAdapter.ViewHolder>{
    private List<ClassInfo> mClassInfos=new ArrayList<ClassInfo>();
    public ClassInfoAdapter(List<ClassInfo> classInfos){
        this.mClassInfos=classInfos;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.classes_class_items,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ClassInfo classInfo=mClassInfos.get(position);
        holder.classTitle.setText(classInfo.getTitle());
        holder.className.setText(classInfo.getName());
        holder.classTeacher.setText(classInfo.getTeacher());
        holder.classPrice.setText(""+classInfo.getPrice());
        holder.classType.setText(classInfo.getType());
        holder.classTime.setText(classInfo.getTime());
        if(mClassInfoListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mClassInfoListener.onClassInfoClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mClassInfos.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView className,classTitle,classTeacher,classTime,classType;
        private Button classPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            className=(TextView)itemView.findViewById(R.id.class_name);
            classTitle=(TextView)itemView.findViewById(R.id.class_title);
            classTeacher=(TextView)itemView.findViewById(R.id.class_teacher);
            classTime=(TextView)itemView.findViewById(R.id.class_time);
            classType=(TextView)itemView.findViewById(R.id.class_type);
            classPrice=(Button)itemView.findViewById(R.id.class_price);
        }
    }
    //定义接口监听
    public interface ClassInfoClickListener{
        public void onClassInfoClick(int position);
    }
    public ClassInfoClickListener mClassInfoListener;
    public void setmClassInfoListener(ClassInfoClickListener listener){
        this.mClassInfoListener=listener;
    }
}
