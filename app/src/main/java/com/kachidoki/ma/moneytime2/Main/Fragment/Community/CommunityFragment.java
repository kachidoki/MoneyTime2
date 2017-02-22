package com.kachidoki.ma.moneytime2.Main.Fragment.Community;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kachidoki.ma.moneytime2.App.Base.BaseLazyFragment;
import com.kachidoki.ma.moneytime2.Main.Fragment.Community.Di.CommunityModule;
import com.kachidoki.ma.moneytime2.Main.MainActivity;
import com.kachidoki.ma.moneytime2.Model.Status.Status;
import com.kachidoki.ma.moneytime2.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mayiwei on 2017/2/16.
 */

public class CommunityFragment extends BaseLazyFragment implements CommunityContract.View {

    @Inject
    CommunityContract.Presenter presenter;
    @Inject
    CommunityAdapter adapter;

    @BindView(R.id.community_toolbar)
    Toolbar toolbar;
    @BindView(R.id.community_recycler)
    RecyclerView communityRecycler;
    @BindView(R.id.community_RefreshLayout)
    SwipeRefreshLayout communityRefreshLayout;

    @Override
    protected void setupComponent(Context context) {
        ((MainActivity) context).getMainComponent()
                .plus(new CommunityModule(this))
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community, container, false);
        ButterKnife.bind(this, view);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("社区");
        communityRecycler.setAdapter(adapter);
        communityRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        communityRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
        communityRefreshLayout.setRefreshing(true);
    }

    @Override
    public void showNoTask() {

    }

    @Override
    public void setTask(List<Status> statuses) {
        adapter.setData(statuses);
        communityRefreshLayout.setRefreshing(false);
    }

}
