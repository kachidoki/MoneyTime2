package com.kachidoki.ma.moneytime2.Main.Fragment.Me;


import com.kachidoki.ma.moneytime2.App.Base.RBasePresenter;
import com.kachidoki.ma.moneytime2.Model.Status.Status;
import com.kachidoki.ma.moneytime2.Model.Status.StatusSource;
import com.kachidoki.ma.moneytime2.Model.User.User;
import com.kachidoki.ma.moneytime2.Model.User.UserSource;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by mayiwei on 2017/2/16.
 */

public class MePresenter extends RBasePresenter implements MeContract.Presenter {
    private MeContract.View view;
    private StatusSource statusSource;
    private UserSource userSource;

    public MePresenter(MeContract.View view,StatusSource statusSource,UserSource userSource){
        this.view = view;
        this.statusSource = statusSource;
        this.userSource = userSource;
    }


    @Override
    public void start() {
        if (statusSource.checkIsLogin()){
            loadLoginUser();
            view.showLogin();
        }else {
            view.showNotLogin();
        }
    }


    @Override
    public void loadLoginUser() {
        loadStatusNum();
        view.setUser(userSource.getNowUser());
    }

    @Override
    public void OnUserImgClick() {
        if (statusSource.checkIsLogin()){
            view.goToConfig();
        }else {
            view.gotoLogin();
        }
    }

    @Override
    public void logout() {
        userSource.LogOut();
        view.showLogout();
    }


    private void loadStatusNum() {
        addSubScription(statusSource.getInbox(Status.INBOX_TIMELINE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Status>>() {
                    @Override
                    public void call(List<Status> statuses) {
                        if (statuses!=null) view.setStatusNum(statuses.size());
                        loadFollowerNum();
                    }
                }));
    }

    private void loadFollowerNum() {
        addSubScription(statusSource.getFollowers(statusSource.getNowUserID())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<User>>() {
                    @Override
                    public void call(List<User> users) {
                        if (users!=null) view.setFollowerNum(users.size());
                        loadFollowingNum();
                    }
                }));
    }

    private void loadFollowingNum() {
        addSubScription(statusSource.getFollowings(statusSource.getNowUserID())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<User>>() {
                    @Override
                    public void call(List<User> users) {
                        if (users!=null) view.setFollowerNum(users.size());
                    }
                }));
    }

}
