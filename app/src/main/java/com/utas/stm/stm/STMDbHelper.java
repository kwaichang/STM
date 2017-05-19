package com.utas.stm.stm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by dave on 19/05/17.
 */

public class STMDbHelper extends SQLiteOpenHelper {
    private static final String TAG = "STMDbHelper";

	private static final String DATABASE_NAME 		= "taskdata";
	private static final int 	DATABASE_VERSION 	= 2;
	private static final String TASK_TABLE 	    	= "task_data";

	/*
	 * Some constant definitions that will be used in the application to look
	 * up data from the field names used in the database.
	 */
	public static final String KEY_ROWID 			= "_id";
	public static final String KEY_TASK_ID 		    = "task_id";
	public static final String KEY_UNIT_CODE		= "unit_code";
	public static final String KEY_TASK 		    = "task";
	public static final String KEY_WEIGHTING 		= "weighting";
	public static final String KEY_DUE_DATE 		= "due_date";
	public static final String KEY_DUE_TIME 		= "due_time";
	public static final String KEY_URGENCY 			= "urgency";

    private SQLiteDatabase db;
	private DatabaseHelper dbHelper;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TASK_TABLE + "(" +
                KEY_TASK_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                KEY_UNIT_CODE +" TEXT,"+
                KEY_WEIGHTING +" INTEGER,"+
                KEY_DUE_DATE +" DATE,"+
                KEY_DUE_TIME +" TIME"+
                KEY_URGENCY +" TEXT"+
                ")");
        Log.e("database", "Task table created");
    }
	/**
	 * open. Opens the database and writes the task data to it.
	 */
	public STMDbHelper open(boolean toRent, boolean refill) throws SQLException {
		Log.d(TAG, "open");
		//Check that the database object doesn't already exist.
		if (db == null) {
			db = dbHelper.getWritableDatabase();
		}

		//We reset the database each time the user switches between buy and rent.
		if (refill) {
			dbHelper.reset(db);
		}

		// The nulls mean that we want all columns in the table, all data, need
		// no grouping, and will take the default order.
		Cursor c = db.query(TASK_TABLE, null, null, null, null, null, null);
		if (c != null) { // Check that we have actually retrieved some data.
			if (c.getCount() == 0) { // i.e. if there are no rows in the table.
				Log.d(TAG, "The database is empty.");

				// Store the XML property data into a tree data structure.
				//populateXMLPropertyCatalog(toRent);
				// Write the tree data structure out to the SQLite database.
				writeTaskDataToDatabase();
			}
			c.close(); // Cursor management, to prevent an uncaught exception
			c = null;  // thrown by finalizer/IllegalStateException.
		}
		return this;
	} // open

	/**
	 * close. Closes this database. Call this at the end of use.
	 */
	public void close() {
		Log.d(TAG, "close");
		if (db != null) {
			db.close();
			db = null;	// This is important as garbage collection may
						// not have occured by the time the Activities
						// need to know whether they should recreated the
						// DB.
		}
	} // close

	/**
	 * writeTaskDataToDatabase. Inserts task data
	 *
	 */
	public boolean writeTaskDataToDatabase (String unitCode, String task, String weighting, String dueDate, String dueTime, String urgency) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_UNIT_CODE, unitCode);
        contentValues.put(KEY_TASK, task);
        contentValues.put(KEY_WEIGHTING, weighting);
        contentValues.put(KEY_DUE_DATE, dueDate);
        contentValues.put(KEY_DUE_TIME, dueTime);
        contentValues.put(KEY_URGENCY, urgency);
        long result = db.insert(TASK_TABLE, null, contentValues);
        if (result == -1) {
            return false;
        }
        else{
            db.close();
            Log.e("database", "data inserted ...");
            return true;
        }
    }

    public Integer deleteTask(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TASK_TABLE, "ID=?", new String[]{id});
    }

	/**
	 * getProperty. Retrieves a particular property from the database.
	 */
	public Cursor getProperty(String id) {
		Cursor c = db.query(TASK_TABLE, null, KEY_TASK_ID + "=" + id,
				null, null, null, null);
		return c;
	} // getProperty

	public Cursor getCursorToThePropertyList() {
		Cursor c = db.query(TASK_TABLE, null, null, null, null, null, null);
		return c;
	} // getCursorToThePropertyList


	/**
	 * DatabaseHelper class.
	 *
	 * Database helper class to manage connections with the database.
	 */
	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) { //This creates the database.
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		} // Constructor

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion,
				int newVersion) {
			Log.d(TAG, "DatabaseHelper onUpgrade");
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS " + TASK_TABLE);
			onCreate(db);
		} // onUpgrade

		public void reset(SQLiteDatabase db) {
			Log.d(TAG, "DatabaseHelper reset");
			db.execSQL("DROP TABLE IF EXISTS " + TASK_TABLE);
			onCreate(db);
		} // reset
	} // DatabaseHelper class.
} // STMDbHelper class
