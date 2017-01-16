package com.oceansoft.osga.moudle.consult.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oceansoft.osga.R;
import com.oceansoft.osga.moudle.consult.bean.ConsultMatter;
import com.oceansoft.osga.utils.ToastUtil;

import java.util.List;

/**
 * Created by TempCw on 2017/1/15.
 * 咨询中心界面 adapter
 */

public class ConsultAdapter extends RecyclerView.Adapter<ConsultAdapter.ViewHolder>{

    private List<ConsultMatter.DataBean.ListBean> mList;
    private Context context;
    private OnItemClickListener mListener;

    public ConsultAdapter(List<ConsultMatter.DataBean.ListBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    public void setmListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.consult_adapter_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txt_question_content.setText(mList.get(position).getContext());
        holder.view_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showToast(context,"点击的问题");
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private  TextView txt_question_content;
        private TextView txt_question_time;
        private TextView txt_title;

        private RelativeLayout view_question;

        public ViewHolder(View itemView) {
           super(itemView);
             txt_question_content = (TextView) itemView.findViewById(R.id.txt_question_content);
            txt_question_time= (TextView) itemView.findViewById(R.id.txt_question_time);
            txt_title= (TextView) itemView.findViewById(R.id.txt_title);
            view_question= (RelativeLayout) itemView.findViewById(R.id.view_question);


//           TextView txt_question_content = (TextView) ViewHolder.get(view, R.id.txt_question_content);
//           TextView txt_question_time = (TextView) ViewHolder.get(view, R.id.txt_question_time);
//           TextView txt_title = ViewHolder.get(view, R.id.txt_title);
//           final View view_loading = ViewHolder.get(view, R.id.view_loading);
//           final View view_question = ViewHolder.get(view, R.id.view_question);
//           final TextView txt_answer_content = (TextView) ViewHolder.get(view, R.id.txt_answer_content);
//           final TextView txt_answer_time = (TextView) ViewHolder.get(view, R.id.txt_answer_time);
//           final View view_answer = ViewHolder.get(view, R.id.view_answer);
//           final TextView txt_no_response = (TextView) ViewHolder.get(view, R.id.txt_no_response);
       }
   }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
