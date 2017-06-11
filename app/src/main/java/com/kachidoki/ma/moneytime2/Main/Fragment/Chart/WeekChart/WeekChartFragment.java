package com.kachidoki.ma.moneytime2.Main.Fragment.Chart.WeekChart;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kachidoki.ma.moneytime2.App.Base.BaseLazyFragment;
import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.ChartFragment;
import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.WeekChart.Di.WeekChartModule;
import com.kachidoki.ma.moneytime2.Model.Task.Task;
import com.kachidoki.ma.moneytime2.R;
import com.kachidoki.ma.moneytime2.Widget.TableDialog;
import com.kachidoki.ma.moneytime2.Widget.WeekTable;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mayiwei on 2017/2/17.
 */

public class WeekChartFragment extends BaseLazyFragment implements WeekChartContract.View {


    @Inject
    WeekChartContract.Presenter presenter;
    @BindView(R.id.weekchart_table)
    WeekTable weekchartTable;
    @BindView(R.id.weekchart_previous)
    ImageView weekchartPrevious;
    @BindView(R.id.weekchart_isnowweek)
    TextView weekchartIsnowweek;
    @BindView(R.id.weekchart_week)
    TextView weekchartWeek;
    @BindView(R.id.weekchart_next)
    ImageView weekchartNext;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weekchart, container, false);
        ButterKnife.bind(this, view);
        weekchartTable.setOnClickListener(new WeekTable.itemOnClickListener() {
            @Override
            public void OnClick(Task task) {
                new TableDialog(getContext(), task).show();
            }
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unSubScribe();
    }

    @Override
    protected void setupComponent(Context context) {
        ((ChartFragment) getParentFragment()).getChartComponent()
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
        if (!presenter.isNowWeek()) {
            weekchartIsnowweek.setVisibility(View.GONE);
            weekchartWeek.setGravity(Gravity.CENTER);
        }else {
            weekchartIsnowweek.setVisibility(View.VISIBLE);
            weekchartWeek.setGravity(Gravity.START|Gravity.CENTER_VERTICAL);
        }
        weekchartWeek.setText("第"+presenter.getNowWeek()+"周");

    }

    @Override
    public void showWeekTasks(List<Task> tasks) {
        weekchartTable.setData(tasks);
    }

    @OnClick({R.id.weekchart_previous, R.id.weekchart_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.weekchart_previous:
                presenter.previousWeek();
                break;
            case R.id.weekchart_next:
                presenter.nextWeek();
                break;
        }
    }
}
