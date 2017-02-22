package com.kachidoki.ma.moneytime2.AddStatus;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.kachidoki.ma.moneytime2.App.Base.BaseActivity;
import com.kachidoki.ma.moneytime2.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mayiwei on 2017/2/22.
 */

public class AddStatusActivity extends BaseActivity implements AddStatusContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.addstatus_message)
    EditText addstatusMessage;
    @BindView(R.id.addstatus_img)
    ImageView addstatusImg;
    @BindView(R.id.addstatus_send)
    Button addstatusSend;

    @Override
    protected void setupActivityComponent() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstatus);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.addstatus_img, R.id.addstatus_send})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addstatus_img:
                break;
            case R.id.addstatus_send:
                break;
        }
    }
}
