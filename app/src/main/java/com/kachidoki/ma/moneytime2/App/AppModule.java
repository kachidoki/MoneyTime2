package com.kachidoki.ma.moneytime2.App;

import android.content.Context;

import com.kachidoki.ma.moneytime2.Model.File.FileSource;
import com.kachidoki.ma.moneytime2.Model.File.QiniuFileModel;
import com.kachidoki.ma.moneytime2.Model.Status.AVStatusModel;
import com.kachidoki.ma.moneytime2.Model.Status.StatusSource;
import com.kachidoki.ma.moneytime2.Model.Task.LocalTasksDataSource;
import com.kachidoki.ma.moneytime2.Model.Task.Source.TasksDataSource;
import com.kachidoki.ma.moneytime2.Model.User.AVUserModel;
import com.kachidoki.ma.moneytime2.Model.User.UserSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mayiwei on 2017/2/16.
 */
@Module
public class AppModule {
    private Context context;

    AppModule(Context context){
        this.context = context;
    }

    @Singleton
    @Provides
    TasksDataSource providesTaskDataReposity(){
        return new LocalTasksDataSource(context);
    }

    @Singleton
    @Provides
    UserSource providesUserModel(){
        return new AVUserModel();
    }

    @Singleton
    @Provides
    StatusSource providesStatusModel(UserSource userSource,FileSource fileSource){
        return new AVStatusModel(userSource,fileSource);
    }

    @Singleton
    @Provides
    FileSource providesFileModel(){
        return new QiniuFileModel(AppConstant.QINIUAK,AppConstant.QINIUSK,AppConstant.QINIUbucket);
    }

}
