package com.kachidoki.ma.moneytime2.Model.Status;

import android.graphics.Bitmap;

import com.kachidoki.ma.moneytime2.Model.User.User;

import java.util.List;

import rx.Observable;

/**
 * Created by mayiwei on 2017/2/14.
 */

public interface StatusSource {

    interface StatusCall{

        void fail(Exception e);

        void sucess();
    }

    void sendStatus(String text, Bitmap bitmap,String inboxType,StatusCall call);

    void sendPublicStatus(String text, Bitmap bitmap,String inboxType,StatusCall call);

    void sendPrivateStatus();

    Observable<List<Status>> getPublicStatus(String inboxType);

    Observable<List<Status>> getInbox(String inboxType);

    void deleteStatus(Status status);

    void likeStatus(String detil,List<String> likes,StatusCall statusCall);

    void likeStatus(String detil,String userId);

    void unlikeStatus(String detil,String userId);

    Observable<List<Status.Comment>> getStatusComment();

    Observable<List<User>> getFollowers(String userObjId);

    Observable<List<User>> getFollowings(String userObjId);

    int getFollowStatus(Object user);

}
