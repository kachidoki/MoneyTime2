package com.kachidoki.ma.moneytime2.AddStatus;

import com.kachidoki.ma.moneytime2.Add.AddContract;
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
}
