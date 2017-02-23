package com.kachidoki.ma.moneytime2.Model.Status;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

import java.util.Date;
import java.util.List;

/**
 * Created by mayiwei on 2017/2/14.
 */

@AutoValue
public abstract class Status implements Parcelable{
    public static final String TABLE = "_Status";
    public static final String INBOX_TIMELINE =  "default";
    public static final String INBOX_SYSTEM = "system";


    public static final String LIKES = "likes";
    public static final String STATUS_DETAIL = "StatusDetail";
    public static final String DETAIL_ID = "detailId";
    public static final String CREATED_AT = "createdAt";
    public static final String FOLLOWER = "follower";
    public static final String FOLLOWEE = "followee";
    public static final String INBOXTYPE = "inboxType";
    public static final String SOURCE = "source";
    public static final String IMG = "image";
    public static final String MESSAGE = "message";
    public static final String COMMENT = "StatusComment";

    public static final int MUTUAL_FOLLOW = 0;//disable follow
    public static final int ISMYFOLLOWER = 1;  //can follow
    public static final int MYFOLLOWING = 2;  //disable follow
    public static final int NONE_FOLLOW = 3; //can follow

    public abstract String objectId();
    public abstract String detailId();
    public abstract String inboxType();
    public abstract String Postername();
    @Nullable public abstract String Posterimg();
    @Nullable public abstract String imgurl();
    public abstract String source();
    public abstract String message();
    public abstract Date createAt();
    @Nullable public abstract StatusDetil statusDetil();

    @AutoValue
    public abstract static class StatusDetil implements Parcelable{
        @Nullable public abstract List<Comment> comments();
        @Nullable public abstract List<String> likes();
    }

    @AutoValue
    public abstract static class Comment implements Parcelable{
        public static final String STATUSID = "statusId";
        public static final String SOURCE = "source";
        public static final String MESSAGE = "message";
        public static final String POSTIMG = "postimg";
        public static final String CRATEAT = "createAt";

        public abstract String objectId();
        public abstract String statusId();
        public abstract String source();
        public abstract String message();
        @Nullable public abstract String postimg();
        public abstract Date createAt();
    }

}
