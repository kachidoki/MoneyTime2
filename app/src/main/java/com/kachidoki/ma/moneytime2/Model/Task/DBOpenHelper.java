package com.kachidoki.ma.moneytime2.Model.Task;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mayiwei on 2017/2/12.
 */

public class DBOpenHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;

    private static final String CREATE_TABLE = ""
            + "CREATE TABLE " + Task.TABLE + "("
            + Task.ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
            + Task.TITLE + " TEXT NOT NULL,"
            + Task.DESCRIPTION + " TEXT,"
            + Task.STARTTIME + " INTEGER NOT NULL,"
            + Task.ENDTIME + " INTEGER NOT NULL,"
            + Task.DAY + " INTEGER NOT NULL,"
            + Task.YEAR + " INTEGER NOT NULL,"
            + Task.MONTH + " INTEGER NOT NULL,"
            + Task.WEEKDAY + " INTEGER NOT NULL,"
            + Task.WEEKOFYEAR + " INTEGER NOT NULL,"
            + Task.COLOR + " INTEGER NOT NULL,"
            + Task.COMPLETE + " INTEGER NOT NULL DEFAULT 0"
            + ")";

    public DBOpenHelper(Context context) {
        super(context, Task.TABLE, null , VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
