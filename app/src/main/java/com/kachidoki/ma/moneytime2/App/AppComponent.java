package com.kachidoki.ma.moneytime2.App;

import com.kachidoki.ma.moneytime2.Model.Status.StatusSource;
import com.kachidoki.ma.moneytime2.Model.Task.Source.TasksDataSource;
import com.kachidoki.ma.moneytime2.Model.User.UserSource;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mayiwei on 2017/2/16.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    TasksDataSource getTaskDataRepository();

    UserSource getUserModel();

    StatusSource getStatusSource();

}
