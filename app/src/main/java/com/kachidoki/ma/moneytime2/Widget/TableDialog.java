package com.kachidoki.ma.moneytime2.Widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kachidoki.ma.moneytime2.Model.Task.Task;
import com.kachidoki.ma.moneytime2.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mayiwei on 2017/2/25.
 */

public class TableDialog extends Dialog {
    Context mContext;
    Task task;
    @BindView(R.id.tablediagle_title)
    TextView tablediagleTitle;
    @BindView(R.id.tablediagle_date)
    TextView tablediagleDate;
    @BindView(R.id.tablediagle_starttime)
    TextView tablediagleStarttime;
    @BindView(R.id.tablediagle_endtime)
    TextView tablediagleEndtime;
    @BindView(R.id.tablediagle_color)
    TextView tablediagleColor;
    @BindView(R.id.tablediagle_gotoshow)
    Button tablediagleGotoshow;
    @BindView(R.id.tablediagle_gotodo)
    Button tablediagleGotodo;

    public TableDialog(Context context, Task task) {
        this(context, 0);
        this.task = task;
        this.mContext = context;
    }

    public TableDialog(Context context, int themeResId) {
        super(context, R.style.MyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View layout = LayoutInflater.from(mContext).inflate(R.layout.dialog_table, null);
        ButterKnife.bind(this,layout);
        setContentView(layout);
        showDialog(task);
    }

    private void showDialog(Task task){
        tablediagleTitle.setText(task.title());
        tablediagleDate.setText(task.year()+"年"+task.month()+"月"+task.day()+"日");
        tablediagleStarttime.setText(task.startTime()+"");
        tablediagleEndtime.setText(task.endTime()+"");
        tablediagleColor.setText(Task.getColorName(task.color()));
        if (task.complete()){
            tablediagleGotodo.setVisibility(View.GONE);
        }
    }



    @OnClick({R.id.tablediagle_gotoshow, R.id.tablediagle_gotodo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tablediagle_gotoshow:
                break;
            case R.id.tablediagle_gotodo:
                break;
        }
    }
}
