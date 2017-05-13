package com.utas.stm.stm;

import android.support.v7.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        // Get a Spinner and bind it to an ArrayAdapter that
        // references a String array.
        Spinner importanceSpinner = (Spinner) findViewById(R.id.editImportanceSpinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this, R.array.importance_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        importanceSpinner.setAdapter(adapter);
    }
}
