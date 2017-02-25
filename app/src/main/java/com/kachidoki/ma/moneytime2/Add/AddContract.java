package com.kachidoki.ma.moneytime2.Add;

import com.kachidoki.ma.moneytime2.App.Base.BasePresenter;
import com.kachidoki.ma.moneytime2.App.Base.BaseView;
import com.kachidoki.ma.moneytime2.Model.Task.Task;

import java.util.Calendar;

/**
 * Created by mayiwei on 2017/2/17.
 */

public interface AddContract {
    interface View extends BaseView{

        void setDateText(int year,int month,int day);

        float getstartTime();

        float getendTime();

        String getTitleText();

        String getDescription();

        int getColor();

        void showTimeIsNotOk();

        void showNoColor();

        void showNoTitle();


        void saveIsOk();

    }
    interface Presenter extends BasePresenter{

        void saveTask(Task task);

        Task checkInput(String title, float startTime, float endTime, int color, String description, boolean complete);

        void setDateTime(int year,int month,int day);

        Calendar getCurrentDate();
    }
}
