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
import android.content.Intent;



public class TaskListActivity extends Activity implements View.OnClickListener {
    TextView txtData;
    Button delBtn;
    TaskDB helper;
    SimpleCursorAdapter cursorAdapter;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        helper = new TaskDB(this);
        Cursor cursor = helper.getTasks();
        String[] fromFieldNames = new String[]{helper.KEY_CODE, helper.KEY_TASK, helper.KEY_DATE, helper.KEY_ROWID};
        int[] viewID = new int[]{R.id.unit_code, R.id.task, R.id.due_date, R.id.delete_btn};
        cursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.list_layout, cursor, fromFieldNames, viewID, 0);
        list = (ListView) findViewById(R.id.list_view);
        list.setAdapter(cursorAdapter);
    }

    public void onClick(View v)
    {
        Button b = (Button)v;
        String buttonText = b.getText().toString();
        String[] id;
        id = new String[1];
        id[0] = buttonText;
        Log.d(id[0], "Here");
        helper.deleteRow(id);
        helper = new TaskDB(this);
        Cursor cursor = helper.getTasks();
        String[] fromFieldNames = new String[]{helper.KEY_CODE, helper.KEY_TASK, helper.KEY_DATE, helper.KEY_ROWID};
        int[] viewID = new int[]{R.id.unit_code, R.id.task, R.id.due_date, R.id.delete_btn};
        cursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.list_layout, cursor, fromFieldNames, viewID, 0);
        list = (ListView) findViewById(R.id.list_view);
        list.setAdapter(cursorAdapter);

    }
}