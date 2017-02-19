package com.kachidoki.ma.moneytime2.Main.Fragment.Chart.DayChart.Di;

import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.DayChart.DayChartContract;

import dagger.Module;

/**
 * Created by mayiwei on 2017/2/17.
 */
@Module
public class DayChartModule {

    private DayChartContract.View view;

    public DayChartModule(DayChartContract.View view){
        this.view = view;
    }

}
