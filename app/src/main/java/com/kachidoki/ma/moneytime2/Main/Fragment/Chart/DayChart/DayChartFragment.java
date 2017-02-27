package com.kachidoki.ma.moneytime2.Main.Fragment.Chart.DayChart;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.kachidoki.ma.moneytime2.App.Base.BaseLazyFragment;
import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.ChartFragment;
import com.kachidoki.ma.moneytime2.Main.Fragment.Chart.DayChart.Di.DayChartModule;
import com.kachidoki.ma.moneytime2.Model.Task.Task;
import com.kachidoki.ma.moneytime2.R;
import com.kachidoki.ma.moneytime2.Widget.PieChartView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mayiwei on 2017/2/17.
 */

public class DayChartFragment extends BaseLazyFragment implements DayChartContract.View {


    @Inject
    DayChartContract.Presenter presenter;
    @BindView(R.id.daychart_pieview)
    PieChartView daychartPieview;
    @BindView(R.id.daychart_showdata)
    TextView daychartShowdata;


    @Override
    protected void setupComponent(Context context) {
        ((ChartFragment) getParentFragment()).getChartComponent()
                .plus(new DayChartModule(this))
                .inject(this);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daychart, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onLazyLoad() {
        presenter.start();
        presenter.getDayTasks();
    }


    @Override
    public void showPickData() {
        new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                presenter.setDataTime(year, monthOfYear, dayOfMonth);
            }
        }, presenter.getCurrentDate().getYear(), presenter.getCurrentDate().getMonth()-1, presenter.getCurrentDate().getDay()).show();

    }

    @Override
    public void setDateText(int year, int month, int day) {
        daychartShowdata.setText(year+"年"+month+"月"+day+"日");
    }

    @Override
    public void showDayTasks(List<Task> tasks) {
        daychartPieview.setTaskData(tasks);
    }

    @OnClick(R.id.daychart_showdata)
    public void onClick() {
        showPickData();
    }
}
