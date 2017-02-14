package com.kachidoki.ma.moneytime2.Model.Task;

import android.support.annotation.NonNull;

import com.kachidoki.ma.moneytime2.Model.Task.Source.TasksDataSource;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by mayiwei on 2017/2/14.
 */

public class TasksRepository implements TasksDataSource {

    @NonNull
    private final TasksDataSource mTasksRemoteDataSource;

    @NonNull
    private final TasksDataSource mTasksLocalDataSource;

    Map<String, Task> mCachedTasks;

    boolean mCacheIsDirty = false;

    public TasksRepository(@NonNull TasksDataSource tasksRemoteDataSource,
                           @NonNull TasksDataSource tasksLocalDataSource){
        this.mTasksLocalDataSource = tasksLocalDataSource;
        this.mTasksRemoteDataSource = tasksRemoteDataSource;
    }



    @Override
    public Observable<List<Task>> getWeekTasks(String year, String weekOfYear) {
        if (mCachedTasks == null) {
            mCachedTasks = new LinkedHashMap<>();
        }
        Observable<List<Task>> localTasks = mTasksLocalDataSource.getWeekTasks(year,weekOfYear)
        .flatMap(new Func1<List<Task>, Observable<List<Task>>>() {
            @Override
            public Observable<List<Task>> call(List<Task> tasks) {
                return Observable.from(tasks)
                        .doOnNext(new Action1<Task>() {
                            @Override
                            public void call(Task task) {
                                putToCache(task);
                            }
                        })
                        .toList();
            }
        });
        if (mCacheIsDirty){
            return localTasks;
        } else {
            return Observable.concat(Observable.from(mCachedTasks.values()).toList(), localTasks)
                    .filter(new Func1<List<Task>, Boolean>() {
                        @Override
                        public Boolean call(List<Task> tasks) {
                            return !tasks.isEmpty();
                        }
                    })
                    .first();
        }
    }


    private void putToCache(Task task){
        mCachedTasks.put(task.year()+task.month()+task.day()+task.startTime()+""+task.endTime(),task);

    }



    @Override
    public Observable<List<Task>> getDayTask(String year, String month, String day) {
        if (mCachedTasks == null) {
            mCachedTasks = new LinkedHashMap<>();
        }
        Observable<List<Task>> localTasks = mTasksLocalDataSource.getDayTask(year,month,day)
                .flatMap(new Func1<List<Task>, Observable<List<Task>>>() {
                    @Override
                    public Observable<List<Task>> call(List<Task> tasks) {
                        return Observable.from(tasks)
                                .doOnNext(new Action1<Task>() {
                                    @Override
                                    public void call(Task task) {
                                        putToCache(task);
                                    }
                                })
                                .toList();
                    }
                });
        if (mCacheIsDirty){
            return localTasks;
        } else {
            return Observable.concat(Observable.from(mCachedTasks.values()).toList(), localTasks)
                    .filter(new Func1<List<Task>, Boolean>() {
                        @Override
                        public Boolean call(List<Task> tasks) {
                            return !tasks.isEmpty();
                        }
                    })
                    .first();
        }
    }

    @Override
    public void saveTask(String title, float startTime, float endTime, int year, int day, int month, int weekDay, int weekOfYear, int color, String description, boolean complete) {
        mTasksLocalDataSource.saveTask(title,startTime,endTime,year,day,month,weekDay, weekOfYear,color,description,complete);
        mTasksRemoteDataSource.saveTask(title,startTime,endTime,year,day,month,weekDay, weekOfYear,color,description,complete);

        if (mCachedTasks == null) {
            mCachedTasks = new LinkedHashMap<>();
        }
        putToCache(new AutoValue_Task(title,startTime,endTime,year,day,month,weekDay, weekOfYear,color,description,complete));
    }

    @Override
    public void doneTask(String year, String day, String month, String startTime, String endTime) {
        mTasksLocalDataSource.doneTask(year,day,month,startTime,endTime);
        mTasksRemoteDataSource.doneTask(year,day,month,startTime,endTime);
        mCacheIsDirty = true;
    }

    @Override
    public void sync(boolean delete, boolean download) {

    }

    @Override
    public void deleteAllTasks() {

    }

    @Override
    public void deleteTask(String year, String day, String month, String startTime, String endTime) {
        mTasksRemoteDataSource.deleteTask(year,day,month,startTime,endTime);
        mTasksLocalDataSource.deleteTask(year,day,month,startTime,endTime);
        mCacheIsDirty = true;
    }

    @Override
    public void refresh() {
        mCacheIsDirty = true;
    }
}
