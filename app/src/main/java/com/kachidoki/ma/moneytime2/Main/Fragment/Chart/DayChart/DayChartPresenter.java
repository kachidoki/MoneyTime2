package com.kachidoki.ma.moneytime2.Main.Fragment.Chart.DayChart;


import com.kachidoki.ma.moneytime2.Model.Task.Source.TasksDataSource;
import com.kachidoki.ma.moneytime2.Model.Task.Task;
import com.kachidoki.ma.moneytime2.Utils.TimeTransform;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by mayiwei on 2017/2/17.
 */

public class DayChartPresenter implements DayChartContract.Presenter {

    private int Year=0,Month=0,Day=0;
    private DayChartContract.View view;
    private TasksDataSource DataRepositorys;


    public DayChartPresenter(DayChartContract.View view, TasksDataSource dataSource){
        this.view = view;
        this.DataRepositorys = dataSource;
    }


    @Override
    public void start() {
        SetChinaTime();
        view.setDateText(Year,Month,Day);
    }

    @Override
    public void setDataTime(int year, int month, int day) {
       TimeTransform timeTransform = new TimeTransform(year,month,day);
        Year = timeTransform.getYear();
        Month = timeTransform.getMonth();
        Day = timeTransform.getDay();
        view.setDateText(Year,Month,Day);
        getDayTasks();
    }

    @Override
    public TimeTransform getCurrentDate() {
        return new TimeTransform(Year,Month-1,Day);
    }

    @Override
    public void getDayTasks() {
        DataRepositorys.getDayTask(Year+"",Month+"",Day+"")
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Task>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Task> tasks) {
                        view.showDayTasks(tasks);
                    }
                });

    }


    private void SetChinaTime(){
        TimeTransform nowtime = new TimeTransform();
        Year = nowtime.getYear();
        Month = nowtime.getMonth();
        Day = nowtime.getDay();
    }
}
