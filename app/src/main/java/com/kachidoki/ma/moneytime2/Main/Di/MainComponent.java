package com.kachidoki.ma.moneytime2.Main.Di;

import com.kachidoki.ma.moneytime2.App.AppComponent;
import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.Di.ChartModule;
import com.kachidoki.ma.moneytime2.Main.Fragment.Community.Di.CommunityModule;
import com.kachidoki.ma.moneytime2.Main.Fragment.Host.Di.HostModule;
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

    HostModule plus(HostModule hostModule);

    ChartModule plus(ChartModule chartModule);

    CommunityModule plus(CommunityModule communityModule);

    MeModule plus(MeModule meModule);


}
