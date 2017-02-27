package com.kachidoki.ma.moneytime2.Main.Fragment.Chart.WeekChart;

import com.kachidoki.ma.moneytime2.App.Base.BasePresenter;
import com.kachidoki.ma.moneytime2.App.Base.BaseView;
import com.kachidoki.ma.moneytime2.Model.Task.Task;

import java.util.List;

/**
 * Created by mayiwei on 2017/2/20.
 */

public interface WeekChartContract {
    interface View extends BaseView{

        void setWeekText(int weekOfyear);

        void showWeekTasks(List<Task> tasks);
    }

    interface Presenter extends BasePresenter{

        boolean isNowWeek();

        int getNowWeek();

        void getWeekTasks();

        void nextWeek();

        void previousWeek();
    }
}
