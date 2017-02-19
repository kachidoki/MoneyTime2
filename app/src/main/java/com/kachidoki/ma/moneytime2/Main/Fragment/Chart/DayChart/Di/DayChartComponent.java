package com.kachidoki.ma.moneytime2.Main.Fragment.Chart.DayChart.Di;

import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.DayChart.DayChartFragment;

import dagger.Subcomponent;

/**
 * Created by mayiwei on 2017/2/17.
 */
@ForFragmentSub
@Subcomponent(modules = DayChartModule.class)
public interface DayChartComponent {

    void inject(DayChartFragment dayChartFragment);

}
