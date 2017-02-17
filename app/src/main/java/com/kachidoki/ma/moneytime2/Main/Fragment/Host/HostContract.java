package com.kachidoki.ma.moneytime2.Main.Fragment.Host;

import com.kachidoki.ma.moneytime2.App.Base.BasePresenter;
import com.kachidoki.ma.moneytime2.App.Base.BaseView;
import com.kachidoki.ma.moneytime2.Model.Task.Task;

import java.util.List;

/**
 * Created by mayiwei on 2017/2/16.
 */

public interface HostContract {
    interface View extends BaseView{
        void showLoading();

        void showTask();

        void showNoTask();

        void setTask(List<Task> tasks);

        void showPop();
    }

    interface Presenter extends BasePresenter{

        void loadTasks();

        void refresh();
    }

}
