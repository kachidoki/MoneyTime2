package com.kachidoki.ma.moneytime2.RegLogin.Register.Di;

import com.kachidoki.ma.moneytime2.Main.Di.ForActivity;
import com.kachidoki.ma.moneytime2.Model.User.UserSource;
import com.kachidoki.ma.moneytime2.RegLogin.Register.RegisterContract;
import com.kachidoki.ma.moneytime2.RegLogin.Register.RegisterPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mayiwei on 2017/2/21.
 */
@Module
public class RegisterModule {
    private RegisterContract.View view;

    public RegisterModule(RegisterContract.View view){
        this.view = view;
    }

    @ForActivity
    @Provides
    RegisterContract.Presenter provides(UserSource userSource){
        return new RegisterPresenter(view,userSource);
    }
}
