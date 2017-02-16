package com.kachidoki.ma.moneytime2.Main.Fragment.Me.Di;

import com.kachidoki.ma.moneytime2.Main.Di.ForFragment;
import com.kachidoki.ma.moneytime2.Main.Fragment.Me.MeContract;
import com.kachidoki.ma.moneytime2.Main.Fragment.Me.MePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mayiwei on 2017/2/16.
 */
@Module
public class MeModule {
    private MeContract.View view;

    public MeModule(MeContract.View view){
        this.view = view;
    }

    @ForFragment
    @Provides
    MeContract.Presenter providesPresenter(){
        return new MePresenter(view);
    }

}
