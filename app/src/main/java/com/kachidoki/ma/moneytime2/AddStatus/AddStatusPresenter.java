package com.kachidoki.ma.moneytime2.AddStatus;

import android.graphics.Bitmap;

import com.kachidoki.ma.moneytime2.App.Base.RBasePresenter;
import com.kachidoki.ma.moneytime2.Model.Status.Status;
import com.kachidoki.ma.moneytime2.Model.Status.StatusSource;

/**
 * Created by mayiwei on 2017/2/22.
 */

public class AddStatusPresenter extends RBasePresenter implements AddStatusContract.Presenter {

    private AddStatusContract.View view;
    private StatusSource statusSource;
    private Bitmap bitmap;

    public AddStatusPresenter(AddStatusContract.View view,StatusSource statusSource){
        this.view =view;
        this.statusSource =statusSource;
    }

    @Override
    public void start() {

    }

    @Override
    public void send(String message) {
        if (!message.isEmpty()||bitmap!=null){
            statusSource.sendPublicStatus(message, bitmap, Status.INBOX_SYSTEM, new StatusSource.StatusCall() {
                @Override
                public void fail(Exception e) {
                    view.sendFail(e);
                }

                @Override
                public void sucess() {
                    view.sendScuess();
                }
            });
        }else {
            view.showMessageNo();
        }
    }

    @Override
    public void setBitmip(Bitmap bitmip) {
        this.bitmap = bitmip;
        view.showBitmap(bitmip);
    }
}
