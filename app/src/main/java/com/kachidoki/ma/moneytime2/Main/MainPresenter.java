package com.kachidoki.ma.moneytime2.Main;

/**
 * Created by mayiwei on 2017/2/16.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    public MainPresenter(MainContract.View view){
        this.view = view;
    }

    @Override
    public void start() {

    }
}
