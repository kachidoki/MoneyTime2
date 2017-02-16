package com.kachidoki.ma.moneytime2.Main.Fragment.Me;

/**
 * Created by mayiwei on 2017/2/16.
 */

public class MePresenter implements MeContract.Presenter {
    private MeContract.View view;

    public MePresenter(MeContract.View view){
        this.view = view;
    }


    @Override
    public void start() {

    }
}
