package com.kachidoki.ma.moneytime2.Widget;

import android.content.Context;
import android.graphics.Color;
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
 * Created by mayiwei on 2017/2/16.
 */

public class PieChartView extends PieChart {

    private List<Task> taskList;

    public PieChartView(Context context) {
        this(context,null);
    }

    public PieChartView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PieChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setChart();
        taskList = new ArrayList<>();
//        setTaskData(FakeData.getTask());
    }


    private void setChart(){
        //使用百分比
        this.setUsePercentValues(true);
        //描述
//        mChart.setDescription();
        //设置手拖动的速度
        this.setDragDecelerationFrictionCoef(0.98f);
        //设置是否是环状
        this.setDrawHoleEnabled(true);
        this.setTransparentCircleColor(Color.WHITE);
        this.setTransparentCircleAlpha(110);
        this.setHoleRadius(50f);
        this.setTransparentCircleRadius(58f);
        this.setDrawCenterText(true);
        this.setRotationAngle(90);
        // enable rotation of the chart by touch 设置是否可旋转
        this.setRotationEnabled(true);
        this.setCenterText("今天的\n效率");
        this.setCenterTextSize(20);
        this.setCenterTextColor(R.color.Gray);
    }



    public void setTaskData(List<Task> tasks) {
        if (tasks==null) return;
        taskList.clear();
        taskList = tasks;

        List<PieEntry> yValues = new ArrayList<>();
        List<Integer> colors = new ArrayList<>();
        colors.clear();
        yValues.clear();

        float quarterly1 = 0;
        float quarterly2 = 0;
        float quarterly3 = 0;
        float quarterly4 = 0;
        float quarterly5 = 0;
        float[] quarterlys ={quarterly1,quarterly2,quarterly3,quarterly4,quarterly5};
        for (int i=0;i<tasks.size();i++){
            if(tasks.get(i).color()==1) quarterlys[0] = quarterlys[0]-tasks.get(i).startTime()+tasks.get(i).endTime();
            if(tasks.get(i).color()==2) quarterlys[1] = quarterlys[1]-tasks.get(i).startTime()+tasks.get(i).endTime();
            if(tasks.get(i).color()==3) quarterlys[2] = quarterlys[2]-tasks.get(i).startTime()+tasks.get(i).endTime();
            if(tasks.get(i).color()==4) quarterlys[3] = quarterlys[3]-tasks.get(i).startTime()+tasks.get(i).endTime();
            if(tasks.get(i).color()==5) quarterlys[4] = quarterlys[4]-tasks.get(i).startTime()+tasks.get(i).endTime();
        }
        for(int i=0;i<=4;i++){
            if(quarterlys[i]!=0.0) {
                yValues.add(new PieEntry(quarterlys[i], getColorName(i)));

            }
        }
        PieDataSet dataSet = new PieDataSet(yValues, "");
        dataSet.setSliceSpace(3f);//设置饼图的距离
        dataSet.setSelectionShift(5f);

        // add a lot of colors
        if(quarterlys[0]!=0.0) colors.add(Color.argb(220,255, 193, 7));
        if(quarterlys[1]!=0.0) colors.add(Color.argb(220,255, 87, 34));
        if(quarterlys[2]!=0.0) colors.add(Color.argb(220,0, 150, 136));
        if(quarterlys[3]!=0.0) colors.add(Color.argb(220,33, 150, 243));
        if(quarterlys[4]!=0.0) colors.add(Color.argb(220,255, 68, 68));

        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        this.setData(data);
        this.highlightValues(null);
        invalidate();
    }

    private String getColorName(int i){
        switch (i){
            case 1:return "高效";
            case 2:return "不专心";
            case 3:return "休息";
            case 4:return "玩耍";
            case 5:return "拖延";
            default:return "";
        }
    }


}
