package com.kachidoki.ma.moneytime2.Main.Fragment.Community;

import android.util.Log;

import com.kachidoki.ma.moneytime2.Model.Status.Status;
import com.kachidoki.ma.moneytime2.Model.Status.StatusSource;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by mayiwei on 2017/2/16.
 */

public class CommunityPresenter implements CommunityContract.Presenter {
    private CommunityContract.View view;
    private StatusSource statusSource;

    public CommunityPresenter(CommunityContract.View view,StatusSource statusSource){
        this.view = view;
        this.statusSource = statusSource;
    }


    @Override
    public void start() {
        loadStatus();
    }

    @Override
    public void loadStatus() {
        statusSource.getStatus()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Status>>() {
                    @Override
                    public void onCompleted() {
                        Log.e("Test","onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Status> statuses) {

                    }
                });
    }

    @Override
    public void refresh() {

    }
}
