package com.kachidoki.ma.moneytime2.Add.Di;

import android.content.Context;

import com.kachidoki.ma.moneytime2.Add.AddContract;
import com.kachidoki.ma.moneytime2.Add.AddPresenter;
import com.kachidoki.ma.moneytime2.Main.Di.ForActivity;
import com.kachidoki.ma.moneytime2.Model.Task.Source.TasksDataSource;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mayiwei on 2017/2/17.
 */
@Module
public class AddModule {
    private AddContract.View view;
    private Context mainContext;

    public AddModule(AddContract.View view,Context context){
        this.view = view;
        this.mainContext = context;
    }

    @ForActivity
    @Provides
    AddContract.Presenter providePresenter(TasksDataSource dataSource){
        return new AddPresenter(view,dataSource);
    }


}
