package com.kachidoki.ma.moneytime2.Main.Fragment.Chart.Di;

import com.kachidoki.ma.moneytime2.Main.Di.ForFragment;
import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.ChartFragment;
import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.DayChart.Di.DayChartComponent;
import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.DayChart.Di.DayChartModule;
import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.WeekChart.Di.WeekChartComponent;
import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.WeekChart.Di.WeekChartModule;

import dagger.Subcomponent;

/**
 * Created by mayiwei on 2017/2/16.
 */
@ForFragment
@Subcomponent(modules = ChartModule.class)
public interface ChartComponent {

    void inject(ChartFragment chartFragment);

    DayChartComponent plus(DayChartModule dayChartModule);

    WeekChartComponent plus(WeekChartModule weekChartModule);

}
