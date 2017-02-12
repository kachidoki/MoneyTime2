package com.kachidoki.ma.moneytime2.Model.Task;

import android.support.annotation.NonNull;

import java.util.List;

import rx.Observable;

/**
 * Created by mayiwei on 2017/2/12.
 */

public interface TasksDataSource {

    Observable<List<Task>> getTasks();

    Observable<Task> getTask();

    void saveTask(@NonNull Task task);

    void doneTask(@NonNull Task task);

    void sync(boolean delete,boolean download);

    void deleteAllTasks();

    void deleteTask(@NonNull Task task);

}
