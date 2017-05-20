package com.utas.stm.stm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TaskDB {
    private static final String TAG = "TaskDB";

    private static final String DATABASE_NAME 		= "taskdata";
    private static final int 	DATABASE_VERSION 	= 2;
    private static final String TASK_TABLE 		= "task_data";

    public static final String KEY_ROWID 			= "_id";
    public static final String KEY_PROPERTY_ID 		= "property_id";
    public static final String KEY_CODE 		    = "code";
    public static final String KEY_TASK 		    = "task";
    public static final String KEY_DATE             = "date";
    public static final String KEY_TIME             = "time";
    public static final String KEY_WEIGHTING		= "weighting";
    public static final String KEY_URGENCY			= "urgency";

    private static final String TASK_TABLE_CREATE = "create table "
            + TASK_TABLE
            + " (" + KEY_ROWID + " integer primary key autoincrement, "
            + KEY_PROPERTY_ID + " long integer not null, "
            + KEY_CODE  + " string not null, "
            + KEY_TASK  + " string not null, "
            + KEY_DATE + " string not null, "
            + KEY_TIME   + " string not null, "
            + KEY_WEIGHTING + " string not null, "
            + KEY_URGENCY + " string not null);";

    private SQLiteDatabase mDb;
    private final Context mCtx;

    public TaskDB(Context ctx) {
        this.mCtx = ctx;
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TASK_TABLE_CREATE);
    }

    public void onsaving(String code, String task, String date, String time, String weighting, String urgency) {
        SQLiteDatabase db = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_CODE, code);
        cv.put(KEY_TASK, task);
        cv.put(KEY_DATE, task);
        cv.put(KEY_TIME, task);
        cv.put(KEY_WEIGHTING, task);
        cv.put(KEY_URGENCY, task);
        db.insert(TASK_TABLE, null, cv);
    }
}

