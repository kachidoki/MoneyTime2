package com.kachidoki.ma.moneytime2.Main.Fragment.Me;

import com.kachidoki.ma.moneytime2.App.AppConstant;
import com.kachidoki.ma.moneytime2.Model.Status.Status;
import com.kachidoki.ma.moneytime2.Model.Status.StatusSource;
import com.kachidoki.ma.moneytime2.Model.User.User;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by mayiwei on 2017/2/16.
 */

public class MePresenter implements MeContract.Presenter {
    private MeContract.View view;
    private StatusSource statusSource;

    public MePresenter(MeContract.View view,StatusSource statusSource){
        this.view = view;
        this.statusSource = statusSource;
    }


    @Override
    public void start() {
        if (statusSource.checkIsLogin()){
            loadStatusNum();
            loadFollowerNum();
            loadFollowingNum();
        }else {
            view.showNotLogin();
        }
    }



    @Override
    public boolean checkIsLogin() {
        return statusSource.checkIsLogin();
    }

    @Override
    public void OnUserImgClick() {
        if (statusSource.checkIsLogin()){
            view.goToConfig();
        }else {
            view.gotoLogin();
        }
    }


    private void loadStatusNum() {
        statusSource.getInbox(AppConstant.INBOX_TIMELINE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Status>>() {
                    @Override
                    public void call(List<Status> statuses) {
                        if (statuses!=null) view.setStatusNum(statuses.size());
                    }
                });
    }

    private void loadFollowerNum() {
        statusSource.getFollowers(statusSource.getNowUserID())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<User>>() {
                    @Override
                    public void call(List<User> users) {
                        if (users!=null) view.setFollowerNum(users.size());
                    }
                });
    }

    private void loadFollowingNum() {
        statusSource.getFollowings(statusSource.getNowUserID())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<User>>() {
                    @Override
                    public void call(List<User> users) {
                        if (users!=null) view.setFollowerNum(users.size());
                    }
                });
    }

}
