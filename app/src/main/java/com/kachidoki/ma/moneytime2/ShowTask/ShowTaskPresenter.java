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
    public String getColor(int color) {
        switch (color){
            case Task.YELLOW:return "高效";
            case Task.ORANGE:return "不专心";
            case Task.GREEN:return "休息";
            case Task.BLUE:return "玩耍";
            case Task.RED:return "拖延";
            default:return "";
        }
    }

    @Override
    public int getColorResource(int color) {
        switch (color){
            case Task.YELLOW:return R.color.Yellow;
            case Task.ORANGE:return R.color.Orange;
            case Task.GREEN:return R.color.Green;
            case Task.BLUE:return R.color.Blue;
            case Task.RED:return R.color.Red;
            default:return R.color.Black;
        }
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
