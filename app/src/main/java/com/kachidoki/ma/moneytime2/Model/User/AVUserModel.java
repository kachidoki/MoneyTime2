package com.kachidoki.ma.moneytime2.Model.User;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.SaveCallback;

/**
 * Created by mayiwei on 2017/2/14.
 */

public class AVUserModel implements UserSource {

    @Override
    public void Login(String username, String password, final UserCall call) {
        AVUser.logInInBackground(username, password, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                if (e != null) {
                    call.fail(e);
                } else {
                    call.sucess();
                }
            }
        });
    }

    @Override
    public void Register(String username, String password, final UserCall call) {
        AVUser user = new AVUser();
        user.setUsername(username);
        user.setPassword(password);
        user.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e != null) {
                    call.fail(e);
                } else {
                    call.sucess();
                }
            }
        });
    }


    @Override
    public Object getNowAccount() {
        return AVUser.getCurrentUser();
    }

    @Override
    public void LogOut() {
        AVUser.logOut();
    }

    @Override
    public boolean isLogin() {
        AVUser user = AVUser.getCurrentUser();
        if (user!=null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public User mapperToUser(Object user) {
        return new AutoValue_User(((AVUser)user).getObjectId(),
                ((AVUser)user).getEmail(),
                ((AVUser)user).getSessionToken(),
                ((AVUser)user).getUsername(),
                ((AVUser)user).getMobilePhoneNumber(),
                ((AVUser)user).isMobilePhoneVerified());
    }
}
