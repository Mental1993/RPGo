package com.example.mental.rpgo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.example.mental.rpgo.R.id.image;

/**
 * This is the main menu of the application.Contains the Buttons activity. Inside, the user has the options to find the next riddle, see the achivements list, exit the application and use the Scanner
 *
 * @author Mental
 * @version 1.0
 */
public class Buttons extends AppCompatActivity {

    Button scanner,sack,map, achivements;
    TextView TV_logout, TV_riddle_progress;
    ProgressBar PB_riddle;
    DatabaseHelper mydb;
    Keys key;
    int imageNum;
    ImageView image;

    /**
     * Called when the Buttons activity is loaded.
     * Calles the corresponding xml layout.
     * Instantiates the widgets, a DatabaseHelper object and a Keys object
     * Sets an action on each button of the layout
     *
     * @param savedInstanceState Bundle: if the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons);

        scanner= (Button) findViewById(R.id.btnScan);
        sack= (Button) findViewById(R.id.btnSack);
        map= (Button) findViewById(R.id.btnMap);
        achivements = (Button) findViewById(R.id.btnAchivements);
        TV_logout = (TextView) findViewById(R.id.TV_logout);
        PB_riddle = (ProgressBar) findViewById(R.id.PB_riddle);
        TV_riddle_progress = (TextView) findViewById(R.id.TV_riddle_progress);
        // MISSING!! -- SET MAX DYNAMICALLY -- MISSING -- RIDDLES STORED IN DATABASE
        PB_riddle.setMax(5);
        PB_riddle.setProgress(Integer.parseInt(Global.getNogrif())-1);
        TV_riddle_progress.setText(String.valueOf(Integer.parseInt(Global.getNogrif())-1) + "/" + 5);
        image = (ImageView) findViewById(R.id.image);
        mydb = new DatabaseHelper(this);
        key = new Keys();

        timePlayedAchivement(mydb);



        if(Integer.parseInt(Global.getNogrif()) < 2) {
            startActivity(new Intent(Buttons.this, IntroSequenceActivity.class));
        }

        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Buttons.this, Scanner.class));
            }
        });

        sack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Buttons.this, sack.class));
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Buttons.this, Map.class));
            }
        });

        achivements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Buttons.this, Achivements.class));
            }
        });

        TV_logout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                new AlertDialog.Builder(Buttons.this)
                        .setTitle("Logout")
                        .setMessage("Would you like to logout?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Buttons.this, MainActivity.class);
                                startActivity(intent);
                                //mydb.close();
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // user doesn't want to logout
                            }
                        })
                        .show();
            }
        });
        picSetter();
    }

    /**
     * Called when the user presses the back button of the smartphone.
     */
    @Override
    public void onBackPressed() {
        AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
        myAlert.setTitle("Logout")
                .setMessage("You will be redirected to Log In screen. Are you sure?")
                .setNegativeButton("No", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Buttons.this, MainActivity.class);
                        startActivity(intent);
                        //mydb.close();
                        finish();
                    }
                })
                .create();
        myAlert.show();
    }

    /**
     * Calculates the total time passes (in seconds) since the user signed up. If it's more than 1 week, the corresponding achivement is completed.
     * @param mydb DatabaseHelper: Object of type {@link DatabaseHelper} required
     */
    public void timePlayedAchivement(DatabaseHelper mydb) {
        double secondsPLayed = ((TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()))-mydb.getTimestamp(Global.getId()));
        if(secondsPLayed > 48*3600) {
            Toast.makeText(getApplicationContext(), "You have successfully completed the TIME PLAYED Achivement!" + secondsPLayed, Toast.LENGTH_LONG).show();
            Global.setAchivement_timePassed(true);
        }
    }

    /**
     * Sets the icon that the user has selected, at the bottom left corner of the activity
     */
    public void picSetter(){
        int num=0;
        DatabaseHelper mydb= new DatabaseHelper(this);

        Cursor cs = mydb.getImage(Global.getId());
        if ((cs != null) && (cs.moveToFirst())) {
            imageNum = cs.getInt(cs.getColumnIndex(DatabaseHelper.USER_COLUMN_IMAGE));

        }
        cs.close();
        mydb.close();

        num = imageNum;
            if (num==0){
                image.setImageResource(R.drawable.mario);
            }

            if (num == 1) {
                image.setImageResource(R.drawable.luigi);
            }

            if (num == 2) {
                image.setImageResource(R.drawable.peach);
            }

            if (num == 3) {
                image.setImageResource(R.drawable.rosalina);

            }
    }
}
