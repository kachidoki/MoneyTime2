package com.kachidoki.ma.moneytime2.Widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.kachidoki.ma.moneytime2.Model.Task.Task;
import com.kachidoki.ma.moneytime2.R;

import java.util.List;

/**
 * Created by mayiwei on 2017/2/15.
 */

public class WeekTable extends FrameLayout {

    public interface itemOnClickListener{
        void OnClick(Task task);
    }

    private int width = 0;
    private final int height = dip2px(60);
    private List<Task> data;
    private itemOnClickListener onClickListener;

    public void setOnClickListener(itemOnClickListener itemOnClickListener){
        this.onClickListener  = itemOnClickListener;
        requestLayout();
    }

    public WeekTable(Context context) {
        this(context, null, 0);
    }

    public WeekTable(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeekTable(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth()/7;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int margin = 2;
        if(data!=null) {
            for (int i = 0; i < data.size(); i++) {
                left = (data.get(i).weekDay() - 1) * width;
                right = left + width - margin;
                top = (int) ((data.get(i).startTime() - 7) * height);
                bottom = (int) (top + (data.get(i).endTime() - data.get(i).startTime()) * height - margin);
                getChildAt(i).layout(left, top, right, bottom);
            }
        }
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.Gray));
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(1);
        paint.setAlpha(80);
        for(int i =1;i<=16;i++){
            canvas.drawLine(1,i*height,width*7,i*height,paint);
        }


    }

    public void init(){
        if (data==null) return;
        for(final Task task:data){
            LinearLayout linearLayout = new LinearLayout(getContext());
//            if(data.get(i).getColor().equals("red")) textView.setBackgroundResource(R.layout.red_background);
            if(task.color()==1) linearLayout.setBackgroundColor(getResources().getColor(R.color.Red));
            if(task.color()==2) linearLayout.setBackgroundColor(getResources().getColor(R.color.Green));
            if(task.color()==3) linearLayout.setBackgroundColor(getResources().getColor(R.color.Blue));
//            if(task.color()==3) linearLayout.setBackgroundResource(R.drawable.table_red_background);
            if(task.color()==4) linearLayout.setBackgroundColor(getResources().getColor(R.color.Orange));
            if(task.color()==5) linearLayout.setBackgroundColor(getResources().getColor(R.color.Yellow));
            linearLayout.getBackground().setAlpha(200);
            linearLayout.setGravity(Gravity.CENTER);
            linearLayout.setPadding(dip2px(4),dip2px(6),dip2px(4),dip2px(6));

            TextView textView=new TextView(getContext());
            textView.setLayoutParams(new ViewGroup.LayoutParams(120, ViewGroup.LayoutParams.MATCH_PARENT));
            textView.setText(task.title());
            textView.setTextColor(getResources().getColor(R.color.White));
            textView.setTextSize(12);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setMaxLines(3);
            textView.setGravity(Gravity.CENTER);
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
//            textView.setBackgroundColor(getResources().getColor(R.color.Gray));

            linearLayout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener!=null){
                        onClickListener.OnClick(task);
                    }
                }
            });
            linearLayout.addView(textView);
            addView(linearLayout);
        }

    }


    private int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public void setData(List<Task> data){
        this.data= data;
        removeAllViews();
        init();
    }
}
