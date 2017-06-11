package com.kachidoki.ma.moneytime2.App.Base;

import rx.Subscription;

/**
 * Created by mayiwei on 2017/2/16.
 */

public interface BasePresenter {
    void start();

    void addSubScription(Subscription subscription);

    void unSubScribe();
}
