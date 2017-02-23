package com.kachidoki.ma.moneytime2.AddStatus;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.kachidoki.ma.moneytime2.AddStatus.Di.AddStatusModule;
import com.kachidoki.ma.moneytime2.AddStatus.Di.DaggerAddStatusComponent;
import com.kachidoki.ma.moneytime2.App.App;
import com.kachidoki.ma.moneytime2.App.Base.BaseActivity;
import com.kachidoki.ma.moneytime2.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mayiwei on 2017/2/22.
 */

public class AddStatusActivity extends BaseActivity implements AddStatusContract.View {

    @Inject
    AddStatusContract.Presenter presenter;
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
        DaggerAddStatusComponent.builder()
                .appComponent(((App) getApplication()).getAppComponent())
                .addStatusModule(new AddStatusModule(this))
                .build().inject(this);
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
                presenter.send(addstatusMessage.getText().toString(),null);
                break;
        }
    }

    @Override
    public void showMessageNo() {
        Toast.makeText(AddStatusActivity.this,"发送不能为空",Toast.LENGTH_LONG).show();
    }

    @Override
    public void sendScuess() {
        Toast.makeText(AddStatusActivity.this,"发送成功",Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void sendFail(Exception e) {
        Toast.makeText(AddStatusActivity.this,"发送失败",Toast.LENGTH_LONG).show();
    }
}
