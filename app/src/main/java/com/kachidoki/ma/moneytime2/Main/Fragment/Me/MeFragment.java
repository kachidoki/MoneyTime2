package com.kachidoki.ma.moneytime2.Main.Fragment.Me;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kachidoki.ma.moneytime2.App.Base.BaseLazyFragment;
import com.kachidoki.ma.moneytime2.Main.Fragment.Me.Di.MeModule;
import com.kachidoki.ma.moneytime2.Main.MainActivity;
import com.kachidoki.ma.moneytime2.R;

import javax.inject.Inject;

/**
 * Created by mayiwei on 2017/2/16.
 */

public class MeFragment extends BaseLazyFragment implements MeContract.View{

    @Inject
    MeContract.Presenter presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MainActivity)context).getMainComponent()
                .plus(new MeModule(this))
                .inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me,container,false);
        return view;
    }


    @Override
    public void onLazyLoad() {

    }
}
