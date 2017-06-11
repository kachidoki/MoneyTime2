package com.kachidoki.ma.moneytime2.Main.Fragment.Host;


import android.util.Log;

import com.kachidoki.ma.moneytime2.App.Base.RBasePresenter;
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

/**
 * Created by mayiwei on 2017/2/16.
 */

public class HostPresenter extends RBasePresenter implements HostContract.Presenter {

    private int Year=0,Month=0,Day=0,WeekOfYear=0,WeekDay=0;
    final Calendar currentTime = Calendar.getInstance();
    private HostContract.View view;
    private TasksDataSource tasksRepository;

    private Subscriber showTasks = new Subscriber<List<Task>>() {
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
    };

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
        addSubScription(
                tasksRepository.getWeekTasks(Year+"",WeekOfYear+"")
                .doOnNext(new Action1<List<Task>>() {
                    @Override
                    public void call(List<Task> tasks) {
                        Collections.reverse(tasks);
                        for (int i=1;i<tasks.size();i++){
                            if (tasks.get(i).day()!=tasks.get(i-1).day()||tasks.get(i).month()!=tasks.get(i-1).month()){
                                tasks.add(i-1,Task.createTask("",0,0,tasks.get(i).year(),tasks.get(i).day(),tasks.get(i).month(),
                                        tasks.get(i).weekDay(),tasks.get(i).weekOfYear(),Task.BLACK,"",false));
                                i++;
                            }
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(showTasks));
    }

    @Override
    public void refresh() {
        addSubScription(
                tasksRepository.getWeekTasks(Year+"",WeekOfYear+"")
                .doOnNext(new Action1<List<Task>>() {
                    @Override
                    public void call(List<Task> tasks) {
                        Collections.reverse(tasks);
                        for (int i=1;i<tasks.size();i++){
                            if (tasks.get(i).day()!=tasks.get(i-1).day()||tasks.get(i).month()!=tasks.get(i-1).month()){
                                tasks.add(i-1,Task.createTask("",0,0,tasks.get(i).year(),tasks.get(i).day(),tasks.get(i).month(),
                                        tasks.get(i).weekDay(),tasks.get(i).weekOfYear(),Task.BLACK,"",false));
                                i++;
                            }
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(showTasks));
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
