package com.kachidoki.ma.moneytime2.Widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mayiwei on 2017/2/17.
 */

public class TimePicker extends View {

    public static final String TAG = "TimePicker";

    private Context mContext;

    private int mViewHeight;
    private int mViewWidth;

    /** 中间条目宽度的 */
    private int mCenterItemHeight;

    private int mCenterY;

    private float mLastDownY;  //使用偏移量为了
    private float mMoveDistanceY = 0;
    /** 当前数组索引 */
    private int mCurrentDataIndex = 0;
    /** 绘制文本画笔 */
    private Paint mTextPaint;
    /** 绘制直线画笔 */
    private Paint mLinePaint;
    /** 文字最大尺寸 */
    private int mMaxTextSize;
    /** 间隔长度比率 */
    private float mGapRatio = 0;
    /** 是否允许滑动 */
    private boolean mIsTouchEnable = true;

    /** 是否允许下拉 */
    private boolean mIsTouchDownEnable = true;
    /** 是否允许上拉 */
    private boolean mIsTouchUpEnable = true;

    private List<String> mDataList = new ArrayList<>();

    public TimePicker(Context context) {
        super(context);
        init(context);
    }

    public TimePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        this.mContext = context;

        initData();

        mTextPaint = new Paint();
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setColor(0xff1f1f1f);

        mLinePaint = new Paint();
        mLinePaint.setStyle(Paint.Style.FILL);
        mLinePaint.setTextAlign(Paint.Align.CENTER);
        mLinePaint.setColor(0xff1f1f1f);
    }

    private void initData(){
        mDataList.clear();
        mDataList.add("7:00");
        mDataList.add("7:30");
        mDataList.add("8:00");
        mDataList.add("8:30");
        mDataList.add("9:00");
        mDataList.add("9:30");
        mDataList.add("10:00");
        mDataList.add("10:30");
        mDataList.add("11:00");
        mDataList.add("11:30");
        mDataList.add("12:00");
        mDataList.add("12:30");
        mDataList.add("13:00");
        mDataList.add("13:30");
        mDataList.add("14:00");
        mDataList.add("14:30");
        mDataList.add("15:00");
        mDataList.add("15:30");
        mDataList.add("16:00");
        mDataList.add("16:30");
        mDataList.add("17:00");
        mDataList.add("17:30");
        mDataList.add("18:00");
        mDataList.add("18:30");
        mDataList.add("19:00");
        mDataList.add("19:30");
        mDataList.add("20:00");
        mDataList.add("20:30");
        mDataList.add("21:00");
        mDataList.add("21:30");
        mDataList.add("22:00");
        mDataList.add("22:30");
        mDataList.add("23:00");
        mDataList.add("23:30");
        mDataList.add("24:00");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mViewHeight = getMeasuredHeight();
        mViewWidth = getMeasuredWidth();

        mCenterY = mViewHeight/2;
        mMaxTextSize = mViewHeight/8;
        mCenterItemHeight = mViewHeight/4;
        mGapRatio = 0.7f;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(mIsTouchEnable) {
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    onActionDown(event);
                    break;
                case MotionEvent.ACTION_MOVE:
                    onActionMove(event);
                    break;
                case MotionEvent.ACTION_UP:
                    onActionUp(event);
                    break;
            }
        }

        return true;
    }

    private void onActionDown(MotionEvent event){
        mLastDownY  = event.getY();
        //mMoveDistanceY = 0;
    }

    private void onActionMove(MotionEvent event){
        float currentY = event.getY();
        float diff = currentY - mLastDownY;

        if(mIsTouchUpEnable && mIsTouchDownEnable) {
            mMoveDistanceY += diff;
        }else if(!mIsTouchUpEnable && mIsTouchDownEnable){   //拉到第一个 只能向下滑动
            if(diff > 0){
                mMoveDistanceY += diff;
                mIsTouchUpEnable = true;
            }
        }else if(mIsTouchUpEnable && !mIsTouchDownEnable){   //拉到最后一个 只能向上滑动
            if(diff < 0){
                mMoveDistanceY += diff;
                mIsTouchDownEnable = true;
            }
        }

        mLastDownY = currentY;

        invalidate();
    }

    private void onActionUp(MotionEvent event){

        returnBack(mMoveDistanceY, 0);
    }

    private void drawText(Canvas canvas, int currentIndex, int position){

        float length = mCenterY + mMoveDistanceY;

        if(position == 0){                   //绘制当前中间的文字
            float scale = getScale((int) mMoveDistanceY);
            mTextPaint.setTextSize((float) (mMaxTextSize * scale));
            mTextPaint.setColor(getAlphaTextColor(scale));
            Paint.FontMetricsInt fontMetricsInt = mTextPaint.getFontMetricsInt();
            int diff = (fontMetricsInt.bottom + fontMetricsInt.top)/2;

            float baseY = length-diff;
            canvas.drawText(mDataList.get(currentIndex), mViewWidth / 2, baseY, mTextPaint);

            if(mMoveDistanceY > mCenterItemHeight/2){            //向下拉 index减
                if(mCurrentDataIndex > 0) {
                    mCurrentDataIndex--;
                }else{
                    mIsTouchDownEnable = false;                   //禁止下拉
                }
                mMoveDistanceY = 0;
            }else if(mMoveDistanceY < -mCenterItemHeight/2){    //向上拉 index加
                if(mCurrentDataIndex < mDataList.size()-1) {
                    mCurrentDataIndex++;
                }else{
                    mIsTouchUpEnable = false;                     //禁止上拉
                }
                mMoveDistanceY = 0;
            }
        }else {                                    //绘制上下文字
            int realIndex = currentIndex + position;

            if(realIndex >= 0 && realIndex < mDataList.size()){
                length = length + mCenterItemHeight * mGapRatio * position;

                float scale = getScale((int) length - mCenterY);
                mTextPaint.setTextSize((float) (mMaxTextSize * scale));
                mTextPaint.setColor(getAlphaTextColor(scale));
                Paint.FontMetricsInt fontMetricsInt = mTextPaint.getFontMetricsInt();
                int diff = (fontMetricsInt.bottom + fontMetricsInt.top)/2;

                canvas.drawText(mDataList.get(realIndex), mViewWidth/2 , length-diff, mTextPaint);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i=-3; i<=3; i++){
            drawText(canvas, mCurrentDataIndex, i);
        }
        canvas.drawLine(0, mCenterY-mCenterItemHeight/2, mViewWidth, mCenterY-mCenterItemHeight/2, mLinePaint);
        canvas.drawLine(0, mCenterY + mCenterItemHeight / 2, mViewWidth, mCenterY + mCenterItemHeight / 2, mLinePaint);
    }

    private float getScale(int lengthToCenter){
        float ret = (float) (1 - Math.pow(((float)lengthToCenter)/mCenterY, 2));
        if(ret < 0){
            ret = 0;
        }
        return ret;
    }

    private void returnBack(float start, float end){
        mIsTouchEnable = false;
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(start, end);
        valueAnimator.setDuration(100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mMoveDistanceY = (float) valueAnimator.getAnimatedValue();
                if (Math.abs(mMoveDistanceY) <= 3) {
                    mMoveDistanceY = 0;
                    mIsTouchEnable = true;
                }
                invalidate();
            }
        });
        valueAnimator.start();
    }

    /**
     * 根据比率设置字体颜色
     * @param scale
     * @return
     */
    private int getAlphaTextColor(float scale){
        int data = (int) (255 * scale);
        return (data << 24);
    }

    public float getTimeData(){
        return (mCurrentDataIndex/2)+7;
    }
}