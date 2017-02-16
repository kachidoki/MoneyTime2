package com.kachidoki.ma.moneytime2.Main.Fragment.Community.Di;

import com.kachidoki.ma.moneytime2.Main.Di.ForFragment;
import com.kachidoki.ma.moneytime2.Main.Fragment.Community.CommunityContract;
import com.kachidoki.ma.moneytime2.Main.Fragment.Community.CommunityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mayiwei on 2017/2/16.
 */
@Module
public class CommunityModule {
    private CommunityContract.View view;

    public CommunityModule(CommunityContract.View view){
        this.view = view;
    }

    @ForFragment
    @Provides
    CommunityContract.Presenter providePresenter(){
        return new CommunityPresenter(view);
    }


}
