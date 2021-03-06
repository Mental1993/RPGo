package com.example.mental.rpgo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

/**
 * Contains the Achivement activity. Inside, the user can check each achivement's status(Completed or Incomplete)
 *
 * @author Mental
 * @version 1.0
 */

public class Achivements extends AppCompatActivity {

    TextView TV_Ach_1_status, TV_Ach_2_status, TV_Ach_3_status, TV_Ach_4_status;
    List<AchivementObject> achivementsList;
    DatabaseHelper db;


    /**
     * Called when the Achivement activity is loaded.
     * Calles the corresponding xml layout.
     * Instantiates a DatabaseHelper object and all the layout's widgets
     * Fills the TextViews with each achivement and it's status. Information is imported from the database
     * @param savedInstanceState Bundle: if the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
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
        int[] textViewIDs = new int[] {R.id.TV_Ach_1_name, R.id.TV_Ach_1_desc, R.id.TV_Ach_2_name, R.id.TV_Ach_2_desc, R.id.TV_Ach_3_name, R.id.TV_Ach_3_desc, R.id.TV_Ach_4_name, R.id.TV_Ach_4_desc };

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
            }else if(i<8) {
                if (i % 2 == 0) { tv.setText(achivementsList.get(3).getName()); }
                else { tv.setText(achivementsList.get(3).getDesc()); }
            }
        }
    }
}
