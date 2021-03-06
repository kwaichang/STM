package com.utas.stm.stm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.R.attr.button;

public class MainActivity extends AppCompatActivity {
TextView scoreText;

    TextView score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = getIntent().getExtras();
        if( bundle != null)
        {
            int score = bundle.getInt("Score");
            scoreText = (TextView) findViewById(R.id.editTextScore);
            String text = String.valueOf(score) + "%";
            scoreText.setText(text);
        }


    }

    public void onUrl(View v) {

        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://blogs.utas.edu.au/student-advice/tools-and-resources-for-time-management/"));
        startActivity(i);

        //Log.d("%s", "btnInfo pressed");
    }

    public void editActivity(View v) {
        startActivity(new Intent(this, EditActivity.class));
    }
    public void questionnaireActivity(View v) { startActivity(new Intent(this, Questionnaire.class));}
    public void listActivity(View v) { startActivity(new Intent(this, TaskListActivity.class));}


}
