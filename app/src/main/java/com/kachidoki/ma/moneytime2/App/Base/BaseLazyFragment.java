package com.kachidoki.ma.moneytime2.App.Base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by mayiwei on 2017/2/16.
 */

public abstract class BaseLazyFragment extends Fragment {
    /**
     * 懒加载过
     */
    private boolean isLazyLoaded = false;

    private boolean isPrepared = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setupComponent(context);
    }

    protected abstract void setupComponent(Context context);

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isPrepared = true;
        lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        lazyLoad();
    }

    /**
     * 调用懒加载
     */

    private void lazyLoad() {
        if (getUserVisibleHint() && isPrepared) {
            onLazyLoad();
            isLazyLoaded = true;
        }
    }

    @UiThread
    public abstract void onLazyLoad();
}
