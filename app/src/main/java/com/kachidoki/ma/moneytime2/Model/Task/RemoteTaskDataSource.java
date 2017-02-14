package com.kachidoki.ma.moneytime2.Model.Task;

import android.support.annotation.NonNull;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.DeleteCallback;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.SaveCallback;
import com.kachidoki.ma.moneytime2.Model.Task.Source.TasksDataSource;
import com.kachidoki.ma.moneytime2.Model.User.UserSource;

import java.util.List;

import rx.Observable;

/**
 * Created by mayiwei on 2017/2/14.
 */

public class RemoteTaskDataSource implements TasksDataSource {

    private UserSource UserModel;

    public RemoteTaskDataSource(@NonNull UserSource userSource){
        this.UserModel = userSource;
    }

    @Override
    public Observable<List<Task>> getWeekTasks(String year, String weekOfYear) {
        return null;
    }

    @Override
    public Observable<List<Task>> getDayTask(String year, String month, String day) {
        return null;
    }

    @Override
    public void saveTask(String title, float startTime, float endTime, int year, int day, int month, int weekDay, int weekOfYear, int color, String description, boolean complete) {
        if (UserModel.isLogin()){
            AVObject task = new AVObject(Task.TABLE);
            task.put(Task.USER, ((AVUser)UserModel.getNowAccount()).getObjectId());
            task.put(Task.TITLE, title);
            task.put(Task.STARTTIME, startTime);
            task.put(Task.ENDTIME, endTime);
            task.put(Task.YEAR, year);
            task.put(Task.DAY, day);
            task.put(Task.MONTH, month);
            task.put(Task.WEEKDAY, weekDay);
            task.put(Task.WEEKOFYEAR, weekOfYear);
            task.put(Task.COLOR, color);
            task.put(Task.DESCRIPTION, description);
            task.put(Task.COMPLETE, complete);
            task.saveInBackground(new SaveCallback() {
                @Override
                public void done(AVException e) {
                    if (e == null) {
                        // 存储成功
                    } else {
                        // 失败的话，请检查网络环境以及 SDK 配置是否正确
                    }
                }
            });
        }
    }

    @Override
    public void doneTask(String year, String day, String month, String startTime, String endTime) {
        if (UserModel.isLogin()){
            AVQuery<AVObject> query = new AVQuery<>(Task.TABLE);
            query.whereEqualTo(Task.USER,((AVUser)UserModel.getNowAccount()).getObjectId());
            query.whereEqualTo(Task.YEAR,year);
            query.whereEqualTo(Task.DAY,day);
            query.whereEqualTo(Task.MONTH,month);
            query.whereEqualTo(Task.STARTTIME,startTime);
            query.whereEqualTo(Task.ENDTIME,endTime);
            query.findInBackground(new FindCallback<AVObject>() {
                @Override
                public void done(List<AVObject> list, AVException e) {
                    if (!list.isEmpty()||list!=null){
                        for (AVObject task:list){
                            task.put(Task.COMPLETE,true);
                            task.saveInBackground();
                        }
                    }
                }
            });
        }
    }

    @Override
    public void sync(boolean delete, boolean download) {

    }

    @Override
    public void deleteAllTasks() {
        if (UserModel.isLogin()){
            AVQuery<AVObject> query = new AVQuery<>(Task.TABLE);
            query.whereEqualTo(Task.USER,((AVUser)UserModel.getNowAccount()).getObjectId());
            query.findInBackground(new FindCallback<AVObject>() {
                @Override
                public void done(List<AVObject> list, AVException e) {
                    if (!list.isEmpty()||list!=null){
                        AVObject.deleteAllInBackground(list, new DeleteCallback() {
                            @Override
                            public void done(AVException e) {

                            }
                        });
                    }
                }
            });
        }
    }

    @Override
    public void deleteTask(String year, String day, String month, String startTime, String endTime) {
        if (UserModel.isLogin()){
            AVQuery<AVObject> query = new AVQuery<>(Task.TABLE);
            query.whereEqualTo(Task.USER,((AVUser)UserModel.getNowAccount()).getObjectId());
            query.whereEqualTo(Task.YEAR,year);
            query.whereEqualTo(Task.DAY,day);
            query.whereEqualTo(Task.MONTH,month);
            query.whereEqualTo(Task.STARTTIME,startTime);
            query.whereEqualTo(Task.ENDTIME,endTime);
            query.findInBackground(new FindCallback<AVObject>() {
                @Override
                public void done(List<AVObject> list, AVException e) {
                    if (!list.isEmpty()||list!=null){
                        for (AVObject task:list){
                            task.deleteInBackground();
                        }
                    }
                }
            });
        }
    }

    @Override
    public void refresh() {

    }
}
