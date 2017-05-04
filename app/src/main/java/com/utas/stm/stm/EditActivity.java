package com.utas.stm.stm;

import android.os.Bundle;
import android.app.Activity;

public class EditActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
