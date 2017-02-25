package com.kachidoki.ma.moneytime2.DoTask.Di;

import com.kachidoki.ma.moneytime2.DoTask.DoTaskContract;
import com.kachidoki.ma.moneytime2.DoTask.DoTaskPresenter;
import com.kachidoki.ma.moneytime2.Main.Di.ForActivity;
import com.kachidoki.ma.moneytime2.Model.Task.Source.TasksDataSource;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mayiwei on 2017/2/25.
 */
@Module
public class DoTaskModule {
    private DoTaskContract.View view;

    public DoTaskModule(DoTaskContract.View view){
        this.view = view;
    }

    @ForActivity
    @Provides
    public DoTaskContract.Presenter providesPresnter(TasksDataSource tasksDataSource){
        return new DoTaskPresenter(view,tasksDataSource);
    }



}
