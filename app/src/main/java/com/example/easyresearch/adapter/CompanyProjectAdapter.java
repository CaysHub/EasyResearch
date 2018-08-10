package com.example.easyresearch.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.easyresearch.R;
import com.example.easyresearch.entity.CompanyProject;

import java.util.List;

public class CompanyProjectAdapter extends RecyclerView.Adapter<CompanyProjectAdapter.ViewHolder>{
    private List<CompanyProject> companyProjectList;

    public CompanyProjectAdapter(List<CompanyProject> companyProjectList) {
        this.companyProjectList = companyProjectList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from (parent.getContext ()).inflate (R.layout.project_list_item,parent,false);
        ViewHolder holder=new ViewHolder (view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        CompanyProject companyProject=companyProjectList.get (position);
        holder.title.setText (companyProject.getTitle ());
        holder.instruction.setText (companyProject.getInstruction ());
        holder.company.setText (companyProject.getCompany ());
        holder.time.setText (companyProject.getTime ());
        holder.type.setText (companyProject.getType ());
        holder.request.setText (companyProject.getRequestor ());
        holder.location.setText (companyProject.getLocation ());
        holder.status.setText (companyProject.getStatus ());
        if (listener!=null){
            holder.itemView.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    listener.onCompanyProjectClick (holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return companyProjectList.size ();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,instruction,type,time,status,request,location,company;
        public ViewHolder(View itemView) {
            super (itemView);
            title=(TextView)itemView.findViewById (R.id.project_title);
            instruction=(TextView)itemView.findViewById (R.id.project_instruction);
            type=(TextView)itemView.findViewById (R.id.project_type);
            time=(TextView)itemView.findViewById (R.id.project_time);
            status=(TextView)itemView.findViewById (R.id.project_status);
            company=(TextView)itemView.findViewById (R.id.project_company);
            location=(TextView)itemView.findViewById (R.id.project_location);
            request=(TextView)itemView.findViewById (R.id.project_request);
        }
    }
    public interface CompanyProjectClickListener{
        public void onCompanyProjectClick(View item,int position);
    }
    private CompanyProjectClickListener listener;
    public void setCompanyProjectClickListenerListener(CompanyProjectClickListener listener){
        this.listener=listener;
    }
}
