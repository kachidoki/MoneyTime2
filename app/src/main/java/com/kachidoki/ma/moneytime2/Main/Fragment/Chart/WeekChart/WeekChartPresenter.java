package com.kachidoki.ma.moneytime2.Main.Fragment.Chart.WeekChart;

import com.kachidoki.ma.moneytime2.Model.Task.Source.TasksDataSource;
import com.kachidoki.ma.moneytime2.Model.Task.Task;
import com.kachidoki.ma.moneytime2.Utils.TimeTransform;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by mayiwei on 2017/2/20.
 */

public class WeekChartPresenter implements WeekChartContract.Presenter{
    private int Year=0,Month=0,Day=0,WeekOfYear=0,WeekDay=0;
    private int NowWeekofYear = 0;
    private WeekChartContract.View view;
    private TasksDataSource DataRepository;

    public WeekChartPresenter(WeekChartContract.View view,TasksDataSource dataSource){
        this.view = view;
        this.DataRepository = dataSource;
    }

    @Override
    public boolean isNowWeek() {
        return WeekOfYear==NowWeekofYear;
    }

    @Override
    public int getNowWeek() {
        return WeekOfYear;
    }

    @Override
    public void getWeekTasks() {
        DataRepository.getWeekTasks(Year+"",WeekOfYear+"")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Task>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Task> tasks) {
                        view.showWeekTasks(tasks);
                    }
                });
    }

    @Override
    public void nextWeek() {
        WeekOfYear++;
        view.setWeekText(WeekOfYear);
        getWeekTasks();
    }

    @Override
    public void previousWeek() {
        WeekOfYear--;
        view.setWeekText(WeekOfYear);
        getWeekTasks();
    }

    @Override
    public void start() {
        SetNowTime();
        view.setWeekText(WeekOfYear);
    }

    private void SetNowTime(){
        TimeTransform nowtime = new TimeTransform();
        Year = nowtime.getYear();
        Month = nowtime.getMonth();
        Day = nowtime.getDay();
        WeekDay = nowtime.getWeekDay();
        WeekOfYear = nowtime.getWeekOfYear();
        NowWeekofYear = nowtime.getWeekOfYear();
    }
}
