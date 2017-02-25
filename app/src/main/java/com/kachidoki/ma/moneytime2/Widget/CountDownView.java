package com.kachidoki.ma.moneytime2.Widget;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.kachidoki.ma.moneytime2.Model.Task.Task;
import com.kachidoki.ma.moneytime2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mayiwei on 2017/2/24.
 */

public class CountDownView extends PieChart {

    private CountDownTimer timer;
    private long alltime;
    private boolean isfinsh = false;
    private Callback callback;

    public interface Callback{
        void OnFinish();
    }

    public CountDownView(Context context) {
        this(context,null);
    }

    public CountDownView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CountDownView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setChart();
    }

    public void setCallback(Callback callback){
        this.callback = callback;
    }

    private void setChart(){
        this.setDragDecelerationFrictionCoef(0.98f);
        //设置是否是环状
        this.setDrawHoleEnabled(true);
        this.setTransparentCircleColor(Color.WHITE);
        this.setTransparentCircleAlpha(110);
        this.setHoleRadius(50f);
        this.setTransparentCircleRadius(58f);
        this.setDrawCenterText(true);
        this.setRotationAngle(90);
        this.setCenterText("执行任务");
        this.setCenterTextSize(20);
        this.setCenterTextColor(R.color.Gray);
    }


    private void setTime(long donwntime) {
        List<PieEntry> yValues = new ArrayList<>();
        List<Integer> colors = new ArrayList<>();
        colors.clear();
        yValues.clear();


        long lefttime = alltime - donwntime;

        yValues.add(new PieEntry(donwntime,"已完成"));
        yValues.add(new PieEntry(lefttime, "剩余时间"+lefttime));


        PieDataSet dataSet = new PieDataSet(yValues, "");
        dataSet.setSliceSpace(3f);//设置饼图的距离
        dataSet.setSelectionShift(5f);

        colors.add(Color.argb(220,255, 193, 7));
        colors.add(Color.argb(220,255, 87, 34));
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        this.setData(data);
        this.highlightValues(null);
        invalidate();
    }


    public void startCountDown(final long time){
        alltime = time;
        timer = new CountDownTimer(time*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.e("PIEVIEW", "onTick  " + millisUntilFinished / 1000);
                setTime(millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                isfinsh = true;
                if (callback!=null){
                    callback.OnFinish();
                }
            }
        };
        timer.start();
    }


    public boolean isFinish(){
        return isfinsh;
    }

    public void finish(){
        if (timer!=null){
            timer.onFinish();
        }
    }


    public void cancel(){
        if (timer!=null){
            timer.cancel();
        }
    }


}
