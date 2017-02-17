package com.kachidoki.ma.moneytime2.Main.Fragment.Host.Di;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.kachidoki.ma.moneytime2.Main.Di.ForFragment;
import com.kachidoki.ma.moneytime2.Main.Fragment.Host.HostAdapter;
import com.kachidoki.ma.moneytime2.Main.Fragment.Host.HostContract;
import com.kachidoki.ma.moneytime2.Main.Fragment.Host.HostPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mayiwei on 2017/2/16.
 */
@Module
public class HostModule {
    private HostContract.View view;

    public HostModule(HostContract.View view){
        this.view = view;
    }

    @ForFragment
    @Provides
    HostContract.Presenter providePresenter(){
        return new HostPresenter(view);
    }

    @ForFragment
    @Provides
    HostAdapter provideAdapter(Context context){
        return new HostAdapter(context);
    }

    @ForFragment
    @Provides
    LinearLayoutManager provideLinearLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }

}
