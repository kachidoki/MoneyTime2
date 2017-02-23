package com.kachidoki.ma.moneytime2.Main.Fragment.Community;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.kachidoki.ma.moneytime2.Model.Status.Status;
import com.kachidoki.ma.moneytime2.Model.Status.StatusSource;
import com.kachidoki.ma.moneytime2.Model.User.User;
import com.kachidoki.ma.moneytime2.Model.User.UserSource;
import com.kachidoki.ma.moneytime2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mayiwei on 2017/2/21.
 */

public class CommunityAdapter extends RecyclerView.Adapter<CommunityBaseViewholder> {

    private List<Status> statusList;
    private User user;
    private Context context;
    private StatusSource statusSource;

    private static final int NonmalView = 1;
    private static final int HeadView = 2;
    private static final int NullView = 3;

    public CommunityAdapter(Context context, StatusSource statusSource){
        this.context = context;
        statusList = new ArrayList<>();
        this.statusSource = statusSource;
    }

    public void setData(List<Status> statuses){
        this.statusList = statuses;
        notifyDataSetChanged();
    }

    public void setUser(User user){
        this.user = user;
        notifyDataSetChanged();
    }


    @Override
    public CommunityBaseViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommunityBaseViewholder vh = null;
        switch (viewType){
            case NonmalView:
                vh = new ViewHolderNomal(parent,statusSource);
                break;
            case HeadView:
                vh = new ViewHolderHead(parent);
                break;
            case NullView:
                break;
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(CommunityBaseViewholder holder, final int position) {
        holder.bindUser(user);
        holder.bind(position>0?statusList.get(position-1):null);
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0) return HeadView;
        if (statusList.isEmpty()){
            return NullView;
        }else {
            return NonmalView;
        }
    }

    @Override
    public int getItemCount() {
        return statusList.isEmpty()?1:statusList.size()+1;
    }
}
