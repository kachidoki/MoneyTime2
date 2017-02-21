package com.kachidoki.ma.moneytime2.Main.Fragment.Community;

import com.kachidoki.ma.moneytime2.App.Base.BasePresenter;
import com.kachidoki.ma.moneytime2.App.Base.BaseView;
import com.kachidoki.ma.moneytime2.Model.Status.Status;

import java.util.List;

/**
 * Created by mayiwei on 2017/2/16.
 */

public interface CommunityContract {
    interface View extends BaseView{
        void showLoading();

        void showNoTask();

        void setTask(List<Status> statuses);

    }

    interface Presenter extends BasePresenter{
        void loadStatus();

        void refresh();
    }
}
