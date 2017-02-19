package com.kachidoki.ma.moneytime2.Main.Fragment.Chart;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kachidoki.ma.moneytime2.App.Base.BaseLazyFragment;
import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.DayChart.DayChartFragment;
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

    boolean isDayFragmentShow = true;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MainActivity) context).getMainComponent()
                .plus(new ChartModule(this))
                .inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        ButterKnife.bind(this, view);


        ((AppCompatActivity)getActivity()).setSupportActionBar(chartToolbar);
        android.support.v7.app.ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.hide(weekChartFragment).show(dayChartFragment);
        transaction.commit();

        return view;
    }


    @Override
    public void onLazyLoad() {

    }

    @OnClick({R.id.chart_toolbar_day, R.id.chart_toolbar_week})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chart_toolbar_day:
                if (!isDayFragmentShow){
                    if (dayChartFragment.isAdded()){
                        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                        transaction.hide(weekChartFragment).show(dayChartFragment);
                        transaction.commit();
                    }else {
                        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                        transaction.add(R.id.daychart_content,dayChartFragment);
                        transaction.commit();
                    }
                    isDayFragmentShow = true;
                }
                break;
            case R.id.chart_toolbar_week:
                if (isDayFragmentShow){
                    if (dayChartFragment.isAdded()){
                        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                        transaction.hide(dayChartFragment).show(weekChartFragment);
                        transaction.commit();
                    }else {
                        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                        transaction.add(R.id.daychart_content,weekChartFragment);
                        transaction.commit();
                    }
                    isDayFragmentShow = false;
                }
                break;
        }
    }
}