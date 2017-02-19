package com.kachidoki.ma.moneytime2.Add;

import com.kachidoki.ma.moneytime2.Model.Task.Source.TasksDataSource;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by mayiwei on 2017/2/17.
 */

public class AddPresenter implements AddContract.Presenter {


    private int Year=0,Month=0,Day=0,WeekOfYear=0,WeekDay=0;
    final Calendar currentTime = Calendar.getInstance();
    private AddContract.View view;
    private TasksDataSource dataRespository;

    public AddPresenter(AddContract.View view, TasksDataSource dataSource){
        this.view = view;
        this.dataRespository = dataSource;
    }

    @Override
    public void start() {
        SetChinaTime(currentTime);
        view.setDateText(Year,Month,Day);
    }


    @Override
    public void saveTask(String title, float startTime, float endTime, int year, int day, int month, int weekDay, int weekOfYear, int color, String description, boolean complete) {
        dataRespository.saveTask(title,startTime,endTime,year,day,month,weekDay,weekOfYear,color,description,complete);
        view.finish();
    }

    @Override
    public void checkInput(String title, float startTime, float endTime,int color,String description, boolean complete) {
        if(title.isEmpty()){
            view.showNoTitle();
        }else {
            if (startTime==endTime){
                view.showTimeIsNotOk();
            }else {
                if(isDouble()==true){
                    view.showTimeIsNotOk();
                }else {
                    if (color==-1){
                        view.showNoColor();
                    }else {
                        saveTask(title,startTime,endTime,Year,Day,Month,WeekDay,WeekOfYear,color,description,complete);
                    }
                }

            }
        }
    }

    @Override
    public void setDateTime(int year, int month, int day) {
        Calendar cal = new GregorianCalendar(Locale.CHINA);
        Date date = new GregorianCalendar(year,month,day).getTime();
        cal.setTime(date);
        Year = cal.get(Calendar.YEAR);
        Month = cal.get(Calendar.MONTH)+1;
        Day = cal.get(Calendar.DAY_OF_MONTH);
        WeekDay = cal.get(Calendar.DAY_OF_WEEK);
        WeekOfYear = cal.get(Calendar.WEEK_OF_YEAR);
        view.setDateText(year,month,day);
    }

    @Override
    public Calendar getCurrentDate() {
        return currentTime;
    }


    private boolean isDouble(){
        return false;
    }

    private void SetChinaTime(Calendar c){
        Calendar cal = new GregorianCalendar(Locale.CHINA);
        Date date = new GregorianCalendar(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)).getTime();
        cal.setTime(date);
        Year = cal.get(Calendar.YEAR);
        Month = cal.get(Calendar.MONTH)+1;
        Day = cal.get(Calendar.DAY_OF_MONTH);
        WeekDay = cal.get(Calendar.DAY_OF_WEEK);
        WeekOfYear = cal.get(Calendar.WEEK_OF_YEAR);
    }

}
