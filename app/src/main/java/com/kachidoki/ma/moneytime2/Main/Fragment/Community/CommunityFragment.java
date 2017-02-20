package com.kachidoki.ma.moneytime2.Main.Fragment.Community;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kachidoki.ma.moneytime2.App.Base.BaseLazyFragment;
import com.kachidoki.ma.moneytime2.Main.Fragment.Community.Di.CommunityModule;
import com.kachidoki.ma.moneytime2.Main.MainActivity;
import com.kachidoki.ma.moneytime2.R;

import javax.inject.Inject;

/**
 * Created by mayiwei on 2017/2/16.
 */

public class CommunityFragment extends BaseLazyFragment implements CommunityContract.View{

    @Inject
    CommunityContract.Presenter presenter;

    @Override
    protected void setupComponent(Context context) {
        ((MainActivity)context).getMainComponent()
                .plus(new CommunityModule(this))
                .inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community,container,false);
        return view;
    }


    @Override
    public void onLazyLoad() {

    }
}
