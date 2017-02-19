package com.kachidoki.ma.moneytime2.Main.Fragment.Chart.DayChart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kachidoki.ma.moneytime2.App.Base.BaseLazyFragment;
import com.kachidoki.ma.moneytime2.R;

/**
 * Created by mayiwei on 2017/2/17.
 */

public class DayChartFragment extends BaseLazyFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daychart,container,false);
        return view;
    }

    @Override
    public void onLazyLoad() {

    }



}
