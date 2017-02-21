package com.kachidoki.ma.moneytime2.Main.Fragment.Community.Di;

import android.content.Context;

import com.kachidoki.ma.moneytime2.Main.Di.ForFragment;
import com.kachidoki.ma.moneytime2.Main.Fragment.Community.CommunityAdapter;
import com.kachidoki.ma.moneytime2.Main.Fragment.Community.CommunityContract;
import com.kachidoki.ma.moneytime2.Main.Fragment.Community.CommunityPresenter;
import com.kachidoki.ma.moneytime2.Model.Status.StatusSource;
import com.kachidoki.ma.moneytime2.Model.User.UserSource;

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
    CommunityContract.Presenter providePresenter(StatusSource statusSource){
        return new CommunityPresenter(view,statusSource);
    }

    @ForFragment
    @Provides
    CommunityAdapter providesAdapter(Context context, UserSource userSource){
        return new CommunityAdapter(context,userSource);
    }
}
