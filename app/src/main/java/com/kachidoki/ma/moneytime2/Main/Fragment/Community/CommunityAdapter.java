package com.kachidoki.ma.moneytime2.Main.Fragment.Community;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.kachidoki.ma.moneytime2.Model.Status.Status;
import com.kachidoki.ma.moneytime2.Model.User.UserSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mayiwei on 2017/2/21.
 */

public class CommunityAdapter extends RecyclerView.Adapter<CommunityBaseViewholder> {

    private List<Status> statusList;
    private UserSource userSource;
    private Context context;

    private static final int NonmalView = 1;
    private static final int HeadView = 2;
    private static final int NullView = 3;

    public CommunityAdapter(Context context, UserSource userSource){
        this.context = context;
        this.userSource = userSource;
        statusList = new ArrayList<>();
    }

    public void setData(List<Status> statuses){
        this.statusList = statuses;
        notifyDataSetChanged();
    }


    @Override
    public CommunityBaseViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommunityBaseViewholder vh = null;
        switch (viewType){
            case NonmalView:
                vh = new ViewHolderNomal(parent);
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
    public void onBindViewHolder(CommunityBaseViewholder holder, int position) {
        holder.bind(position>0?statusList.get(position-1):null);
        if (userSource.isLogin()){
            holder.bindUser(userSource.getNowUser());
        }
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
