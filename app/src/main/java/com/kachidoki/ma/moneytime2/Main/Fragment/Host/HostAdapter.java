package com.kachidoki.ma.moneytime2.Main.Fragment.Host;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.kachidoki.ma.moneytime2.Model.Task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mayiwei on 2017/2/17.
 */

public class HostAdapter extends RecyclerView.Adapter<HostBaseViewHolder> {
    private Context context;
    private List<Task> taskList;
    private static final int NonmalView = 1;
    private static final int DayView = 2;
    private static final int HeadView = 3;
    private static final int NullView = 4;

    public HostAdapter(Context context){
        this.context = context;
        taskList = new ArrayList<>();
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
            case HeadView:
                vh = new ViewHolderHead(parent);
                break;
            case NullView:
                vh = new ViewHolderNull(parent);
                break;
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(HostBaseViewHolder holder, int position) {
        holder.bind(position>0?taskList.get(position-1):null);
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0) return HeadView;
        if (taskList.isEmpty()){
            return NullView;
        }else {
            if (taskList.get(position-1).color()==Task.BLACK){
                return DayView;
            }else {
                return NonmalView;
            }
        }
    }

    @Override
    public int getItemCount() {
        return taskList.isEmpty()?1:taskList.size()+1;
    }
}
