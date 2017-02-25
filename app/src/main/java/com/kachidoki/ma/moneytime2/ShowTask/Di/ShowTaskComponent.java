package com.kachidoki.ma.moneytime2.ShowTask.Di;

import com.kachidoki.ma.moneytime2.App.AppComponent;
import com.kachidoki.ma.moneytime2.Main.Di.ForActivity;
import com.kachidoki.ma.moneytime2.ShowTask.ShowTaskActivity;

import dagger.Component;

/**
 * Created by mayiwei on 2017/2/25.
 */
@ForActivity
@Component(dependencies = AppComponent.class,modules = ShowTaskModule.class)
public interface ShowTaskComponent {

    void inject(ShowTaskActivity showTaskActivity);

}
