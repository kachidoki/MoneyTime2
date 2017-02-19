package com.kachidoki.ma.moneytime2.Main.Fragment.Host;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kachidoki.ma.moneytime2.Model.Task.Task;
import com.kachidoki.ma.moneytime2.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mayiwei on 2017/2/19.
 */

public class ViewHolderHead extends HostBaseViewHolder {


    @BindView(R.id.host_topimg)
    ImageView hostTopimg;
    @BindView(R.id.host_monthAndYear)
    TextView hostMonthAndYear;
    @BindView(R.id.host_day)
    TextView hostDay;
    @BindView(R.id.host_weekDay)
    TextView hostWeekDay;
    private int Year=0,Month=0,Day=0,WeekOfYear=0,WeekDay=0;

    public ViewHolderHead(ViewGroup viewGroup) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.viewholder_host_recycler_head, viewGroup, false));
        ButterKnife.bind(this, itemView);
        SetTime(Calendar.getInstance());
    }

    @Override
    public void bind(Task task) {
        setTextviewTime();
    }

    public void SetTime(Calendar c){
        Calendar cal = new GregorianCalendar(Locale.CHINA);
        Date date = new GregorianCalendar(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)).getTime();
        cal.setTime(date);
        Year = cal.get(Calendar.YEAR);
        Month = cal.get(Calendar.MONTH)+1;
        Day = cal.get(Calendar.DAY_OF_MONTH);
        WeekDay = cal.get(Calendar.DAY_OF_WEEK);
        WeekOfYear = cal.get(Calendar.WEEK_OF_YEAR);
    }

    public void setTextviewTime(){
        hostMonthAndYear.setText(Month+"/"+Year);
        hostDay.setText(Day+"");
        if (WeekDay == 2) hostWeekDay.setText("星期一");
        if (WeekDay == 3) hostWeekDay.setText("星期二");
        if (WeekDay == 4) hostWeekDay.setText("星期三");
        if (WeekDay == 5) hostWeekDay.setText("星期四");
        if (WeekDay == 6) hostWeekDay.setText("星期五");
        if (WeekDay == 7) hostWeekDay.setText("星期六");
        if (WeekDay == 1) hostWeekDay.setText("星期日");
    }
}
