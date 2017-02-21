package com.kachidoki.ma.moneytime2.Main.Fragment.Community;

import com.kachidoki.ma.moneytime2.Model.Status.StatusSource;

/**
 * Created by mayiwei on 2017/2/16.
 */

public class CommunityPresenter implements CommunityContract.Presenter {
    private CommunityContract.View view;
    private StatusSource statusSource;

    public CommunityPresenter(CommunityContract.View view,StatusSource statusSource){
        this.view = view;
        this.statusSource = statusSource;
    }


    @Override
    public void start() {

    }

    @Override
    public void loadStatus() {

    }

    @Override
    public void refresh() {

    }
}
