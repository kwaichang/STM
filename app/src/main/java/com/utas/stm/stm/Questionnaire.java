package com.utas.stm.stm;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.content.res.Resources;
import android.content.SharedPreferences;
import android.content.Intent;

public class Questionnaire extends Activity implements View.OnClickListener {

    Button btnSubmit;
    RadioGroup radioGroup;
    float total = 0;
    Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_questionnaire);

        btnSubmit=(Button)findViewById(R.id.quizSubmitBtn);

        btnSubmit.setOnClickListener(this);
    }

    public void onClick(View v) {

        if (v == btnSubmit){
            for(int i = 1; i < 19; i++)
            {
                String radioID = "quest" + i;
                int resID = getResources().getIdentifier(radioID, "id", getPackageName());
                RadioGroup radioGroup = (RadioGroup)findViewById(resID);
                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                View radioButton = radioGroup.findViewById(radioButtonID);
                int idx = radioGroup.indexOfChild(radioButton);
                idx++;
                total = total + idx;
            }
            total = total/90;
            total *= 100;
            int totalint = (int) Math.round(total);
            btnSubmit.setText("Score: "+totalint+"%");
            total = 0;
            Intent i=new Intent(Questionnaire.this,MainActivity.class);
            i.putExtra("Score",totalint);
            startActivity(i);
        }
    }

}
