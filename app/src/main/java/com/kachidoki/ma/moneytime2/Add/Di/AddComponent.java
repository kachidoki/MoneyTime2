package com.kachidoki.ma.moneytime2.Add.Di;

import com.kachidoki.ma.moneytime2.Add.AddActivity;
import com.kachidoki.ma.moneytime2.App.AppComponent;
import com.kachidoki.ma.moneytime2.Main.Di.ForActivity;

import dagger.Component;

/**
 * Created by mayiwei on 2017/2/17.
 */
@ForActivity
@Component(dependencies = AppComponent.class,modules = AddModule.class)
public interface AddComponent {

    void inject(AddActivity addActivity);
}
