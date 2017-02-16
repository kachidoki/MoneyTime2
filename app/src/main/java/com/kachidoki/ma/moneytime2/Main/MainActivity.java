package com.kachidoki.ma.moneytime2.Main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kachidoki.ma.moneytime2.App.App;

//import com.kachidoki.ma.moneytime2.Main.Di.DaggerMainComponent;
import com.kachidoki.ma.moneytime2.Main.Di.DaggerMainComponent;
import com.kachidoki.ma.moneytime2.Main.Di.MainComponent;
import com.kachidoki.ma.moneytime2.Main.Di.MainModule;
import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.ChartFragment;
import com.kachidoki.ma.moneytime2.Main.Fragment.Community.CommunityFragment;
import com.kachidoki.ma.moneytime2.Main.Fragment.Host.HostFragment;
import com.kachidoki.ma.moneytime2.Main.Fragment.Me.MeFragment;
import com.kachidoki.ma.moneytime2.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    private List<Fragment> fragments;
    @BindView(R.id.main_viewpager)
    ViewPager viewPager;
    @Inject
    HostFragment hostFragment;
    @Inject
    ChartFragment chartFragment;
    @Inject
    CommunityFragment communityFragment;
    @Inject
    MeFragment meFragment;
    @Inject
    MainContract.Presenter presenter;

    MainComponent mainComponent;

    public MainComponent getMainComponent(){
        return mainComponent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainComponent = DaggerMainComponent.builder()
                .appComponent(((App)getApplication()).getAppComponent())
                .mainModule(new MainModule(this))
                .build();
        mainComponent.inject(this);
        initViewPager();
    }


    private void initViewPager(){
        fragments = new ArrayList<>();
        fragments.add(hostFragment);
        fragments.add(chartFragment);
        fragments.add(communityFragment);
        fragments.add(meFragment);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

}
