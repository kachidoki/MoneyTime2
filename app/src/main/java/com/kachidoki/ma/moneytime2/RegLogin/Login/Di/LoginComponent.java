package com.kachidoki.ma.moneytime2.RegLogin.Login.Di;

import com.kachidoki.ma.moneytime2.App.AppComponent;
import com.kachidoki.ma.moneytime2.Main.Di.ForActivity;
import com.kachidoki.ma.moneytime2.RegLogin.Login.LoginActivity;

import dagger.Component;

/**
 * Created by mayiwei on 2017/2/21.
 */
@ForActivity
@Component(dependencies = AppComponent.class,modules = LoginModule.class)
public interface LoginComponent {

    void inject(LoginActivity loginActivity);

}
