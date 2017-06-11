package com.kachidoki.ma.moneytime2.Main;

import com.kachidoki.ma.moneytime2.App.Base.RBasePresenter;

/**
 * Created by mayiwei on 2017/2/16.
 */

public class MainPresenter extends RBasePresenter implements MainContract.Presenter {

    private MainContract.View view;
    public MainPresenter(MainContract.View view){
        this.view = view;
    }

    @Override
    public void start() {

    }
}
