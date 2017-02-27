package com.kachidoki.ma.moneytime2.Add;

import android.util.Log;

import com.kachidoki.ma.moneytime2.Model.Task.Source.TasksDataSource;
import com.kachidoki.ma.moneytime2.Model.Task.Task;
import com.kachidoki.ma.moneytime2.Utils.TimeTransform;

import java.util.Calendar;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by mayiwei on 2017/2/17.
 */

public class AddPresenter implements AddContract.Presenter {


    private int Year=0,Month=0,Day=0,WeekOfYear=0,WeekDay=0;
    final Calendar currentTime = Calendar.getInstance();
    private AddContract.View view;
    private TasksDataSource dataRespository;

    public AddPresenter(AddContract.View view, TasksDataSource dataSource){
        this.view = view;
        this.dataRespository = dataSource;
    }

    @Override
    public void start() {
        SetNowTime();
        view.setDateText(Year,Month,Day);
    }


    @Override
    public void saveTask(Task task) {
        dataRespository.saveTask(task.title(),task.startTime(),task.endTime(),task.year(),task.day(),task.month(),task.weekDay(),task.weekOfYear(),task.color(),task.description(),task.complete());
        view.saveIsOk();
    }

    @Override
    public Task checkInput(String title, float startTime, float endTime, int color, String description, boolean complete) {
        if(title.isEmpty()){
            view.showNoTitle();
            return null;
        }else {
            if (startTime==endTime){
                view.showTimeIsNotOk();
                return null;
            }else {
                if(color==-1){
                    view.showNoColor();
                    return null;
                }else {
                    if (isDouble(startTime,endTime)){
                        view.showTimeIsNotOk();
                        return null;
                    }else {
                        return Task.createTask(title,startTime,endTime,Year,Day,Month,WeekDay,WeekOfYear,color,description,complete);
                    }
                }

            }
        }
    }

    @Override
    public void setDateTime(int year, int month, int day) {
        TimeTransform timeTransform = new TimeTransform(year,month,day);
        Year = timeTransform.getYear();
        Month = timeTransform.getMonth();
        Day = timeTransform.getDay();
        WeekDay = timeTransform.getWeekDay();
        WeekOfYear = timeTransform.getWeekOfYear();
        view.setDateText(Year,Month,Day);
    }

    @Override
    public Calendar getCurrentDate() {
        return currentTime;
    }


    private boolean isDouble(final float starttime, final float endtime){
        return dataRespository.getDayTask(Year+"",Month+"",Day+"")
                .map(new Func1<List<Task>, Boolean>() {
                    @Override
                    public Boolean call(List<Task> tasks) {
                        if (tasks!=null){
                            for (Task task:tasks){
                                if((starttime<task.endTime()&&starttime>=task.startTime())||
                                        (endtime<task.endTime()&&endtime>=task.startTime())||
                                        (starttime<=task.startTime()&&endtime>=task.endTime())){
                                    return true;
                                }
                            }
                        }
                        return false;
                    }
                })
                .toBlocking()
                .first();
    }

    private void SetNowTime(){
        TimeTransform nowtime = new TimeTransform();
        Year = nowtime.getYear();
        Month = nowtime.getMonth();
        Day = nowtime.getDay();
        WeekDay = nowtime.getWeekDay();
        WeekOfYear = nowtime.getWeekOfYear();
    }

}
