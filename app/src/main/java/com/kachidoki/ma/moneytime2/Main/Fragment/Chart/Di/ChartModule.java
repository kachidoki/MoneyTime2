package com.kachidoki.ma.moneytime2.Main.Fragment.Chart.Di;

import com.kachidoki.ma.moneytime2.Main.Di.ForFragment;
import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.ChartContract;
import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.ChartPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mayiwei on 2017/2/16.
 */
@Module
public class ChartModule {
    private ChartContract.View view;

    public ChartModule(ChartContract.View view){
        this.view = view;
    }

    @ForFragment
    @Provides
    ChartContract.Presenter providePresenter(){
        return new ChartPresenter(view);
    }

}
