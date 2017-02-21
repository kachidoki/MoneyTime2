package com.kachidoki.ma.moneytime2.RegLogin.Register.Di;

import com.kachidoki.ma.moneytime2.App.AppComponent;
import com.kachidoki.ma.moneytime2.Main.Di.ForActivity;
import com.kachidoki.ma.moneytime2.RegLogin.Register.RegisterActivity;

import dagger.Component;

/**
 * Created by mayiwei on 2017/2/21.
 */
@ForActivity
@Component(dependencies = AppComponent.class,modules = RegisterModule.class)
public interface RegisterComponent {

    void inject(RegisterActivity registerActivity);

}
