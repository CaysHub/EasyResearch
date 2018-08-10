package com.example.easyresearch.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.easyresearch.R;
import com.example.easyresearch.entity.ProjectTitle;

import java.util.ArrayList;
import java.util.List;

public class ProjectTitlesAdapter extends RecyclerView.Adapter<ProjectTitlesAdapter.ViewHolder>{
    private List<ProjectTitle> projectTitleList;
    private List<Boolean> isChicks;
    public ProjectTitlesAdapter(List<ProjectTitle> projectTitleList) {
        this.projectTitleList = projectTitleList;
        this.isChicks=new ArrayList<Boolean> ();
        for(int i=0;i<projectTitleList.size ();i++){
            this.isChicks.add (false);
        }
        isChicks.set (0,true);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from (parent.getContext ()).inflate (R.layout.project_title_items,parent,false);
        ViewHolder holder=new ViewHolder (view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ProjectTitle projectTitles=projectTitleList.get (position);
        holder.projectTitlesText.setText (projectTitles.getTitle ());
        holder.itemView.setTag (holder.projectTitlesText);
        if (isChicks.get (position)){
            holder.projectTitlesText.setTextColor (Color.parseColor ("#2e4ec3"));
        }else{
            holder.projectTitlesText.setTextColor (Color.parseColor ("#000000"));
        }
        if(listener!=null){
            holder.itemView.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    for(int i=0;i<projectTitleList.size ();i++){
                        isChicks.set (i,false);
                    }
                    isChicks.set (position,true);
                    notifyDataSetChanged ();
                    listener.onProjectTitleClick (position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return projectTitleList.size ();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView projectTitlesText;
        public ViewHolder(View itemView) {
            super (itemView);
            projectTitlesText=(TextView)itemView.findViewById (R.id.project_titles_text);
        }
    }
    //定义回调接口
    public interface ProjectTitleItemClickListener{
        public void onProjectTitleClick(int position);
    }
    public ProjectTitleItemClickListener listener;
    public void setProjectTitlesListener(ProjectTitleItemClickListener listener){
        this.listener=listener;
    }
}