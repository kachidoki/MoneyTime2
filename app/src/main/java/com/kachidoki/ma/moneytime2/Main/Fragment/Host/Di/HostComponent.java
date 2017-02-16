package com.kachidoki.ma.moneytime2.Main.Fragment.Host.Di;

import com.kachidoki.ma.moneytime2.Main.Di.ForFragment;
import com.kachidoki.ma.moneytime2.Main.Fragment.Host.HostFragment;

import dagger.Subcomponent;

/**
 * Created by mayiwei on 2017/2/16.
 */
@ForFragment
@Subcomponent(modules = HostModule.class)
public interface HostComponent {

    void inject(HostFragment hostFragment);

}
