package com.kachidoki.ma.moneytime2.Main.Fragment.Chart.Di;

import com.kachidoki.ma.moneytime2.Main.Di.ForFragment;
import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.ChartFragment;

import dagger.Subcomponent;

/**
 * Created by mayiwei on 2017/2/16.
 */
@ForFragment
@Subcomponent(modules = ChartModule.class)
public interface ChartComponent {

    void inject(ChartFragment chartFragment);

}
