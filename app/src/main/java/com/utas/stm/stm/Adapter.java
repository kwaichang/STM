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
import android.widget.CursorAdapter;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;


public class Adapter extends CursorAdapter {
    // Cursor cursor;
    Context context;

    public Adapter(Context context, int RId, Cursor cursor) {
        super(context, cursor);
        // this.cursor = cursor;
        this.context = context;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView code = (TextView) view.findViewById(R.id.textUnitCode);
        code.setText(cursor.getString(1));
        TextView task = (TextView) view.findViewById(R.id.textTask);
        task.setText(cursor.getString(2));
        TextView date = (TextView) view.findViewById(R.id.textDueDate);
        date.setText(cursor.getString(3));
        TextView time = (TextView) view.findViewById(R.id.textDueTime);
        time.setText(cursor.getString(4));
        TextView weighting = (TextView) view.findViewById(R.id.textWeighting);
        weighting.setText(cursor.getString(5));
        TextView urgency = (TextView) view.findViewById(R.id.textUrgency);
        urgency.setText(cursor.getString(6));
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.activity_task_list, parent, false);
        return v;
    }
}
