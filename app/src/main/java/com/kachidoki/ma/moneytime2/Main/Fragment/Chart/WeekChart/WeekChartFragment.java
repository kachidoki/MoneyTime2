package com.kachidoki.ma.moneytime2.Main.Fragment.Chart.WeekChart;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kachidoki.ma.moneytime2.App.Base.BaseLazyFragment;
import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.ChartFragment;
import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.WeekChart.Di.WeekChartModule;
import com.kachidoki.ma.moneytime2.Model.Task.Task;
import com.kachidoki.ma.moneytime2.R;
import com.kachidoki.ma.moneytime2.Widget.WeekTable;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mayiwei on 2017/2/17.
 */

public class WeekChartFragment extends BaseLazyFragment implements WeekChartContract.View {


    @Inject
    WeekChartContract.Presenter presenter;
    @BindView(R.id.weekchart_table)
    WeekTable weekchartTable;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weekchart, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    protected void setupComponent(Context context) {
        ((ChartFragment)getParentFragment()).getChartComponent()
                .plus(new WeekChartModule(this))
                .inject(this);
    }

    @Override
    public void onLazyLoad() {
        presenter.start();
        presenter.getWeekTasks();
    }

    @Override
    public void setWeekText(int weekOfyear) {

    }

    @Override
    public void showWeekTasks(List<Task> tasks) {
        weekchartTable.setData(tasks);
    }
}
