package com.kachidoki.ma.moneytime2.ShowTask;

import com.kachidoki.ma.moneytime2.Model.Task.Source.TasksDataSource;
import com.kachidoki.ma.moneytime2.Model.Task.Task;
import com.kachidoki.ma.moneytime2.R;

/**
 * Created by mayiwei on 2017/2/25.
 */

public class ShowTaskPresenter implements ShowTaskContract.Presenter {
    private ShowTaskContract.View view;
    private TasksDataSource tasksDataSource;

    public ShowTaskPresenter(ShowTaskContract.View view,TasksDataSource tasksDataSource){
        this.view = view;
        this.tasksDataSource = tasksDataSource;
    }


    @Override
    public void start() {

    }



    @Override
    public void deleteTask(String year,String day,String month,String startTime, String endTime) {
        tasksDataSource.deleteTask(year,day,month,startTime,endTime);
    }

    @Override
    public void doneTask(String year,String day,String month,String startTime, String endTime) {
        tasksDataSource.doneTask(year,day,month,startTime,endTime);
    }
}
