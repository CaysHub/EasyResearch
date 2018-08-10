package com.example.easyresearch.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;

import com.example.easyresearch.MainActivity;
import com.example.easyresearch.R;
import com.example.easyresearch.activity.ProjectInfoActivity;
import com.example.easyresearch.adapter.CompanyProjectAdapter;
import com.example.easyresearch.adapter.ProjectTitlesAdapter;
import com.example.easyresearch.constant.Constant;
import com.example.easyresearch.entity.CompanyProject;
import com.example.easyresearch.entity.ProjectTitle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2018/6/23.
 */

public class EnterpriseProjectFragment extends BaseFragment implements ProjectTitlesAdapter.ProjectTitleItemClickListener,
    CompanyProjectAdapter.CompanyProjectClickListener{
    private View view;
    private RecyclerView projectRecyclerView,companyProjectRV;
    private List<ProjectTitle> projectTitleList=new ArrayList<ProjectTitle> ();
    private List<CompanyProject> companyProjectList=new ArrayList<CompanyProject> ();
    private ProjectTitlesAdapter projectTitlesAdapter;
    private CompanyProjectAdapter companyProjectAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view==null){
            view=inflater.inflate(R.layout.enterprise_project_layout,container,false);
        }
        projectRecyclerView=(RecyclerView)view.findViewById (R.id.project_recycler_lists);
        companyProjectRV=(RecyclerView)view.findViewById (R.id.project_list);
        initProjectTitles();
        LinearLayoutManager manager=new LinearLayoutManager (getActivity ());
        manager.setOrientation (LinearLayout.HORIZONTAL);
        projectRecyclerView.setLayoutManager (manager);
        projectTitlesAdapter=new ProjectTitlesAdapter (projectTitleList);
        projectRecyclerView.setAdapter (projectTitlesAdapter);
        projectTitlesAdapter.setProjectTitlesListener (this);
        initCompanyProject(projectTitleList.get (0));
        LinearLayoutManager manager1=new LinearLayoutManager (getActivity ());
        manager1.setOrientation (LinearLayout.VERTICAL);
        companyProjectRV.setLayoutManager (manager1);
        companyProjectAdapter=new CompanyProjectAdapter (companyProjectList);
        companyProjectRV.setAdapter (companyProjectAdapter);
        companyProjectAdapter.setCompanyProjectClickListenerListener (this);
        return view;
    }
    private void initProjectTitles(){
        projectTitleList.clear ();
        for (int i=0;i<80;i++){
            ProjectTitle projectTitle=new ProjectTitle ("主题"+i);
            projectTitleList.add (projectTitle);
        }
    }
    private void initCompanyProject(ProjectTitle projectTitle){
        companyProjectList.clear ();
        for (int i=0;i<20;i++){
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date= formatter.format(currentTime);
            CompanyProject companyProject=new CompanyProject (projectTitle.getTitle ()+"大数据与人工智能",
                    "描述","cays",date, "计算机","计算机专业",
                    "nuaa","已发布","nuaa");
            companyProjectList.add (companyProject);
        }
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.currTag= Constant.fragment_enterprise_project;
    }

    @Override
    public void onProjectTitleClick(int position) {
        initCompanyProject (projectTitleList.get (position));
        companyProjectAdapter.notifyDataSetChanged ();
    }

    @Override
    public void onCompanyProjectClick(View item, int position) {
        Intent intent=new Intent (getActivity (), ProjectInfoActivity.class);
        String cp=Constant.gson.toJson (companyProjectList.get (position));
        intent.putExtra ("company_project",cp);
        startActivity (intent);
    }
}
