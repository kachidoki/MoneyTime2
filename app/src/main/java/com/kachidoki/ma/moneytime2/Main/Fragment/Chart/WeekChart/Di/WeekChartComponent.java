package com.kachidoki.ma.moneytime2.Main.Fragment.Chart.WeekChart.Di;

import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.DayChart.Di.ForFragmentSub;
import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.WeekChart.WeekChartFragment;

import dagger.Subcomponent;

/**
 * Created by mayiwei on 2017/2/18.
 */
@ForFragmentSub
@Subcomponent(modules = WeekChartModule.class)
public interface WeekChartComponent {
    void inject(WeekChartFragment weekChartFragment);
}
