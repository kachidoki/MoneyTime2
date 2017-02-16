package com.kachidoki.ma.moneytime2.Main.Fragment.Chart;

/**
 * Created by mayiwei on 2017/2/16.
 */

public class ChartPresenter implements ChartContract.Presenter {
    private ChartContract.View view;

    public  ChartPresenter(ChartContract.View view){
        this.view = view;
    }

    @Override
    public void start() {

    }
}
