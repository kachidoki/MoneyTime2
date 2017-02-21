package com.kachidoki.ma.moneytime2.RegLogin.Register;

import com.kachidoki.ma.moneytime2.App.AppConstant;
import com.kachidoki.ma.moneytime2.Model.User.UserSource;

/**
 * Created by mayiwei on 2017/2/21.
 */

public class RegisterPresenter implements RegisterContract.Presenter {

    private RegisterContract.View view;
    private UserSource userSource;

    public RegisterPresenter(RegisterContract.View view,UserSource userSource){
        this.view = view;
        this.userSource = userSource;
    }

    @Override
    public void start() {

    }

    @Override
    public boolean checkInput(String name, String password, String passRe) {
        if (name.length()< AppConstant.NameMinLenth) {
            view.showNameIsShort();
            return false;
        }else if (name.length()> AppConstant.NameMaxLenth){
            view.showNameIsLong();
            return false;
        }else if (password.length()<AppConstant.PasswordMinLenth) {
            view.showPassIsNot();
            return false;
        }else if (!password.equals(passRe)){
            view.showPassIsNotSame();
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void Login(String name, String password) {
        userSource.Register(name, password, new UserSource.UserCall() {
            @Override
            public void sucess() {
                view.registerSucess();
            }

            @Override
            public void fail(Exception e) {
                view.registerFail(e);
            }
        });
    }
}
