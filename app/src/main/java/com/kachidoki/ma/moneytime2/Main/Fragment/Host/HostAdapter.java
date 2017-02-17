package com.kachidoki.ma.moneytime2.Main.Fragment.Host;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.kachidoki.ma.moneytime2.Model.Task.Task;

import java.util.List;

/**
 * Created by mayiwei on 2017/2/17.
 */

public class HostAdapter extends RecyclerView.Adapter<HostBaseViewHolder> {
    private Context context;
    private List<Task> taskList;
    private static final int NonmalView = 1;
    private static final int DayView = 2;

    public HostAdapter(Context context){
        this.context = context;
    }


    public void setData(List<Task> tasks){
        this.taskList = tasks;
        notifyDataSetChanged();
    }

    @Override
    public HostBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HostBaseViewHolder vh = null;
        switch (viewType){
            case NonmalView:
                vh =  new ViewHolderNomal(parent);
                break;
            case DayView:
                vh =  new ViewHolderDay(parent);
                break;
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(HostBaseViewHolder holder, int position) {
        holder.bind(taskList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        if (taskList.get(position).color()==Task.BLACK){
            return DayView;
        }else {
            return NonmalView;
        }
    }

    @Override
    public int getItemCount() {
        return taskList==null?0:taskList.size();
    }
}
