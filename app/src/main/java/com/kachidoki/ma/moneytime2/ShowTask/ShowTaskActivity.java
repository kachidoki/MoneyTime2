package com.kachidoki.ma.moneytime2.ShowTask;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kachidoki.ma.moneytime2.App.App;
import com.kachidoki.ma.moneytime2.App.Base.BaseActivity;
import com.kachidoki.ma.moneytime2.DoTask.DoTaskActivity;
import com.kachidoki.ma.moneytime2.Model.Task.Task;
import com.kachidoki.ma.moneytime2.R;
import com.kachidoki.ma.moneytime2.ShowTask.Di.DaggerShowTaskComponent;
import com.kachidoki.ma.moneytime2.ShowTask.Di.ShowTaskModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mayiwei on 2017/2/25.
 */

public class ShowTaskActivity extends BaseActivity implements ShowTaskContract.View {

    @Inject
    ShowTaskContract.Presenter presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.showtask_toolbar_layout)
    CollapsingToolbarLayout showtaskToolbarLayout;
    @BindView(R.id.showtask_title)
    TextView showtaskTitle;
    @BindView(R.id.showtask_description)
    TextView showtaskDescription;
    @BindView(R.id.add_date)
    TextView addDate;
    @BindView(R.id.showtask_starttime)
    TextView showtaskStarttime;
    @BindView(R.id.showtask_endtime)
    TextView showtaskEndtime;
    @BindView(R.id.showtask_delete)
    Button showtaskDelete;
    @BindView(R.id.showtask_done)
    Button showtaskDone;
    @BindView(R.id.showtask_todo)
    Button showtaskTodo;
    @BindView(R.id.showtask_fab)
    FloatingActionButton showtaskFab;

    private static final String TASK = "task";

    private Task task;

    @Override
    protected void setupActivityComponent() {
        DaggerShowTaskComponent.builder()
                .appComponent(((App) getApplication()).getAppComponent())
                .showTaskModule(new ShowTaskModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showtask);
        ButterKnife.bind(this);

        task = getIntent().getParcelableExtra(TASK);
        setSupportActionBar(toolbar);
        showTaskDetil(task);
    }


    @OnClick({R.id.showtask_delete, R.id.showtask_done, R.id.showtask_todo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.showtask_delete:
                showDeleteDialog();
                break;
            case R.id.showtask_done:
                showDonDialog();
                break;
            case R.id.showtask_todo:
                finish();
                DoTaskActivity.gotoDoTask(this,task);
                break;
        }
    }

    private void showTaskDetil(Task task){
        if (task!=null){
            showtaskTitle.setText(task.title());
            showtaskStarttime.setText(task.startTime()+"");
            showtaskEndtime.setText(task.endTime()+"");
            showtaskToolbarLayout.setTitle(Task.getColorName(task.color()));
            showtaskToolbarLayout.setBackgroundResource(Task.getColorResource(task.color()));
            if (task.description().isEmpty()){
                showtaskDescription.setText("没有内容");
            }else {
                showtaskDescription.setText(task.description());
            }
            if (task.complete()){
                showtaskTodo.setVisibility(View.GONE);
                showtaskDone.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void showDeleteDialog() {
        new AlertDialog.Builder(this).setTitle("删除")
                .setMessage("确定要删除自己定下的任务吗")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.deleteTask(task.year()+"",task.day()+"",task.month()+"",task.startTime()+"",task.endTime()+"");
                        finish();
                    }
                }).show();
    }

    @Override
    public void showDonDialog() {
        new AlertDialog.Builder(this).setTitle("完成")
                .setMessage("确定已经完成任务了吗")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("完成", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.doneTask(task.year()+"",task.day()+"",task.month()+"",task.startTime()+"",task.endTime()+"");
                        finish();
                    }
                }).show();
    }

    public static void gotoShowTask(Context context,Task task){
        Intent intent = new Intent(context,ShowTaskActivity.class);
        intent.putExtra(TASK,task);
        context.startActivity(intent);
    }


}
