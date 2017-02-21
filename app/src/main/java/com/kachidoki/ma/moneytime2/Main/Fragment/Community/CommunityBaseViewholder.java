package com.kachidoki.ma.moneytime2.Main.Fragment.Community;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kachidoki.ma.moneytime2.Model.Status.Status;
import com.kachidoki.ma.moneytime2.Model.User.User;

/**
 * Created by mayiwei on 2017/2/21.
 */

public abstract class CommunityBaseViewholder extends RecyclerView.ViewHolder {

    public CommunityBaseViewholder(View itemView) {
        super(itemView);
    }

    public abstract void bind(Status status);
    public abstract void bindUser(User user);
}
