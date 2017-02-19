package com.kachidoki.ma.moneytime2.Main.Fragment.Host;


import com.kachidoki.ma.moneytime2.Model.Task.FakeData;
import com.kachidoki.ma.moneytime2.Model.Task.Source.TasksDataSource;

/**
 * Created by mayiwei on 2017/2/16.
 */

public class HostPresenter implements HostContract.Presenter {

    private HostContract.View view;
    private TasksDataSource tasksRepository;

    public HostPresenter(HostContract.View view, TasksDataSource tasksDataSource){
        this.view = view;
        this.tasksRepository = tasksDataSource;
    }

    @Override
    public void start() {
        view.setTask(FakeData.getTask());
    }

    @Override
    public void loadTasks() {

    }

    @Override
    public void refresh() {

    }
}
