package com.kachidoki.ma.moneytime2.RegLogin.Login.Di;

import com.kachidoki.ma.moneytime2.Main.Di.ForActivity;
import com.kachidoki.ma.moneytime2.Model.User.UserSource;
import com.kachidoki.ma.moneytime2.RegLogin.Login.LoginContract;
import com.kachidoki.ma.moneytime2.RegLogin.Login.LoginPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mayiwei on 2017/2/21.
 */
@Module
public class LoginModule {

    private LoginContract.View view;
    public LoginModule(LoginContract.View view){
        this.view = view;
    }

    @ForActivity
    @Provides
    LoginContract.Presenter providesPresenter(UserSource userSource){
        return new LoginPresenter(view,userSource);
    }
}
