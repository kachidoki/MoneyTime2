package com.kachidoki.ma.moneytime2.Main.Fragment.Chart;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kachidoki.ma.moneytime2.App.Base.BaseLazyFragment;
import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.DayChart.DayChartFragment;
import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.Di.ChartComponent;
import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.Di.ChartModule;
import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.WeekChart.WeekChartFragment;
import com.kachidoki.ma.moneytime2.Main.MainActivity;
import com.kachidoki.ma.moneytime2.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mayiwei on 2017/2/16.
 */

public class ChartFragment extends BaseLazyFragment implements ChartContract.View {

    @Inject
    ChartContract.Presenter presenter;
    @Inject
    WeekChartFragment weekChartFragment;
    @Inject
    DayChartFragment dayChartFragment;

    @BindView(R.id.chart_toolbar_day)
    TextView chartToolbarDay;
    @BindView(R.id.chart_toolbar_week)
    TextView chartToolbarWeek;
    @BindView(R.id.chart_toolbar)
    Toolbar chartToolbar;

    ChartComponent chartComponent;
    boolean isfirst = true;
    boolean isDayFragmentShow = false;
    private final String WHATFRAGMENTSHOW = "isDayFragmentShow";

    @Override
    protected void setupComponent(Context context) {
        chartComponent = ((MainActivity) context).getMainComponent()
                .plus(new ChartModule(this));
        chartComponent.inject(this);
    }

    public ChartComponent getChartComponent(){
        return chartComponent;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        ButterKnife.bind(this, view);

        ((AppCompatActivity)getActivity()).setSupportActionBar(chartToolbar);
        android.support.v7.app.ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

        if (savedInstanceState!=null){
            isDayFragmentShow = savedInstanceState.getBoolean(WHATFRAGMENTSHOW,false);
        }
        if (isfirst){
            showFragment(true);
        }
        setTagColor();

        return view;
    }

    @Override
    public void onLazyLoad() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(WHATFRAGMENTSHOW,isDayFragmentShow);
    }

    @OnClick({R.id.chart_toolbar_day, R.id.chart_toolbar_week})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chart_toolbar_day:
                if (!isDayFragmentShow){
                    showFragment(true);
                }
                break;
            case R.id.chart_toolbar_week:
                if (isDayFragmentShow){
                    showFragment(false);
                }
                break;
        }
    }


    private void showFragment(boolean ShowDayFragment){
        if (ShowDayFragment){
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            if (dayChartFragment.isAdded()){
                transaction.hide(weekChartFragment).show(dayChartFragment);
                transaction.commit();
            }else {
                transaction.hide(weekChartFragment).add(R.id.daychart_content,dayChartFragment);
                transaction.show(dayChartFragment);
                transaction.commit();
            }
            isDayFragmentShow = true;
            setTagColor();
        }else {
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            if (weekChartFragment.isAdded()){
                transaction.hide(dayChartFragment).show(weekChartFragment);
                transaction.commit();
            }else {
                transaction.hide(dayChartFragment).add(R.id.daychart_content,weekChartFragment);
                transaction.show(weekChartFragment);
                transaction.commit();
            }
            isDayFragmentShow = false;
            setTagColor();
        }
        isfirst = false;
    }

    private void setTagColor(){
        if (isDayFragmentShow){
            chartToolbarWeek.setBackgroundResource(R.color.papuler);
            chartToolbarWeek.setTextColor(getResources().getColor(R.color.White));
            chartToolbarDay.setBackgroundResource(R.color.White);
            chartToolbarDay.setTextColor(getResources().getColor(R.color.papuler));
        }else {
            chartToolbarDay.setBackgroundResource(R.color.papuler);
            chartToolbarDay.setTextColor(getResources().getColor(R.color.White));
            chartToolbarWeek.setBackgroundResource(R.color.White);
            chartToolbarWeek.setTextColor(getResources().getColor(R.color.papuler));
        }
    }


}
