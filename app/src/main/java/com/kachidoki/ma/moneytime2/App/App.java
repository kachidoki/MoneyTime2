package com.kachidoki.ma.moneytime2.App;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

/**
 * Created by mayiwei on 2017/2/14.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AVOSCloud.initialize(this, "HVxKKusA1TbN8S8m0PBAWvsT-gzGzoHsz", "KXbdm3BfvasK0irHFCQQSfnD");
        AVOSCloud.setDebugLogEnabled(true);
    }
}
