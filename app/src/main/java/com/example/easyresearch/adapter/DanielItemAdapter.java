package com.example.easyresearch.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.easyresearch.R;
import com.example.easyresearch.entity.Teacher;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DanielItemAdapter extends RecyclerView.Adapter<DanielItemAdapter.ViewHolder>{
    private List<Teacher> teacherList;
    private String classes="课程数：",type="研究方向：",rate="好评率";
    public DanielItemAdapter(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from (parent.getContext ()).inflate (R.layout.daniel_item,parent,false);
        ViewHolder holder=new ViewHolder (view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Teacher teacher=teacherList.get (position);
        holder.instruction.setText (teacher.getInstruction ());
        holder.name.setText (teacher.getName ());
        holder.type.setText (type+teacher.getType ());
        holder.classes.setText (classes+teacher.getClasses ());
        holder.good_rate.setText (String.format ("%.2f",teacher.getGood ()*100/(double)(teacher.getGood ()+teacher.getBad ()))+"%");
        if(listener!=null){
            holder.itemView.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    listener.onDanielItemClick (holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return teacherList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView instruction,name,type,classes,good_rate;
        public ViewHolder(View itemView) {
            super (itemView);
            img=(CircleImageView)itemView.findViewById (R.id.daniel_img);
            instruction=(TextView)itemView.findViewById (R.id.daniel_instruction);
            name=(TextView)itemView.findViewById (R.id.daniel_name);
            type=(TextView)itemView.findViewById (R.id.daniel_type);
            classes=(TextView)itemView.findViewById (R.id.daniel_class);
            good_rate=(TextView)itemView.findViewById (R.id.daniel_good_rate);
        }
    }
    public interface DanielItemClickListener{
        public void onDanielItemClick(View item,int position);
    }
    private DanielItemClickListener listener;
    public void setListener(DanielItemClickListener listener){
        this.listener=listener;
    }
}
