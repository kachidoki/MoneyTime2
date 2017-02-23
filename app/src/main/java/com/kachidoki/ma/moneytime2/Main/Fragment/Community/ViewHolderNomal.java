package com.kachidoki.ma.moneytime2.Main.Fragment.Community;

import android.view.LayoutInflater;
import android.view.View;
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

public class ViewHolderNomal extends CommunityBaseViewholder {

    @BindView(R.id.holder_community_userImg)
    ImageView holderCommunityUserImg;
    @BindView(R.id.holder_community_time)
    TextView holderCommunityTime;
    @BindView(R.id.holder_community_userName)
    TextView holderCommunityUserName;
    @BindView(R.id.holder_community_text)
    TextView holderCommunityText;
    @BindView(R.id.holder_community_img)
    ImageView holderCommunityImg;
    @BindView(R.id.holder_community_like)
    ImageView holderCommunityLike;
    @BindView(R.id.holder_community_comment)
    ImageView holderCommunityComment;
    @BindView(R.id.holder_community_wholike)
    TextView holderCommunityWholike;


    public ViewHolderNomal(ViewGroup viewGroup) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.viewholder_community_recycler_nomal, viewGroup, false));
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(Status status) {
        holderCommunityImg.setImageDrawable(itemView.getResources().getDrawable(R.drawable.icon_nolog));
        holderCommunityTime.setText("时间");
        holderCommunityUserName.setText(status.Postername());
        holderCommunityText.setText(status.message());
    }

    @Override
    public void bindUser(User user) {

    }

    @OnClick({R.id.holder_community_like, R.id.holder_community_comment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.holder_community_like:
                break;
            case R.id.holder_community_comment:
                break;

        }
    }
}
