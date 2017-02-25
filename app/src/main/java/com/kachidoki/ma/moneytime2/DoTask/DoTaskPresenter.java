package com.kachidoki.ma.moneytime2.DoTask;

import com.kachidoki.ma.moneytime2.Model.Task.Source.TasksDataSource;
import com.kachidoki.ma.moneytime2.Utils.TimeTransform;

/**
 * Created by mayiwei on 2017/2/25.
 */

public class DoTaskPresenter implements DoTaskContract.Presenter {

    private DoTaskContract.View view;
    private TasksDataSource tasksDataSource;

    public DoTaskPresenter(DoTaskContract.View view,TasksDataSource tasksDataSource){
        this.view = view;
        this.tasksDataSource = tasksDataSource;
    }

    @Override
    public void start() {
        view.startThread();
        getData();

    }

    @Override
    public String getNowTime() {
        return new TimeTransform().toString("HH:mm");
    }

    @Override
    public void getData() {
        TimeTransform timeTransform = new TimeTransform();
        view.showData(timeTransform.getMonth()+"月"+timeTransform.getDay()+"日 "+getWeekName(timeTransform.getWeekDay()));
    }

    @Override
    public void startDoTime(float starttime, float endtime) {
        if (endtime-starttime>0){
            view.showCoutDown((long) ((endtime-starttime)*TimeTransform.HOUR));
        }
    }

    @Override
    public void downTask() {

    }

    private String getWeekName(int i){
        switch (i){
            case 1:return "周日";
            case 2:return "周一";
            case 3:return "周二";
            case 4:return "周三";
            case 5:return "周四";
            case 6:return "周五";
            case 7:return "周六";
            default: return "";
        }
    }


}
