package com.utas.stm.stm;

import android.os.Bundle;
import android.app.Activity;
import com.utas.stm.stm.TaskDB;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.utas.stm.stm.TaskDB;
import android.database.Cursor;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;


public class TaskListActivity extends Activity {
    TextView txtData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        TaskDB helper = new TaskDB(this);
        Log.d("Here", "Here");
        String text[] = helper.getTasks();
        helper.close();
        Log.d("Here", "Here");
        txtData = (TextView) findViewById(R.id.task);
        Log.d("Here", "Here");
        txtData.setText(text[0]);
        // set text to your TextView
    }
}