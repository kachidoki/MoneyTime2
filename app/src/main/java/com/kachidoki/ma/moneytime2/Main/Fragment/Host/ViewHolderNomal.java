package com.kachidoki.ma.moneytime2.Main.Fragment.Host;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kachidoki.ma.moneytime2.Model.Task.Task;
import com.kachidoki.ma.moneytime2.R;
import com.kachidoki.ma.moneytime2.ShowTask.ShowTaskActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mayiwei on 2017/2/17.
 */

public class ViewHolderNomal extends HostBaseViewHolder {

    @BindView(R.id.holder_host_starttime)
    TextView holderHostStarttime;
    @BindView(R.id.holder_host_endtime)
    TextView holderHostEndtime;
    @BindView(R.id.holder_host_color)
    ImageView holderHostColor;
    @BindView(R.id.holder_host_title)
    TextView holderHostTitle;

    public ViewHolderNomal(ViewGroup viewGroup) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.viewholder_host_recycler_nomal, viewGroup, false));
        ButterKnife.bind(this,itemView);
    }

    public String TransformTime(float time){
        String tansTime;
        int a = 0;
        if(time - (int)time==0.5){
            a = (int)time;
            tansTime = a+":30";
            return tansTime;
        }else {
            tansTime = (int) time+":00";
            return tansTime;
        }
    }


    @Override
    public void bind(final Task task) {
        holderHostStarttime.setText(TransformTime(task.startTime()));
        holderHostEndtime.setText(TransformTime(task.endTime()));
        if(task.complete()) holderHostTitle.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holderHostTitle.setText(task.title());
        if(task.color()==Task.RED) holderHostColor.setImageResource(R.drawable.icon_round_red);
        if(task.color()==Task.BLUE) holderHostColor.setImageResource(R.drawable.icon_round_blue);
        if(task.color()==Task.ORANGE) holderHostColor.setImageResource(R.drawable.icon_round_orange);
        if(task.color()==Task.YELLOW) holderHostColor.setImageResource(R.drawable.icon_round_yellow);
        if(task.color()==Task.GREEN) holderHostColor.setImageResource(R.drawable.icon_round_green);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowTaskActivity.gotoShowTask(itemView.getContext(),task);
            }
        });
    }
}
