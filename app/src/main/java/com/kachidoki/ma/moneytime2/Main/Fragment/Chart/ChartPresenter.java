package com.kachidoki.ma.moneytime2.Main.Fragment.Chart;

import com.kachidoki.ma.moneytime2.App.Base.RBasePresenter;

/**
 * Created by mayiwei on 2017/2/16.
 */

public class ChartPresenter extends RBasePresenter implements ChartContract.Presenter {
    private ChartContract.View view;

    public  ChartPresenter(ChartContract.View view){
        this.view = view;
    }

    @Override
    public void start() {

    }
}
