package com.kachidoki.ma.moneytime2.ShowTask.Di;

import com.kachidoki.ma.moneytime2.Main.Di.ForActivity;
import com.kachidoki.ma.moneytime2.Model.Task.Source.TasksDataSource;
import com.kachidoki.ma.moneytime2.ShowTask.ShowTaskContract;
import com.kachidoki.ma.moneytime2.ShowTask.ShowTaskPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mayiwei on 2017/2/25.
 */
@Module
public class ShowTaskModule {
    private ShowTaskContract.View view;

    public ShowTaskModule(ShowTaskContract.View view){
        this.view = view;
    }

    @ForActivity
    @Provides
    ShowTaskContract.Presenter providesPresenter(TasksDataSource tasksDataSource){
        return new ShowTaskPresenter(view,tasksDataSource);
    }
}
