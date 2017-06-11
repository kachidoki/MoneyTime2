package com.kachidoki.ma.moneytime2.App.Base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Kachidoki on 2017/6/11.
 */

public class RBasePresenter implements BasePresenter{

    private CompositeSubscription subscriptions = new CompositeSubscription();

    @Override
    public void start() {

    }

    @Override
    public void addSubScription(Subscription subscription) {
        subscriptions.add(subscription);
    }

    public void unSubScribe(){
        if (subscriptions.hasSubscriptions())
            subscriptions.unsubscribe();
    }

}
