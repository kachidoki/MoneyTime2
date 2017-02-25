package com.kachidoki.ma.moneytime2.DoTask;

import com.kachidoki.ma.moneytime2.App.Base.BasePresenter;
import com.kachidoki.ma.moneytime2.App.Base.BaseView;

/**
 * Created by mayiwei on 2017/2/25.
 */

public interface DoTaskContract {

    interface View extends BaseView{

        void showNowTime(String time);

        void showData(String data);

        void showCoutDown(long time);

        void showDown();

        void startThread();

    }

    interface Presenter extends BasePresenter{

        String getNowTime();

        void getData();

        void startDoTime(float starttime,float endtime);

        void downTask();


    }

}
