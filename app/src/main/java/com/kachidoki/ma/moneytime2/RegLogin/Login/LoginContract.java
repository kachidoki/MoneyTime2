package com.kachidoki.ma.moneytime2.RegLogin.Login;

import com.kachidoki.ma.moneytime2.App.Base.BasePresenter;
import com.kachidoki.ma.moneytime2.App.Base.BaseView;

/**
 * Created by mayiwei on 2017/2/21.
 */

public interface LoginContract {
    interface View extends BaseView{

        void showNameIsNotOk();

        void showPassWordIsNotOk();

        void showNameOrPassIsNo();

        void LoginSucess();

        void LoginFail();

    }

    interface Presenter extends BasePresenter{

        boolean checkInput(String name,String password);

        void Login(String name, String password);
    }
}
