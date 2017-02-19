package com.kachidoki.ma.moneytime2.Model.Task;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.kachidoki.ma.moneytime2.Model.Task.Source.DBOpenHelper;
import com.kachidoki.ma.moneytime2.Model.Task.Source.Db;
import com.kachidoki.ma.moneytime2.Model.Task.Source.TasksDataSource;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by mayiwei on 2017/2/12.
 */

public class LocalTasksDataSource implements TasksDataSource {

    private static final String WEEK_QUERY =
            "SELECT " + "*" + " FROM " + Task.TABLE
          + " WHERE " + Task.YEAR + " = ?"
          + " AND " + Task.WEEKOFYEAR + " = ?";

    private static final String DAY_QUERY =
            "SELECT " + "*" + " FROM " + Task.TABLE
          + " WHERE " + Task.YEAR + " = ?"
          + " AND " + Task.MONTH + " = ?"
          + " AND " + Task.DAY + " = ?";

    public static final Func1<Cursor, Task> MAPPER = new Func1<Cursor, Task>() {
        @Override public Task call(Cursor cursor) {
            String title = Db.getString(cursor,Task.TITLE);
            float starttime = Db.getFloat(cursor,Task.STARTTIME);
            float endtime = Db.getFloat(cursor,Task.ENDTIME);
            int year = Db.getInt(cursor,Task.YEAR);
            int month = Db.getInt(cursor,Task.MONTH);
            int day = Db.getInt(cursor,Task.DAY);
            int weekofyear = Db.getInt(cursor,Task.WEEKOFYEAR);
            int weekday = Db.getInt(cursor,Task.WEEKDAY);
            int color = Db.getInt(cursor,Task.COLOR);
            String description = Db.getString(cursor, Task.DESCRIPTION);
            boolean complete = Db.getBoolean(cursor, Task.COMPLETE);
            return new AutoValue_Task(title,starttime,endtime,year,day,month,weekday,weekofyear,color,description,complete);
        }
    };

    @NonNull
    private final BriteDatabase mDatabaseHelper;

    public LocalTasksDataSource(@NonNull Context context) {
        DBOpenHelper dbHelper = new DBOpenHelper(context);
        SqlBrite sqlBrite = new SqlBrite.Builder().build();
        mDatabaseHelper = sqlBrite.wrapDatabaseHelper(dbHelper, Schedulers.io());
    }


    @Override
    public Observable<List<Task>> getWeekTasks(String year, String weekOfYear) {
        return mDatabaseHelper.createQuery(Task.TABLE,WEEK_QUERY,year,weekOfYear)
                .mapToList(MAPPER);
    }

    @Override
    public Observable<List<Task>> getDayTask(String year, String month, String day) {
        return mDatabaseHelper.createQuery(Task.TABLE,DAY_QUERY,year,month,day)
                .mapToList(MAPPER);
    }

    @Override
    public void saveTask(String title, float startTime, float endTime, int year, int day, int month, int weekDay, int weekOfYear, int color, String description, boolean complete) {
        mDatabaseHelper.insert(Task.TABLE
                , new Task.Builder()
                        .title(title)
                        .startTime(startTime)
                        .endTime(endTime)
                        .year(year)
                        .day(day)
                        .month(month)
                        .weekDay(weekDay)
                        .weekOfYear(weekOfYear)
                        .description(description)
                        .color(color)
                        .complete(complete)
                        .build());
    }

    @Override
    public void doneTask(String year,String day,String month,String startTime, String endTime) {
        mDatabaseHelper.update(Task.TABLE, new Task.Builder().complete(true).build(),
                        Task.YEAR + " = ?"
                        + " AND " + Task.MONTH + " = ?"
                        + " AND " + Task.DAY + " = ?"
                        + " AND " + Task.STARTTIME + " = ?"
                        + " AND " + Task.ENDTIME + " = ?",year,month,day,startTime,endTime);
    }

    @Override
    public void sync(boolean delete, boolean download) {

    }

    @Override
    public void deleteAllTasks() {

    }

    @Override
    public void deleteTask(String year,String day,String month,String startTime, String endTime) {
        mDatabaseHelper.delete(Task.TABLE,
                Task.YEAR + " = ?"
                        + " AND " + Task.MONTH + " = ?"
                        + " AND " + Task.DAY + " = ?"
                        + " AND " + Task.STARTTIME + " = ?"
                        + " AND " + Task.ENDTIME + " = ?",year,month,day,startTime,endTime);
    }

    @Override
    public void refresh() {

    }
}
