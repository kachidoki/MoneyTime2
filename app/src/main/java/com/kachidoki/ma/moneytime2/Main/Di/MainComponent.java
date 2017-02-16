package com.kachidoki.ma.moneytime2.Main.Di;

import com.kachidoki.ma.moneytime2.App.AppComponent;
import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.Di.ChartComponent;
import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.Di.ChartModule;
import com.kachidoki.ma.moneytime2.Main.Fragment.Community.Di.CommunityComponent;
import com.kachidoki.ma.moneytime2.Main.Fragment.Community.Di.CommunityModule;
import com.kachidoki.ma.moneytime2.Main.Fragment.Host.Di.HostComponent;
import com.kachidoki.ma.moneytime2.Main.Fragment.Host.Di.HostModule;
import com.kachidoki.ma.moneytime2.Main.Fragment.Me.Di.MeComponent;
import com.kachidoki.ma.moneytime2.Main.Fragment.Me.Di.MeModule;
import com.kachidoki.ma.moneytime2.Main.MainActivity;

import dagger.Component;

/**
 * Created by mayiwei on 2017/2/16.
 */

@ForActivity
@Component(dependencies = AppComponent.class,modules = MainModule.class)
public interface MainComponent {

    void inject(MainActivity mainActivity);

    HostComponent plus(HostModule hostModule);

    ChartComponent plus(ChartModule chartModule);

    CommunityComponent plus(CommunityModule communityModule);

    MeComponent plus(MeModule meModule);


}
