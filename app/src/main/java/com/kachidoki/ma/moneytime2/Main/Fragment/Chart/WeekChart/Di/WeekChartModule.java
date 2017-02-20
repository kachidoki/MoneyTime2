package com.kachidoki.ma.moneytime2.Main.Fragment.Chart.WeekChart.Di;

import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.DayChart.Di.ForFragmentSub;
import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.WeekChart.WeekChartContract;
import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.WeekChart.WeekChartPresenter;
import com.kachidoki.ma.moneytime2.Model.Task.Source.TasksDataSource;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mayiwei on 2017/2/18.
 */
@Module
public class WeekChartModule {

    private WeekChartContract.View view;

    public WeekChartModule(WeekChartContract.View view){
        this.view =view;
    }

    @ForFragmentSub
    @Provides
    WeekChartContract.Presenter providesPresenter(TasksDataSource tasksDataSource){
        return new WeekChartPresenter(view,tasksDataSource);
    }

}
