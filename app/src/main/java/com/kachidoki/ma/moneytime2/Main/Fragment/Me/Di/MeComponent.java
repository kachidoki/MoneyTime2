package com.kachidoki.ma.moneytime2.Main.Fragment.Me.Di;

import com.kachidoki.ma.moneytime2.Main.Di.ForFragment;
import com.kachidoki.ma.moneytime2.Main.Fragment.Me.MeFragment;

import dagger.Module;
import dagger.Subcomponent;

/**
 * Created by mayiwei on 2017/2/16.
 */
@ForFragment
@Subcomponent(modules = MeModule.class)
public interface MeComponent {
    void inject(MeFragment meFragment);
}
