package com.kachidoki.ma.moneytime2.Model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mayiwei on 2017/2/17.
 */

public class FakeData {
    public static List<Task> getTask(){
        List<Task> tasks = new ArrayList<>();
        tasks.add(new AutoValue_Task("标题", 7.5f,9,2017,12,12,2,26,1,"描述",true));
        tasks.add(new AutoValue_Task("标题", 8.5f,12,2017,12,12,3,26,2,"描述",true));
        tasks.add(new AutoValue_Task("标题标题标题标题标题标题标题标题标题标题标题标题", 9f,10,2017,12,12,4,26,4,"描述",true));
        tasks.add(new AutoValue_Task("标题", 13.5f,17,2017,12,12,5,26,3,"描述",true));
        tasks.add(new AutoValue_Task("标题", 7.5f,9,2017,12,12,6,26,1,"描述",true));
        tasks.add(new AutoValue_Task("标题标题标题标题", 7.5f,9,2017,12,12,7,26,2,"描述",true));
        tasks.add(new AutoValue_Task("标题标题", 7.5f,9,2017,12,12,1,26,4,"描述",true));
        tasks.add(new AutoValue_Task("标题标题标题", 7.5f,9,2017,12,12,2,26,1,"描述",true));
        return tasks;
    }
}
