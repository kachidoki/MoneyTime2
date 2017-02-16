package com.kachidoki.ma.moneytime2.App;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;

/**
 * Created by mayiwei on 2017/2/16.
 */
@Module
public class AppModule {
    private Context context;

    AppModule(Context context){
        this.context = context;
    }


}
