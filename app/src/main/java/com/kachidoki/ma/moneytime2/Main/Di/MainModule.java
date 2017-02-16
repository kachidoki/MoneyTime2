package com.kachidoki.ma.moneytime2.Main.Di;

import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.ChartFragment;
import com.kachidoki.ma.moneytime2.Main.Fragment.Community.CommunityFragment;
import com.kachidoki.ma.moneytime2.Main.Fragment.Host.HostFragment;
import com.kachidoki.ma.moneytime2.Main.Fragment.Me.MeFragment;
import com.kachidoki.ma.moneytime2.Main.MainContract;
import com.kachidoki.ma.moneytime2.Main.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mayiwei on 2017/2/16.
 */
@Module
public class MainModule {
    private MainContract.View view;

    public MainModule(MainContract.View view){
        this.view = view;
    }

    @ForActivity
    @Provides
    HostFragment provideHostFragment(){
        return new HostFragment();
    }

    @ForActivity
    @Provides
    ChartFragment provideChartFragment(){
        return new ChartFragment();
    }

    @ForActivity
    @Provides
    CommunityFragment provideCommunityFragment(){
        return new CommunityFragment();
    }

    @ForActivity
    @Provides
    MeFragment provideMeFragment(){
        return new MeFragment();
    }


    @ForActivity
    @Provides
    MainContract.Presenter providePresenter(){
        return new MainPresenter(view);
    }
}
