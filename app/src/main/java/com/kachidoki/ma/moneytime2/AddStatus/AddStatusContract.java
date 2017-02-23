package com.kachidoki.ma.moneytime2.AddStatus;

import android.graphics.Bitmap;

import com.kachidoki.ma.moneytime2.App.Base.BasePresenter;
import com.kachidoki.ma.moneytime2.App.Base.BaseView;

/**
 * Created by mayiwei on 2017/2/22.
 */

public interface AddStatusContract {
    interface View extends BaseView{

        void showMessageNo();

        void sendScuess();

        void sendFail(Exception e);
    }

    interface Presenter extends BasePresenter{
        void send(String message);

        void setBitmip(Bitmap bitmip);
    }
}
