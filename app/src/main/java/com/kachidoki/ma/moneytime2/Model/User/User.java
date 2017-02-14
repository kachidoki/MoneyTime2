package com.kachidoki.ma.moneytime2.Model.User;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

/**
 * Created by mayiwei on 2017/2/14.
 */
@AutoValue
public abstract class User implements Parcelable {

    public static final String IMGURL = "imgUrl";

    public abstract String objectId();
    @Nullable public abstract String email();
    @Nullable public abstract String sessionToken();
    public abstract String username();
    public abstract String phoneNumber();
    public abstract boolean phoneVerified();

}
