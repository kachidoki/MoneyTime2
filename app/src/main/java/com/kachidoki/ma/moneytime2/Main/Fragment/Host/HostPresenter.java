package com.kachidoki.ma.moneytime2.Main.Fragment.Host;


import com.kachidoki.ma.moneytime2.Model.Task.FakeData;
import com.kachidoki.ma.moneytime2.Model.Task.Source.TasksDataSource;
import com.kachidoki.ma.moneytime2.Model.Task.Task;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by mayiwei on 2017/2/16.
 */

public class HostPresenter implements HostContract.Presenter {

    private int Year=0,Month=0,Day=0,WeekOfYear=0,WeekDay=0;
    final Calendar currentTime = Calendar.getInstance();
    private HostContract.View view;
    private TasksDataSource tasksRepository;

    public HostPresenter(HostContract.View view, TasksDataSource tasksDataSource){
        this.view = view;
        this.tasksRepository = tasksDataSource;
    }

    @Override
    public void start() {
        SetChinaTime(currentTime);
    }

    @Override
    public void loadTasks() {
        tasksRepository.getWeekTasks(Year+"",WeekOfYear+"")
                .doOnNext(new Action1<List<Task>>() {
                    @Override
                    public void call(List<Task> tasks) {
                        Collections.reverse(tasks);
                    }
                })
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
                        view.setTask(tasks);
                        view.showTask();
                    }
                });
    }

    @Override
    public void refresh() {
        tasksRepository.getWeekTasks(Year+"",WeekOfYear+"")
                .doOnNext(new Action1<List<Task>>() {
                    @Override
                    public void call(List<Task> tasks) {
                        Collections.reverse(tasks);
                    }
                })
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
                        view.setTask(tasks);
                        view.showTask();
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
