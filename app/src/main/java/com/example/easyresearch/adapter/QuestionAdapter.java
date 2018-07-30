package com.example.easyresearch.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.easyresearch.R;
import com.example.easyresearch.entity.Question;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder>{
    private List<Question> mQuestions;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.question_items_layout,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Question question=mQuestions.get(position);
        holder.questionTitle.setText(question.getTitle());
        holder.questionInstruction.setText(question.getContent());
        holder.questionPay.setText(""+question.getReward());
        holder.questionRead.setText(""+question.getLookNum());
        holder.questionZan.setText(""+question.getZanNum());
        if (itemClickListener!=null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.questionItemClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mQuestions.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        //question_title question_pay question_instruction question_image question_read_num question_zan
        TextView questionTitle,questionPay,questionInstruction,questionRead,questionZan;
        public ViewHolder(View itemView) {
            super(itemView);
            questionTitle=(TextView)itemView.findViewById(R.id.question_title);
            questionPay=(TextView)itemView.findViewById(R.id.question_pay);
            questionInstruction=(TextView)itemView.findViewById(R.id.question_instruction);
            questionRead=(TextView)itemView.findViewById(R.id.question_read_num);
            questionZan=(TextView)itemView.findViewById(R.id.question_zan);
        }
    }
    public QuestionAdapter(List<Question> questions){
        this.mQuestions=questions;
    }
    //定义接口事件
    public interface QuestionItemClickListener{
        public void questionItemClick(int position);
    }
    public QuestionItemClickListener itemClickListener;
    public void setItemClickListener(QuestionItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }
}
