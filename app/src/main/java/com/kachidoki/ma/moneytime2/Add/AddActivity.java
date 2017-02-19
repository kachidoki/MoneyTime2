package com.kachidoki.ma.moneytime2.Add;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kachidoki.ma.moneytime2.Add.Di.AddComponent;
import com.kachidoki.ma.moneytime2.Add.Di.AddModule;
import com.kachidoki.ma.moneytime2.Add.Di.DaggerAddComponent;
import com.kachidoki.ma.moneytime2.App.App;
import com.kachidoki.ma.moneytime2.App.Base.BaseActivity;
import com.kachidoki.ma.moneytime2.R;
import com.kachidoki.ma.moneytime2.Widget.ColorPicker;
import com.kachidoki.ma.moneytime2.Widget.TimePicker;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mayiwei on 2017/2/17.
 */

public class AddActivity extends BaseActivity implements AddContract.View {

    @Inject
    AddContract.Presenter presenter;

    AddComponent addComponent;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.add_title)
    EditText addTitle;
    @BindView(R.id.add_description)
    EditText addDescription;
    @BindView(R.id.add_date)
    TextView addDate;
    @BindView(R.id.add_startpick)
    TimePicker addStartpick;
    @BindView(R.id.add_endpick)
    TimePicker addEndpick;
    @BindView(R.id.add_colorpick)
    ColorPicker addColorpick;
    @BindView(R.id.add_undo)
    Button addUndo;
    @BindView(R.id.add_hasdo)
    Button addHasdo;
    @BindView(R.id.add_todo)
    Button addTodo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);
        presenter.start();

    }

    @Override
    protected void setupActivityComponent() {
        addComponent = DaggerAddComponent.builder()
                .appComponent(((App) getApplication()).getAppComponent())
                .addModule(new AddModule(this, this))
                .build();
        addComponent.inject(this);
    }


    @OnClick({R.id.add_date, R.id.add_undo, R.id.add_hasdo, R.id.add_todo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_date:
                showPickTime();
                break;
            case R.id.add_undo:
                presenter.checkInput(getTitleText(),getstartTime(),getendTime(),getColor(),getDescription(),false);
                break;
            case R.id.add_hasdo:
                presenter.checkInput(getTitleText(),getstartTime(),getendTime(),getColor(),getDescription(),true);
                break;
            case R.id.add_todo:
                break;
        }
    }

    @Override
    public void setDateText(int year, int month, int day) {
        addDate.setText(year + "年" + month + "月" + day + "日");
    }

    @Override
    public float getstartTime() {
        return addStartpick.getTimeData();
    }

    @Override
    public float getendTime() {
        return addEndpick.getTimeData();
    }

    @Override
    public String getTitleText() {
        return addTitle.getText().toString();
    }

    @Override
    public String getDescription() {
        return addDescription.getText().toString();
    }

    @Override
    public int getColor() {
        return addColorpick.getPickColor();
    }

    @Override
    public void showTimeIsNotOk() {
        Toast.makeText(AddActivity.this,"这段时间已经被利用了哦",Toast.LENGTH_LONG).show();

    }

    @Override
    public void showNoColor() {
        Toast.makeText(AddActivity.this,"请选择效率",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showNoTitle() {
        Toast.makeText(AddActivity.this,"请添加标题",Toast.LENGTH_LONG).show();
    }

    @Override
    public void goToDo() {

    }

    private void showPickTime(){
        new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                presenter.setDateTime(year, monthOfYear, dayOfMonth);
            }
        }, presenter.getCurrentDate().get(Calendar.YEAR), presenter.getCurrentDate().get(Calendar.MONTH),presenter.getCurrentDate().get(Calendar.DAY_OF_MONTH)).show();

    }
}
