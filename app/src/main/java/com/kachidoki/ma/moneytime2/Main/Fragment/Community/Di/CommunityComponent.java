package com.kachidoki.ma.moneytime2.Main.Fragment.Community.Di;

import com.kachidoki.ma.moneytime2.Main.Di.ForFragment;
import com.kachidoki.ma.moneytime2.Main.Fragment.Community.CommunityFragment;

import dagger.Subcomponent;

/**
 * Created by mayiwei on 2017/2/16.
 */
@ForFragment
@Subcomponent(modules = CommunityModule.class)
public interface CommunityComponent {

    void inject(CommunityFragment communityFragment);

}
