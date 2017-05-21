package com.utas.stm.stm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TaskDB extends SQLiteOpenHelper {
    private static final String TAG = "TaskDB";

    private static final String DATABASE_NAME = "taskdata";
    private static final int DATABASE_VERSION = 2;

    public static final String TASK_TABLE = "task_data";
    public static final String KEY_ROWID = "_id";
    public static final String KEY_TASK_ID = "task_id";
    public static final String KEY_CODE = "code";
    public static final String KEY_TASK = "task";
    public static final String KEY_DATE = "date";
    public static final String KEY_TIME = "time";
    public static final String KEY_WEIGHTING = "weighting";
    public static final String KEY_URGENCY = "urgency";
    public SQLiteDatabase mDb;

    public TaskDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mDb = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String TASK_TABLE_CREATE = "CREATE TABLE "
                + TASK_TABLE
                + "( " + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + KEY_CODE + " STRING NOT NULL, "
                + KEY_TASK + " STRING NOT NULL, "
                + KEY_DATE + " STRING NOT NULL, "
                + KEY_TIME + " STRING NOT NULL, "
                + KEY_WEIGHTING + " STRING NOT NULL, "
                + KEY_URGENCY + " STRING NOT NULL);";
        Log.d("submit", "submit button pressed");
        Log.d("Create", "OnCreate");
        db.execSQL(TASK_TABLE_CREATE);
        Log.d("Create", "OnCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TASK_TABLE);
        onCreate(db);
    }
    public void onsaving(String code, String task, String date, String time, String weighting, String urgency) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_CODE, code);
        cv.put(KEY_TASK, task);
        cv.put(KEY_DATE, date);
        cv.put(KEY_TIME, time);
        cv.put(KEY_WEIGHTING, weighting);
        cv.put(KEY_URGENCY, urgency);
        mDb.insert(TASK_TABLE, null, cv);
    }
}



