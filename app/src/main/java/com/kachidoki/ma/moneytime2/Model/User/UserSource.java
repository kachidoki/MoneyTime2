package com.kachidoki.ma.moneytime2.Model.User;

import com.avos.avoscloud.AVUser;

/**
 * Created by mayiwei on 2017/2/14.
 */

public interface UserSource {
    public interface UserCall{
        void sucess();
        void fail(Exception e);
    }

    void Login(String username, String password, final UserSource.UserCall call);

    void Register(String username, String password, final UserSource.UserCall call);

    Object getNowAccount();

    void LogOut();

    boolean isLogin();

}
