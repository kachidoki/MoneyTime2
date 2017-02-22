package com.kachidoki.ma.moneytime2.AddStatus.Di;

import com.kachidoki.ma.moneytime2.AddStatus.AddStatusContract;
import com.kachidoki.ma.moneytime2.AddStatus.AddStatusPresenter;
import com.kachidoki.ma.moneytime2.Main.Di.ForActivity;
import com.kachidoki.ma.moneytime2.Model.Status.StatusSource;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mayiwei on 2017/2/22.
 */
@Module
public class AddStatusModule {
    private AddStatusContract.View view;

    public AddStatusModule(AddStatusContract.View view){
        this.view = view;
    }

    @ForActivity
    @Provides
    AddStatusContract.Presenter providesPresenter(StatusSource statusSource){
        return new AddStatusPresenter(view,statusSource);
    }
}
