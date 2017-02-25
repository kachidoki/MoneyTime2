package com.kachidoki.ma.moneytime2.DoTask;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kachidoki.ma.moneytime2.App.Base.BaseActivity;
import com.kachidoki.ma.moneytime2.Model.Task.Task;
import com.kachidoki.ma.moneytime2.R;
import com.kachidoki.ma.moneytime2.Widget.CountDownView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mayiwei on 2017/2/25.
 */

public class DoTaskActivity extends BaseActivity implements DoTaskContract.View, Runnable {


    @Inject
    DoTaskContract.Presenter presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.dotask_time)
    TextView dotaskTime;
    @BindView(R.id.dotask_data)
    TextView dotaskData;
    @BindView(R.id.dotask_countdown)
    CountDownView dotaskCountdown;
    @BindView(R.id.dotask_start)
    Button dotaskStart;
    @BindView(R.id.dotask_drop)
    Button dotaskDrop;
    @BindView(R.id.dotask_down)
    Button dotaskDown;
    @BindView(R.id.dotask_done)
    TextView dotaskDone;

    private Handler timeHandler;
    private Task task;


    @Override
    protected void setupActivityComponent() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dotask);
        ButterKnife.bind(this);

        timeHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                presenter.getNowTime();
            }
        };

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        dotaskCountdown.cancel();

    }

    @Override
    public void showNowTime(String time) {
        dotaskTime.setText(time);
    }

    @Override
    public void showData(String data) {
        dotaskData.setText(data);
    }

    @Override
    public void showCoutDown(long time) {
        dotaskStart.setVisibility(View.GONE);
        dotaskCountdown.setVisibility(View.VISIBLE);
        dotaskCountdown.startCountDown(time);
        dotaskDone.setVisibility(View.GONE);
    }



    @Override
    public void showDown() {
        dotaskCountdown.setVisibility(View.GONE);
        dotaskStart.setVisibility(View.GONE);
        dotaskDone.setVisibility(View.VISIBLE);
    }

    @Override
    public void startThread() {
        new Thread(this).start();
    }

    @OnClick({R.id.dotask_start, R.id.dotask_drop, R.id.dotask_down})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dotask_start:
                presenter.startDoTime(task.startTime(), task.endTime());
                break;
            case R.id.dotask_drop:
                if (!dotaskCountdown.isFinish()) {
                    showDialog();
                } else {
                    finish();
                }
                break;
            case R.id.dotask_down:
                if (dotaskCountdown.isFinish()) finish();
                break;
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                timeHandler.sendMessage(timeHandler.obtainMessage());
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void showDialog() {
        new AlertDialog.Builder(this).setTitle("放弃")
                .setMessage("时间还没到哦，确定放弃吗")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("放弃", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).show();
    }
}
