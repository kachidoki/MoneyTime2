package com.kachidoki.ma.moneytime2.Main.Fragment.Chart.DayChart.Di;

import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.DayChart.DayChartContract;
import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.DayChart.DayChartPresenter;
import com.kachidoki.ma.moneytime2.Model.Task.Source.TasksDataSource;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mayiwei on 2017/2/17.
 */
@Module
public class DayChartModule {

    private DayChartContract.View view;

    public DayChartModule(DayChartContract.View view){
        this.view = view;
    }

    @ForFragmentSub
    @Provides
    DayChartContract.Presenter providesPresenter(TasksDataSource dataSource){
        return new DayChartPresenter(view,dataSource);
    }

}
