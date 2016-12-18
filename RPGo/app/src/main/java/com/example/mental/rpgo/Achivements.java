package com.example.mental.rpgo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class Achivements extends AppCompatActivity {

    TextView TV_Ach_1_status, TV_Ach_2_status, TV_Ach_3_status, TV_Ach_4_status;
    List<AchivementObject> achivementsList;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achivements);

        //Initialize widgets and variables
        db = new DatabaseHelper(this);
        achivementsList = db.getAll();
        TV_Ach_1_status = (TextView) findViewById(R.id.TV_Ach_1_status);
        TV_Ach_2_status = (TextView) findViewById(R.id.TV_Ach_2_status);
        TV_Ach_3_status = (TextView) findViewById(R.id.TV_Ach_3_status);
        TV_Ach_4_status = (TextView) findViewById(R.id.TV_Ach_4_status);
        int[] textViewIDs = new int[] {R.id.TV_Ach_1_name, R.id.TV_Ach_1_desc, R.id.TV_Ach_2_name, R.id.TV_Ach_2_desc, R.id.TV_Ach_3_name, R.id.TV_Ach_3_desc };

        //Fill widgets with values
        if(Global.getAchivement_timePassed()) { TV_Ach_1_status.setText(R.string.status_complete); TV_Ach_1_status.setBackgroundColor(Color.GREEN); }
        if(Global.getAchivement_riddlesSolved()) { TV_Ach_2_status.setText(R.string.status_complete); TV_Ach_2_status.setBackgroundColor(Color.GREEN); }
        if(Global.getAchivement_locVisited()) { TV_Ach_3_status.setText(R.string.status_complete); TV_Ach_3_status.setBackgroundColor(Color.GREEN); }
        if(Global.getAchivement_keysCollected()) { TV_Ach_4_status.setText(R.string.status_complete); TV_Ach_4_status.setBackgroundColor(Color.GREEN);}
        for(int i=0; i < textViewIDs.length; i++) {
            TextView tv = (TextView ) findViewById(textViewIDs[i]);
            if(i<2) {
                if (i % 2 == 0) { tv.setText(achivementsList.get(0).getName()); }
                else { tv.setText(achivementsList.get(0).getDesc()); }
            }else if(i<4) {
                if (i % 2 == 0) { tv.setText(achivementsList.get(1).getName()); }
                else { tv.setText(achivementsList.get(1).getDesc()); }
            }else if(i<6) {
                if (i % 2 == 0) { tv.setText(achivementsList.get(2).getName()); }
                else { tv.setText(achivementsList.get(2).getDesc()); }
            }
        }
    }
}
