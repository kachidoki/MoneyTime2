package com.kachidoki.ma.moneytime2.App.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
        if (getUserVisibleHint() && isPrepared && !isLazyLoaded) {
            onLazyLoad();
            isLazyLoaded = true;
        }
    }

    @UiThread
    public abstract void onLazyLoad();
}
