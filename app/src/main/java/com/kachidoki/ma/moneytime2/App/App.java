package com.kachidoki.ma.moneytime2.App;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

/**
 * Created by mayiwei on 2017/2/14.
 */

public class App extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

        AVOSCloud.initialize(this,AppConstant.AVAPPID,AppConstant.AVAPPKEY);
        AVOSCloud.setDebugLogEnabled(true);
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }


}
