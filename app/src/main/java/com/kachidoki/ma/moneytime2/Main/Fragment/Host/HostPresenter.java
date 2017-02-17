package com.kachidoki.ma.moneytime2.Main.Fragment.Host;

import android.util.Log;

import com.kachidoki.ma.moneytime2.Model.Task.FakeData;

/**
 * Created by mayiwei on 2017/2/16.
 */

public class HostPresenter implements HostContract.Presenter {

    private HostContract.View view;

    public HostPresenter(HostContract.View view){
        this.view = view;
    }

    @Override
    public void start() {
        view.setTask(FakeData.getTask());
    }

    @Override
    public void loadTasks() {

    }

    @Override
    public void refresh() {

    }
}
