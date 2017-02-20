package com.kachidoki.ma.moneytime2.Main.Fragment.Chart.DayChart;

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
 * Created by mayiwei on 2017/2/17.
 */

public class DayChartPresenter implements DayChartContract.Presenter {

    private int Year=0,Month=0,Day=0,WeekOfYear=0,WeekDay=0;
    final Calendar currentTime = Calendar.getInstance();
    private DayChartContract.View view;
    private TasksDataSource DataRepositorys;


    public DayChartPresenter(DayChartContract.View view, TasksDataSource dataSource){
        this.view = view;
        this.DataRepositorys = dataSource;
    }


    @Override
    public void start() {
        SetChinaTime(currentTime);
        view.setDateText(Year,Month,Day);
    }

    @Override
    public void setDataTime(int year, int month, int day) {
        Calendar cal = new GregorianCalendar(Locale.CHINA);
        Date date = new GregorianCalendar(year,month,day).getTime();
        cal.setTime(date);
        Year = cal.get(Calendar.YEAR);
        Month = cal.get(Calendar.MONTH)+1;
        Day = cal.get(Calendar.DAY_OF_MONTH);
        WeekDay = cal.get(Calendar.DAY_OF_WEEK);
        WeekOfYear = cal.get(Calendar.WEEK_OF_YEAR);
        view.setDateText(year,month,day);
    }

    @Override
    public Calendar getCurrentDate() {
        return currentTime;
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
