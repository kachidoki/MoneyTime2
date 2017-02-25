package com.kachidoki.ma.moneytime2.ShowTask;

import com.kachidoki.ma.moneytime2.App.Base.BasePresenter;
import com.kachidoki.ma.moneytime2.App.Base.BaseView;

/**
 * Created by mayiwei on 2017/2/25.
 */

public interface ShowTaskContract {
    interface View extends BaseView{

        void showDeleteDialog();

        void showDonDialog();

    }

    interface Presenter extends BasePresenter{

        String getColor(int color);

        int getColorResource(int color);

        void deleteTask(String year,String day,String month,String startTime, String endTime);

        void doneTask(String year,String day,String month,String startTime, String endTime);
    }
}
