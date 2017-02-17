package com.kachidoki.ma.moneytime2.Main.Fragment.Host;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kachidoki.ma.moneytime2.Model.Task.Task;

/**
 * Created by mayiwei on 2017/2/17.
 */

public abstract class HostBaseViewHolder extends RecyclerView.ViewHolder {

    public HostBaseViewHolder(View itemView) {
        super(itemView);
    }
    public abstract void bind(Task task);
}
