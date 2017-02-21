package com.kachidoki.ma.moneytime2.Main.Fragment.Community;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kachidoki.ma.moneytime2.Model.Status.Status;
import com.kachidoki.ma.moneytime2.Model.User.User;
import com.kachidoki.ma.moneytime2.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mayiwei on 2017/2/21.
 */

public class ViewHolderHead extends CommunityBaseViewholder {

    @BindView(R.id.holder_community_myUserImg)
    ImageView holderCommunityMyUserImg;
    @BindView(R.id.holder_community_myUserName)
    TextView holderCommunityMyUserName;

    public ViewHolderHead(ViewGroup viewGroup) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.viewholder_community_recycler_head, viewGroup, false));
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(Status status) {

    }

    @Override
    public void bindUser(User user) {
        if (user == null) {
            holderCommunityMyUserImg.setImageDrawable(itemView.getResources().getDrawable(R.drawable.icon_nolog));
            holderCommunityMyUserName.setText("请先登录");
        }
    }

    @OnClick(R.id.holder_community_myUserImg)
    public void onClick() {
    }
}
