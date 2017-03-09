package com.kachidoki.ma.moneytime2.Utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by mayiwei on 2017/2/21.
 */

public class TimeTransform {
    public static final int SECOND = 60;
    public static final int HOUR = 3600;
    public static final int DAY = 86400;
    public static final int WEEK = 604800;


    Calendar currentTime;

    public TimeTransform(){
        currentTime=new GregorianCalendar();
        currentTime.setTime(new Date());
    }
    public TimeTransform(long timestamp){
        currentTime=new GregorianCalendar();
        currentTime.setTime(new Date(timestamp*1000));
    }
    public TimeTransform(int year, int month, int day){
        currentTime=new GregorianCalendar(year,month,day);
    }

    public TimeTransform(Date date){
        currentTime = new GregorianCalendar();
        currentTime.setTime(date);
    }

    public int getDay(){
        return currentTime.get(Calendar.DATE);
    }
    public int getMonth(){
        return currentTime.get(Calendar.MONTH)+1;
    }
    public int getYear(){
        return currentTime.get(Calendar.YEAR);
    }
    public int getWeekDay(){
        return currentTime.get(Calendar.DAY_OF_WEEK);
    }
    public int getWeekOfYear(){
        return currentTime.get(Calendar.WEEK_OF_YEAR);
    }
    public long getTimestamp(){
        return currentTime.getTime().getTime()/1000;
    }

    /**
     * 格式化输出日期
     * 年:y		月:M		日:d		时:h(12制)/H(24值)	分:m		秒:s		毫秒:S
     * @param formatString
     */
    public String toString(String formatString){
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        String date = format.format(currentTime.getTime());
        return date;
    }


    /**
     * 格式化解析日期文本
     * 年:y		月:M		日:d		时:h(12制)/H(24值)	分:m		秒:s		毫秒:S
     * @param formatString
     */
    public TimeTransform parse(String formatString,String content){
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        try {
            currentTime.setTime(format.parse(content));
            return this;
        } catch (ParseException e) {
            return null;
        }
    }

    public String toString(DateFormat format){
        long delta = (System.currentTimeMillis() - currentTime.getTime().getTime())/1000;
        return format.format(this,delta);
    }

    public interface DateFormat{
        String format(TimeTransform date, long delta);
    }

    public static class RecentDateFormat implements DateFormat{
        private String lastFormat;

        public RecentDateFormat() {
            this("MM-dd");
        }

        public RecentDateFormat(String lastFormat) {
            this.lastFormat = lastFormat;
        }

        @Override
        public String format(TimeTransform date, long delta) {
            if (delta>0){
                if (delta / TimeTransform.SECOND < 1){
                    return delta +"秒前";
                }else if (delta / TimeTransform.HOUR < 1){
                    return delta / TimeTransform.SECOND+"分钟前";
                }else if (delta / TimeTransform.DAY < 2 && new TimeTransform().getDay() == date.getDay()){
                    return delta / TimeTransform.HOUR+"小时前";
                }else if (delta / TimeTransform.DAY < 3 && new TimeTransform().getDay() == new TimeTransform(date.getTimestamp()+ TimeTransform.DAY).getDay()){
                    return "昨天"+date.toString("HH:mm");
                }else if (delta / TimeTransform.DAY < 4 && new TimeTransform().getDay() == new TimeTransform(date.getTimestamp()+ TimeTransform.DAY*2).getDay()){
                    return "前天"+date.toString("HH:mm");
                }else{
                    return date.toString(lastFormat);
                }
            }else{
                delta = -delta;
                if (delta / TimeTransform.SECOND < 1){
                    return delta +"秒后";
                }else if (delta / TimeTransform.HOUR < 1){
                    return delta / TimeTransform.SECOND+"分钟后";
                }else if (delta / TimeTransform.DAY > -2 && new TimeTransform().getDay() == date.getDay()){
                    return delta / TimeTransform.HOUR+"小时后";
                }else if (delta / TimeTransform.DAY > -3 && new TimeTransform().getDay() == new TimeTransform(date.getTimestamp()- TimeTransform.DAY).getDay()){
                    return "明天"+date.toString("HH:mm");
                }else if (delta / TimeTransform.DAY > -4 && new TimeTransform().getDay() == new TimeTransform(date.getTimestamp()- TimeTransform.DAY*2).getDay()){
                    return "后天"+date.toString("HH:mm");
                }else{
                    return date.toString(lastFormat);
                }
            }
        }
    }

}
