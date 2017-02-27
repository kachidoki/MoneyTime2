package com.kachidoki.ma.moneytime2.Model.Task;

import android.content.ContentValues;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.kachidoki.ma.moneytime2.R;

/**
 * Created by mayiwei on 2017/2/12.
 */

@AutoValue
public abstract class Task implements Parcelable{
    public static final String TABLE = "task_item";

    public static final String USER = "user";
    public static final String ID = "_id";
    public static final String TITLE = "title";
    public static final String STARTTIME = "startTime";
    public static final String ENDTIME = "endTime";
    public static final String YEAR = "year";
    public static final String DAY = "day";
    public static final String MONTH = "month";
    public static final String WEEKDAY = "weekDay";
    public static final String WEEKOFYEAR = "weekOfYear";
    public static final String DESCRIPTION = "description";
    public static final String COLOR = "color";
    public static final String COMPLETE = "complete";

    public static final int RED = 1;
    public static final int BLUE = 2;
    public static final int GREEN = 3;
    public static final int ORANGE = 4;
    public static final int YELLOW = 5;
    public static final int BLACK = 0;


    public abstract String title();
    public abstract float startTime();
    public abstract float endTime();
    public abstract int year();
    public abstract int day();
    public abstract int month();
    public abstract int weekDay();
    public abstract int weekOfYear();
    public abstract int color();
    @Nullable public abstract String description();
    public abstract boolean complete();

    public static final class Builder {
        private final ContentValues values = new ContentValues();

        public Builder title(String title) {
            values.put(TITLE, title);
            return this;
        }

        public Builder description(String description) {
            values.put(DESCRIPTION, description);
            return this;
        }

        public Builder startTime(float startTime) {
            values.put(STARTTIME, startTime);
            return this;
        }

        public Builder endTime(float endTime) {
            values.put(ENDTIME, endTime);
            return this;
        }

        public Builder year(int year) {
            values.put(YEAR, year);
            return this;
        }

        public Builder day(int day) {
            values.put(DAY, day);
            return this;
        }

        public Builder month(int month) {
            values.put(MONTH, month);
            return this;
        }

        public Builder weekDay(int weekDay) {
            values.put(WEEKDAY, weekDay);
            return this;
        }

        public Builder weekOfYear(int weekOfYear) {
            values.put(WEEKOFYEAR, weekOfYear);
            return this;
        }

        public Builder color(int color) {
            values.put(COLOR, color);
            return this;
        }

        public Builder complete(boolean complete) {
            values.put(COMPLETE, complete);
            return this;
        }

        public ContentValues build() {
            return values;
        }
    }

    public static Task createTask(String title, float startTime, float endTime, int year, int day, int month, int weekDay, int weekOfYear, int color, String description, boolean complete){
        return new AutoValue_Task(title,startTime,endTime,year,day,month,weekDay,weekOfYear,color,description,complete);
    }

    public static String getColorName(int i){
        switch (i){
            case Task.YELLOW:return "高效";
            case Task.ORANGE:return "不专心";
            case Task.GREEN:return "休息";
            case Task.BLUE:return "玩耍";
            case Task.RED:return "拖延";
            default:return "";
        }
    }

    public static int getColorResource(int color) {
        switch (color){
            case Task.YELLOW:return R.color.Yellow;
            case Task.ORANGE:return R.color.Orange;
            case Task.GREEN:return R.color.Green;
            case Task.BLUE:return R.color.Blue;
            case Task.RED:return R.color.Red;
            default:return R.color.Black;
        }
    }

    public static String getTimeString(float time){
        String tansTime;
        int a = 0;
        if(time - (int)time==0.5){
            a = (int)time;
            tansTime = a+":30";
            return tansTime;
        }else {
            tansTime = (int) time+":00";
            return tansTime;
        }
    }


}
