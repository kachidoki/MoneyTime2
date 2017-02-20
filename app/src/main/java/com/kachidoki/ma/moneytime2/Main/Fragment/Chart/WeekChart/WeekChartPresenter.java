package com.kachidoki.ma.moneytime2.Main.Fragment.Chart.WeekChart;

import com.kachidoki.ma.moneytime2.Model.Task.Source.TasksDataSource;
import com.kachidoki.ma.moneytime2.Model.Task.Task;

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
    final Calendar currentTime = Calendar.getInstance();
    private WeekChartContract.View view;
    private TasksDataSource DataRepository;

    public WeekChartPresenter(WeekChartContract.View view,TasksDataSource dataSource){
        this.view = view;
        this.DataRepository = dataSource;
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
    }

    @Override
    public void previousWeek() {
        WeekOfYear--;
    }

    @Override
    public void start() {
        SetChinaTime(currentTime);
        view.setWeekText(WeekOfYear);
    }

    private void SetChinaTime(Calendar c){
        Calendar cal = new GregorianCalendar(Locale.CHINA);
        Date date = new GregorianCalendar(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)).getTime();
        cal.setTime(date);
        Year = cal.get(Calendar.YEAR);
        Month = cal.get(Calendar.MONTH)+1;
        Day = cal.get(Calendar.DAY_OF_MONTH);
        WeekDay = cal.get(Calendar.DAY_OF_WEEK);
        WeekOfYear = cal.get(Calendar.WEEK_OF_YEAR);
    }
}
