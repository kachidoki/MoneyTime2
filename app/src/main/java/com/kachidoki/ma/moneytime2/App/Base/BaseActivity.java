package com.kachidoki.ma.moneytime2.App.Base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by mayiwei on 2017/2/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent();
    }

    protected abstract void setupActivityComponent();
}