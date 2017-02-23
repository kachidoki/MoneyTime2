package com.kachidoki.ma.moneytime2.AddStatus;

import android.graphics.Bitmap;

import com.kachidoki.ma.moneytime2.Add.AddContract;
import com.kachidoki.ma.moneytime2.App.AppConstant;
import com.kachidoki.ma.moneytime2.Model.Status.Status;
import com.kachidoki.ma.moneytime2.Model.Status.StatusSource;

/**
 * Created by mayiwei on 2017/2/22.
 */

public class AddStatusPresenter implements AddStatusContract.Presenter {

    private AddStatusContract.View view;
    private StatusSource statusSource;

    public AddStatusPresenter(AddStatusContract.View view,StatusSource statusSource){
        this.view =view;
        this.statusSource =statusSource;
    }

    @Override
    public void start() {

    }

    @Override
    public void send(String message, Bitmap bitmap) {
        if (!message.isEmpty()||bitmap!=null){
            statusSource.sendStatus(message, bitmap, Status.INBOX_TIMELINE, new StatusSource.StatusCall() {
                @Override
                public void fail(Exception e) {
                    view.sendFail(e);
                }

                @Override
                public void sucess() {
                    view.sendScuess();
                }
            });
        }
    }
}
