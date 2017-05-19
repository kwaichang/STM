package com.utas.stm.stm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.R.attr.button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void editActivity(View v) {
        startActivity(new Intent(this, EditActivity.class));
    }
    public void viewActivity(View v) {
        startActivity(new Intent(this, ViewActivity.class));
    }
    public void questionnaireActivity(View v) { startActivity(new Intent(this, Questionnaire.class));}


}