package com.kachidoki.ma.moneytime2.Main.Fragment.Host;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kachidoki.ma.moneytime2.Model.Task.Task;
import com.kachidoki.ma.moneytime2.R;

/**
 * Created by mayiwei on 2017/2/20.
 */

public class ViewHolderNull extends HostBaseViewHolder {

    public ViewHolderNull(ViewGroup viewGroup) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.viewholder_host_recycler_null, viewGroup, false));
    }

    @Override
    public void bind(Task task) {

    }
}
