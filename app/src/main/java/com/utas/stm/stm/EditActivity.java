package com.utas.stm.stm;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.AlphabeticIndex;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.utas.stm.stm.TaskDB;
import android.content.ContentValues;
import android.content.Context;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnDatePicker, btnTimePicker, btnSubmit;
    EditText txtDate, txtTime, txtCode, txtWeighting, txtTask;
    String stringDate, stringTime, stringCode, stringWeighting, stringTask, stringUrgency;
    Spinner urgency;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Bundle bundle = getIntent().getExtras();

        btnDatePicker=(Button)findViewById(R.id.editSetDateBtn);
        btnTimePicker=(Button)findViewById(R.id.editSetTimeBtn);
        btnSubmit=(Button)findViewById(R.id.editSubmitBtn);

        txtDate=(EditText)findViewById(R.id.editDueDate);
        txtTime=(EditText)findViewById(R.id.editDueTime);
        txtTask=(EditText)findViewById(R.id.editTask);
        txtCode=(EditText)findViewById(R.id.editUnitCode);
        txtWeighting=(EditText)findViewById(R.id.editWeighting);
        urgency=(Spinner)findViewById(R.id.editImportanceSpinner);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        if (this.getIntent().getExtras() != null && this.getIntent().getExtras().containsKey("id"))
        {
            Log.d(bundle.getString("id"), "Here!!!");
            TaskDB helper = new TaskDB(this);
            String[] id = new String[1];
            id[0] = bundle.getString("id");
            String[] data = helper.getRow(bundle.getString("id"));
            Log.d("Hueh", "Here!!!!!!!");
            txtCode.setText(data[0]);
            txtTask.setText(data[1]);
            txtDate.setText(data[2]);
            txtTime.setText(data[3]);
            txtWeighting.setText(data[4]);
            helper.deleteRow(id);
        }
    }

    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {
            Log.d("submit", "time set button pressed");
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(pad(hourOfDay) + ":" + pad(minute));
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

        if (v == btnSubmit) {
            TaskDB helper = new TaskDB(this);
            SQLiteDatabase db = helper.getWritableDatabase();

            stringCode = txtCode.getText().toString();
            stringTask = txtTask.getText().toString();
            stringDate = txtDate.getText().toString();
            stringTime= txtTime.getText().toString();
            stringWeighting = txtWeighting.getText().toString();
            stringUrgency = urgency.getSelectedItem().toString();

            ContentValues cv = new ContentValues();
            cv.put(TaskDB.KEY_CODE, stringCode);
            cv.put(TaskDB.KEY_TASK, stringTask);
            cv.put(TaskDB.KEY_DATE, stringDate);
            cv.put(TaskDB.KEY_TIME, stringTime);
            cv.put(TaskDB.KEY_WEIGHTING, stringWeighting);
            cv.put(TaskDB.KEY_URGENCY, stringUrgency);
            db.insert(TaskDB.TASK_TABLE, null, cv);
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }
}
