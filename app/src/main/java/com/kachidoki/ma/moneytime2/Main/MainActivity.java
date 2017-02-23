package com.kachidoki.ma.moneytime2.Main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.kachidoki.ma.moneytime2.App.App;
import com.kachidoki.ma.moneytime2.App.Base.BaseActivity;
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
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainContract.View {

    @BindView(R.id.main_ic_host)
    ImageView mainIcHost;
    @BindView(R.id.main_ic_host_lay)
    LinearLayout mainIcHostLay;
    @BindView(R.id.main_ic_chart)
    ImageView mainIcChart;
    @BindView(R.id.main_ic_chart_lay)
    LinearLayout mainIcChartLay;
    @BindView(R.id.main_ic_community)
    ImageView mainIcCommunity;
    @BindView(R.id.main_ic_community_lay)
    LinearLayout mainIcCommunityLay;
    @BindView(R.id.main_ic_me)
    ImageView mainIcMe;
    @BindView(R.id.main_ic_me_lay)
    LinearLayout mainIcMeLay;
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

    public MainComponent getMainComponent() {
        return mainComponent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViewPager();
    }

    @Override
    protected void setupActivityComponent() {
        mainComponent = DaggerMainComponent.builder()
                .appComponent(((App) getApplication()).getAppComponent())
                .mainModule(new MainModule(this, this))
                .build();
        mainComponent.inject(this);
    }


    private void initViewPager() {
        fragments = new ArrayList<>();
        fragments.add(hostFragment);
        fragments.add(chartFragment);
        fragments.add(communityFragment);
        fragments.add(meFragment);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        viewPager.setOnPageChangeListener(listener);
    }


    private void setTab(int current) {
        switch (current){
            case 0:
                mainIcHost.setImageDrawable(getResources().getDrawable(R.drawable.icon_main_host_2));
                mainIcChart.setImageDrawable(getResources().getDrawable(R.drawable.icon_main_chart_1));
                mainIcCommunity.setImageDrawable(getResources().getDrawable(R.drawable.icon_main_community_1));
                mainIcMe.setImageDrawable(getResources().getDrawable(R.drawable.icon_main_me_1));
                break;
            case 1:
                mainIcHost.setImageDrawable(getResources().getDrawable(R.drawable.icon_main_host_1));
                mainIcChart.setImageDrawable(getResources().getDrawable(R.drawable.icon_main_chart_2));
                mainIcCommunity.setImageDrawable(getResources().getDrawable(R.drawable.icon_main_community_1));
                mainIcMe.setImageDrawable(getResources().getDrawable(R.drawable.icon_main_me_1));
                break;
            case 2:
                mainIcHost.setImageDrawable(getResources().getDrawable(R.drawable.icon_main_host_1));
                mainIcChart.setImageDrawable(getResources().getDrawable(R.drawable.icon_main_chart_1));
                mainIcCommunity.setImageDrawable(getResources().getDrawable(R.drawable.icon_main_community_2));
                mainIcMe.setImageDrawable(getResources().getDrawable(R.drawable.icon_main_me_1));
                break;
            case 3:
                mainIcHost.setImageDrawable(getResources().getDrawable(R.drawable.icon_main_host_1));
                mainIcChart.setImageDrawable(getResources().getDrawable(R.drawable.icon_main_chart_1));
                mainIcCommunity.setImageDrawable(getResources().getDrawable(R.drawable.icon_main_community_1));
                mainIcMe.setImageDrawable(getResources().getDrawable(R.drawable.icon_main_me_2));
                break;
        }
    }


    ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            setTab(position);
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @OnClick({R.id.main_ic_host_lay, R.id.main_ic_chart_lay, R.id.main_ic_community_lay, R.id.main_ic_me_lay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_ic_host_lay:
                viewPager.setCurrentItem(0);
                break;
            case R.id.main_ic_chart_lay:
                viewPager.setCurrentItem(1);
                break;
            case R.id.main_ic_community_lay:
                viewPager.setCurrentItem(2);
                break;
            case R.id.main_ic_me_lay:
                viewPager.setCurrentItem(3);
                break;
        }
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
