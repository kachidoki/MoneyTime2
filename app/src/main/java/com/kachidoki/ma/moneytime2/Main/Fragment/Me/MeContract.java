package com.kachidoki.ma.moneytime2.Main.Fragment.Me;

import com.kachidoki.ma.moneytime2.App.Base.BasePresenter;
import com.kachidoki.ma.moneytime2.App.Base.BaseView;
import com.kachidoki.ma.moneytime2.Model.User.User;

/**
 * Created by mayiwei on 2017/2/16.
 */

public interface MeContract {
    interface View extends BaseView{

        void showNotLogin();

        void setStatusNum(int num);

        void setFollowerNum(int num);

        void setFollowingNum(int num);

        void setUser(User user);

        void goToConfig();

        void gotoLogin();

    }

    interface Presenter extends BasePresenter{


        boolean checkIsLogin();

        void OnUserImgClick();

    }
}
