package com.kachidoki.ma.moneytime2.DoTask.Di;

import com.kachidoki.ma.moneytime2.App.AppComponent;
import com.kachidoki.ma.moneytime2.DoTask.DoTaskActivity;
import com.kachidoki.ma.moneytime2.Main.Di.ForActivity;

import dagger.Component;

/**
 * Created by mayiwei on 2017/2/25.
 */
@ForActivity
@Component(dependencies = AppComponent.class,modules = DoTaskModule.class)
public interface DoTaskComponent {

    void inject(DoTaskActivity doTaskActivity);

}
