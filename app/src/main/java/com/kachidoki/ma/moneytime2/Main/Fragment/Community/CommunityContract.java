package com.kachidoki.ma.moneytime2.Main.Fragment.Community;

import com.kachidoki.ma.moneytime2.App.Base.BasePresenter;
import com.kachidoki.ma.moneytime2.App.Base.BaseView;
import com.kachidoki.ma.moneytime2.Model.Status.Status;
import com.kachidoki.ma.moneytime2.Model.User.User;

import java.util.List;

/**
 * Created by mayiwei on 2017/2/16.
 */

public interface CommunityContract {
    interface View extends BaseView{
        void showLoading();

        void showNoTask();

        void setTask(List<Status> statuses);

        void setUser(User user);

    }

    interface Presenter extends BasePresenter{
        void loadUser();

        void loadStatus();

        void refresh();
    }
}
