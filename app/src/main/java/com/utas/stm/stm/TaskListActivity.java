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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;



public class TaskListActivity extends Activity implements View.OnClickListener {
    TextView txtData;
    Button delBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        //delBtn = (Button) findViewById(R.id.delete_btn);
       // delBtn.setOnClickListener(this);

        TaskDB helper = new TaskDB(this);
        Cursor cursor = helper.getTasks();
        //txtData = (TextView) findViewById(R.id.task);
        String[] fromFieldNames = new String[]{helper.KEY_CODE, helper.KEY_TASK, helper.KEY_DATE};
        int[] viewID = new int[]{R.id.unit_code, R.id.task, R.id.due_date};
        SimpleCursorAdapter cursorAdapter;
        cursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.list_layout, cursor, fromFieldNames, viewID, 0);
        ListView list = (ListView) findViewById(R.id.list_view);
        list.setAdapter(cursorAdapter);
    }

    public void onClick(View v) {
        if (v == delBtn)
        {

        }
    }
}