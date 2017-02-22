package com.kachidoki.ma.moneytime2.Model.Status;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVRelation;
import com.avos.avoscloud.AVStatus;
import com.avos.avoscloud.AVStatusQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.kachidoki.ma.moneytime2.Model.User.User;
import com.kachidoki.ma.moneytime2.Model.User.UserSource;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by mayiwei on 2017/2/14.
 */

public class AVStatusModel implements StatusSource {

    @NonNull
    private final UserSource userModel;

    public AVStatusModel(@NonNull UserSource userSource){
        this.userModel = userSource;
    }


    @Override
    public boolean checkIsLogin() {
        return userModel.isLogin();
    }

    @Override
    public String getNowUserID() {
        return userModel.getNowUser().objectId();
    }

    @Override
    public void sendStatus(final String text, Bitmap bitmap, final String inboxType, final StatusCall call) {
        if (!userModel.isLogin()) return;
        if (bitmap != null) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);
            byte[] bs = out.toByteArray();
            AVUser user = (AVUser) userModel.getNowAccount();
            String name = user.getUsername() + "_" + System.currentTimeMillis();
            final AVFile file = new AVFile(name, bs);
            file.saveInBackground(new SaveCallback() {
                @Override
                public void done(AVException e) {
                    if (e != null) {
                        call.fail(e);
                    } else {
                        String url = file.getUrl();
                        sendStatus(text, url, inboxType,call);
                    }
                }
            });
        } else {
            sendStatus(text, "", inboxType,call);
        }
    }

    @Override
    public void sendStatus(final String text, final String url, final String inboxType, final StatusCall call) {
        if (!userModel.isLogin()) return;
        final AVObject statusDetail = new AVObject(Status.STATUS_DETAIL);
        statusDetail.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e != null) {
                    call.fail(e);
                } else {
                    AVStatus status = new AVStatus();
                    status.setMessage(text);
                    status.setImageUrl(url);
                    Map<String, Object> datas = new HashMap<>();
                    datas.put(Status.STATUS_DETAIL, statusDetail);
                    status.setData(datas);
//            status.setInboxType("system");
//            AVStatus.sendPrivateStatusInBackgroud(status,"588f36f51b69e600596715c6",saveCallback);
//            status.sendInBackground(saveCallback);
                    AVStatus.sendStatusToFollowersInBackgroud(status, new SaveCallback() {
                        @Override
                        public void done(AVException e) {
                            if (e != null) {
                                call.fail(e);
                            } else {
                                call.sucess();
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public void sendPrivateStatus() {

    }

    @Override
    public void getStatus() {

    }

    @Override
    public Observable<List<Status>> getInbox(String inboxType){
        if (!userModel.isLogin()) return null;
        AVUser user = AVUser.getCurrentUser();
        final AVStatusQuery q = AVStatus.inboxQuery(user, AVStatus.INBOX_TYPE.TIMELINE.toString());
        q.orderByDescending(Status.CREATED_AT);
        q.include(Status.STATUS_DETAIL);
        return Observable.create(new Observable.OnSubscribe<List<AVStatus>>() {
            @Override
            public void call(Subscriber<? super List<AVStatus>> subscriber) {
                try {
                    subscriber.onNext(q.find());
                } catch (AVException e) {
                    subscriber.onError(e);
                }
            }
        }).flatMap(new Func1<List<AVStatus>, Observable<AVStatus>>() {
            @Override
            public Observable<AVStatus> call(List<AVStatus> avStatuses) {
                return Observable.from(avStatuses);
            }
        }).map(new Func1<AVStatus,Status>()  {
            @Override
            public Status call(AVStatus avStatus) {
                try {
                    return mapperStatus(avStatus);
                } catch (Exception e) {
                    throw Exceptions.propagate(e);
                }
            }
        }).toList().subscribeOn(Schedulers.io());
    }


    private Status mapperStatus(AVStatus avstatus) throws Exception{
//        AVObject statusdetil = avstatus.getAVObject(Status.STATUS_DETAIL);


        Map<String, Object> data = avstatus.getData();
        String detailId = (String) data.get(Status.DETAIL_ID);
        AVObject statusdetil = AVObject.createWithoutData(Status.STATUS_DETAIL, detailId);



        List<String> likes = (List<String>) statusdetil.get(Status.LIKES);
//        AVRelation<AVObject> relation = statusdetil.getRelation(Status.COMMENT);
//        AVQuery<AVObject> query = relation.getQuery();
//        List<AVObject> comments = query.find();
        List<AVObject> comments = null;
        List<Status.Comment> commentlist = mapperComment(comments);
        return new AutoValue_Status(avstatus.getObjectId(),
                (String) avstatus.get(Status.DETAIL_ID),
                avstatus.getInboxType(),
                avstatus.getSource().getUsername(),
                (String)avstatus.getSource().get(User.IMGURL),
                avstatus.getImageUrl(),
                avstatus.getSource().getObjectId(),
                avstatus.getMessage(),
                avstatus.getCreatedAt(),
                new AutoValue_Status_StatusDetil(commentlist,likes));
    }

    private List<Status.Comment> mapperComment(List<AVObject> comment){
        if (comment==null) return null;
        List<Status.Comment> commentlist = new ArrayList<>();
        for (AVObject a:comment){
            commentlist.add(new AutoValue_Status_Comment(a.getObjectId()
                    ,(String)a.get(Status.Comment.STATUSID)
                    ,(String)a.get(Status.Comment.SOURCE)
                    ,(String)a.get(Status.Comment.MESSAGE)
                    ,(String)a.get(Status.Comment.POSTIMG)
                    ,a.getCreatedAt()));
        }
        return commentlist;
    }


    @Override
    public void deleteStatus(Status s) {
        AVObject avstatus = AVObject.createWithoutData(Status.TABLE,s.objectId());
        AVObject detil = AVObject.createWithoutData(Status.STATUS_DETAIL,s.detailId());
        avstatus.deleteInBackground();
        detil.deleteInBackground();
    }

    @Override
    public void likeStatus() {
//        detail.put(App.LIKES, likes);
//        detail.saveInBackground(saveCallback);
    }

    @Override
    public Observable<List<Status.Comment>> getStatusComment() {
        return null;
    }



    @Override
    public Observable<List<User>> getFollowers(String userObjId) {
        final AVQuery<AVUser> q = AVUser.followerQuery(userObjId, AVUser.class);
        q.include(Status.FOLLOWER);
        return Observable.create(new Observable.OnSubscribe<List<AVUser>>() {
            @Override
            public void call(Subscriber<? super List<AVUser>> subscriber) {
                try {
                    subscriber.onNext(q.find());
                } catch (AVException e) {
                    subscriber.onError(e);
                }
            }
        }).flatMap(new Func1<List<AVUser>, Observable<AVUser>>() {
            @Override
            public Observable<AVUser> call(List<AVUser> avUsers) {
                return Observable.from(avUsers);
            }
        }).map(new Func1<AVUser, User>() {
            @Override
            public User call(AVUser avUser) {
                return userModel.mapperToUser(avUser);
            }
        }).toList().subscribeOn(Schedulers.io());
    }


    @Override
    public Observable<List<User>> getFollowings(String userObjId) {
        final AVQuery<AVUser> q = AVUser.followerQuery(userObjId, AVUser.class);
        q.include(Status.FOLLOWEE);
        return Observable.create(new Observable.OnSubscribe<List<AVUser>>() {
            @Override
            public void call(Subscriber<? super List<AVUser>> subscriber) {
                try {
                    subscriber.onNext(q.find());
                } catch (AVException e) {
                    subscriber.onError(e);
                }
            }
        }).flatMap(new Func1<List<AVUser>, Observable<AVUser>>() {
            @Override
            public Observable<AVUser> call(List<AVUser> avUsers) {
                return Observable.from(avUsers);
            }
        }).map(new Func1<AVUser, User>() {
            @Override
            public User call(AVUser avUser) {
                return userModel.mapperToUser(avUser);
            }
        }).toList().subscribeOn(Schedulers.io());
    }


    @Override
    public int getFollowStatus(Object user) {
        boolean isMyFollower = findFollowStatus((AVUser) user, true);
        boolean isMyFollowing = findFollowStatus((AVUser)user, false);
        if (isMyFollower && isMyFollowing) {
            return Status.MUTUAL_FOLLOW;
        } else if (isMyFollower) {
            return Status.ISMYFOLLOWER;
        } else if (isMyFollowing) {
            return Status.MYFOLLOWING;
        } else {
            return Status.NONE_FOLLOW;
        }
    }


    private boolean findFollowStatus(AVUser user, boolean askFollower){
        AVUser currentUser = AVUser.getCurrentUser();
        AVQuery<AVUser> q;
        if (askFollower) {
            q = AVUser.followerQuery(currentUser.getObjectId(), AVUser.class);
            q.whereEqualTo(Status.FOLLOWER, user);
        } else {
            q = AVUser.followeeQuery(currentUser.getObjectId(), AVUser.class);
            q.whereEqualTo(Status.FOLLOWEE, user);
        }
        q.setLimit(1);
        List<AVUser> avUsers = null;
        try {
            avUsers = q.find();
        } catch (AVException e) {
            e.printStackTrace();
        }
        return avUsers.isEmpty() == false;
    }


}
