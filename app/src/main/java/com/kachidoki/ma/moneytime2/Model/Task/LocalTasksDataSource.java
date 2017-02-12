package com.kachidoki.ma.moneytime2.Model.Task;

import android.content.Context;
import android.support.annotation.NonNull;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by mayiwei on 2017/2/12.
 */

public class LocalTasksDataSource implements TasksDataSource {


    @NonNull
    private final BriteDatabase mDatabaseHelper;

    private LocalTasksDataSource(@NonNull Context context) {
        DBOpenHelper dbHelper = new DBOpenHelper(context);
        SqlBrite sqlBrite = new SqlBrite.Builder().build();
        mDatabaseHelper = sqlBrite.wrapDatabaseHelper(dbHelper, Schedulers.io());
    }


    @Override
    public Observable<List<Task>> getTasks() {
        return null;
    }

    @Override
    public Observable<Task> getTask() {
        return null;
    }

    @Override
    public void saveTask(@NonNull Task task) {

    }

    @Override
    public void doneTask(@NonNull Task task) {

    }

    @Override
    public void sync(boolean delete, boolean download) {

    }

    @Override
    public void deleteAllTasks() {

    }

    @Override
    public void deleteTask(@NonNull Task task) {

    }
}
