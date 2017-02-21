package com.kachidoki.ma.moneytime2.RegLogin.Register;

import com.kachidoki.ma.moneytime2.App.Base.BasePresenter;
import com.kachidoki.ma.moneytime2.App.Base.BaseView;

/**
 * Created by mayiwei on 2017/2/21.
 */

public interface RegisterContract {
    interface View extends BaseView{

        void showNameIsShort();

        void showNameIsLong();

        void showPassIsNot();

        void showPassIsNotSame();

        void registerSucess();

        void registerFail(Exception e);

    }

    interface Presenter extends BasePresenter{

        boolean checkInput(String name,String password,String passRe);

        void Login(String name, String password);

    }
}
