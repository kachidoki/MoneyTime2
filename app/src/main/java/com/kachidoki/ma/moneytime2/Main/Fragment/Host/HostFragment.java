package com.kachidoki.ma.moneytime2.Main.Fragment.Host;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kachidoki.ma.moneytime2.App.Base.BaseLazyFragment;
import com.kachidoki.ma.moneytime2.Main.Fragment.Host.Di.HostModule;
import com.kachidoki.ma.moneytime2.Main.MainActivity;
import com.kachidoki.ma.moneytime2.Model.Task.Task;
import com.kachidoki.ma.moneytime2.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mayiwei on 2017/2/16.
 */

public class HostFragment extends BaseLazyFragment implements HostContract.View {


    @Inject
    HostContract.Presenter presenter;
    @Inject
    HostAdapter adapter;
    @Inject
    LinearLayoutManager linearLayoutManager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.host_topimg)
    ImageView hostTopimg;
    @BindView(R.id.host_monthAndYear)
    TextView hostMonthAndYear;
    @BindView(R.id.host_day)
    TextView hostDay;
    @BindView(R.id.host_weekDay)
    TextView hostWeekDay;
    @BindView(R.id.host_recycler)
    RecyclerView hostRecycler;
    @BindView(R.id.host_RefreshLayout)
    SwipeRefreshLayout hostRefreshLayout;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MainActivity) context).getMainComponent()
                .plus(new HostModule(this))
                .inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_host, container, false);
        ButterKnife.bind(this, view);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        hostRecycler.setAdapter(adapter);
        hostRecycler.setLayoutManager(linearLayoutManager);
        hostRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        hostRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refresh();
            }
        });
        return view;
    }


    @Override
    public void onLazyLoad() {
        presenter.start();
    }

    @Override
    public void showLoading() {
        hostRefreshLayout.setRefreshing(true);
    }

    @Override
    public void showTask() {
        hostRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showNoTask() {

    }

    @Override
    public void setTask(List<Task> tasks) {
        adapter.setData(tasks);
    }

    @Override
    public void showPop() {

    }
}
