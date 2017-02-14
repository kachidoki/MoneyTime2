package com.kachidoki.ma.moneytime2.Model.Task.Source;


import com.kachidoki.ma.moneytime2.Model.Task.Task;

import java.util.List;

import rx.Observable;

/**
 * Created by mayiwei on 2017/2/12.
 */

public interface TasksDataSource {

    Observable<List<Task>> getWeekTasks(String year, String weekOfYear);

    Observable<List<Task>> getDayTask(String year,String month,String day);

    void saveTask(String title, float startTime, float endTime, int year, int day, int month, int weekDay, int weekOfYear, int color, String description, boolean complete);

    void doneTask(String year,String day,String month,String startTime, String endTime);

    void sync(boolean delete,boolean download);

    void deleteAllTasks();

    void deleteTask(String year,String day,String month,String startTime, String endTime);

    void refresh();

}
