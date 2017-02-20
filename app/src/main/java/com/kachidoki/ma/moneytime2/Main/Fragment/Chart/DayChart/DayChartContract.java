package com.kachidoki.ma.moneytime2.Main.Fragment.Chart.DayChart;

import com.kachidoki.ma.moneytime2.App.Base.BasePresenter;
import com.kachidoki.ma.moneytime2.App.Base.BaseView;
import com.kachidoki.ma.moneytime2.Model.Task.Task;

import java.util.Calendar;
import java.util.List;

/**
 * Created by mayiwei on 2017/2/17.
 */

public interface DayChartContract {
    interface View extends BaseView{

        void showPickData();

        void setDateText(int year,int month,int day);

        void showDayTasks(List<Task> tasks);
    }

    interface Presenter extends BasePresenter{

        void setDataTime(int year,int month,int day);

        Calendar getCurrentDate();

        void getDayTasks();
    }
}
