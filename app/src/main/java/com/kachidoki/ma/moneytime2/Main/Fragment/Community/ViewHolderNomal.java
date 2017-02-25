package com.kachidoki.ma.moneytime2.Main.Fragment.Community;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kachidoki.ma.moneytime2.Model.Status.Status;
import com.kachidoki.ma.moneytime2.Model.Status.StatusSource;
import com.kachidoki.ma.moneytime2.Model.User.User;
import com.kachidoki.ma.moneytime2.Model.User.UserSource;
import com.kachidoki.ma.moneytime2.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mayiwei on 2017/2/21.
 */

public class ViewHolderNomal extends CommunityBaseViewholder {
    List<String> likes;
    User user;
    boolean userlikes;
    String detilId ;
    private StatusSource statusSource;
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


    public ViewHolderNomal(ViewGroup viewGroup, StatusSource statusSource) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.viewholder_community_recycler_nomal, viewGroup, false));
        ButterKnife.bind(this, itemView);
        this.statusSource = statusSource;
    }

    @Override
    public void bind(Status status) {
        detilId = status.statusDetil().detailId();
        holderCommunityImg.setImageDrawable(itemView.getResources().getDrawable(R.drawable.icon_nolog));
        holderCommunityTime.setText("时间");
        holderCommunityUserName.setText(status.Postername());
        holderCommunityText.setText(status.message());
        if (status.imgurl()!=null){
            Glide.with(itemView.getContext()).load(status.imgurl()).into(holderCommunityImg);
        }
        likes = status.statusDetil().likes();
        userlikes = likes.contains(user.objectId());
        setLikesimg();
    }

    @Override
    public void bindUser(User user) {
        this.user = user;
    }


    private void setLikesimg(){
        if (userlikes) {
            holderCommunityLike.setImageResource(R.drawable.icon_like_2);
        } else {
            holderCommunityLike.setImageResource(R.drawable.icon_like_1);
        }
    }

    private void savelikes(String detilId,String userID){
        if (userlikes) {
            likes.remove(user.objectId());
            statusSource.unlikeStatus(detilId, userID);
            userlikes = false;
            setLikesimg();
        } else {
            likes.add(user.objectId());
            statusSource.likeStatus(detilId, userID);
            userlikes = true;
            setLikesimg();
        }
    }

    @OnClick({R.id.holder_community_like, R.id.holder_community_comment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.holder_community_like:
                savelikes(detilId,user.objectId());
                break;
            case R.id.holder_community_comment:
                break;

        }
    }
}
