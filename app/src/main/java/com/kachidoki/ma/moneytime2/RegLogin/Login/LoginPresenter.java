package com.kachidoki.ma.moneytime2.RegLogin.Login;

import com.kachidoki.ma.moneytime2.App.AppConstant;
import com.kachidoki.ma.moneytime2.Model.User.UserSource;

/**
 * Created by mayiwei on 2017/2/21.
 */

public class LoginPresenter implements LoginContract.Presenter{

    private LoginContract.View view;
    private UserSource userSource;

    public LoginPresenter(LoginContract.View view,UserSource userSource){
        this.view = view;
        this.userSource = userSource;
    }


    @Override
    public void start() {

    }

    @Override
    public boolean checkInput(String name, String password) {
        if (name.length()<AppConstant.NameMinLenth||name.length()>AppConstant.NameMaxLenth){
            view.showNameIsNotOk();
            return false;
        } else if (password.length()<AppConstant.PasswordMinLenth) {
            view.showPassWordIsNotOk();
            return false;
        }else if (name.isEmpty()||password.isEmpty()){
            view.showNameOrPassIsNo();
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void Login(String name, String password) {
        userSource.Login(name, password, new UserSource.UserCall() {
            @Override
            public void sucess() {
                view.LoginSucess();
            }

            @Override
            public void fail(Exception e) {
                view.LoginFail();
            }
        });
    }
}
