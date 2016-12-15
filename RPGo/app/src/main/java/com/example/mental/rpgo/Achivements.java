package com.example.mental.rpgo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Achivements extends AppCompatActivity {

    TextView TV_timePassedStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achivements);

        //Initialize widgets
        TV_timePassedStatus = (TextView) findViewById(R.id.TV_timePassedStatus);

        if(Global.getAchivement_timePassed() != true) {
            TV_timePassedStatus.setTextColor(Color.RED);
        }else {
            TV_timePassedStatus.setTextColor(Color.GREEN);
        }
    }
}
