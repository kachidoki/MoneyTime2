package com.kachidoki.ma.moneytime2.Main.Fragment.Community;

import android.util.Log;

import com.kachidoki.ma.moneytime2.App.Base.RBasePresenter;
import com.kachidoki.ma.moneytime2.Model.Status.Status;
import com.kachidoki.ma.moneytime2.Model.Status.StatusSource;
import com.kachidoki.ma.moneytime2.Model.User.UserSource;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by mayiwei on 2017/2/16.
 */

public class CommunityPresenter extends RBasePresenter implements CommunityContract.Presenter {
    private CommunityContract.View view;
    private StatusSource statusSource;
    private UserSource userSource;

    public CommunityPresenter(CommunityContract.View view,StatusSource statusSource,UserSource userSource){
        this.view = view;
        this.statusSource = statusSource;
        this.userSource = userSource;
    }


    @Override
    public void start() {
        loadUser();
        loadStatus();
    }

    @Override
    public void loadUser() {
        view.setUser(userSource.getNowUser());
    }

    @Override
    public void loadStatus() {
        view.showLoading();
        addSubScription(
                statusSource.getStatus()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Status>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Status> statuses) {
                        view.setTask(statuses);
                    }
                }));
    }

    @Override
    public void refresh() {
        loadStatus();
    }



}
