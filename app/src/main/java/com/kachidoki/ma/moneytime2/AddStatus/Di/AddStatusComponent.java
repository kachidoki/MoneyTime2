package com.kachidoki.ma.moneytime2.AddStatus.Di;

import com.kachidoki.ma.moneytime2.AddStatus.AddStatusActivity;
import com.kachidoki.ma.moneytime2.App.AppComponent;
import com.kachidoki.ma.moneytime2.Main.Di.ForActivity;

import dagger.Component;

/**
 * Created by mayiwei on 2017/2/22.
 */
@ForActivity
@Component(dependencies = AppComponent.class,modules = AddStatusModule.class)
public interface AddStatusComponent {

    void inject(AddStatusActivity addStatusActivity);

}
