package com.example.mental.rpgo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Buttons extends AppCompatActivity {

    Button scanner,sack,map, achivements;
    TextView TV_logout, TV_riddle_progress;
    ProgressBar PB_riddle;
    DatabaseHelper mydb;
    Keys key;

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
    }

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

    public void timePlayedAchivement(DatabaseHelper mydb) {
        double secondsPLayed = ((TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()))-mydb.getTimestamp(Global.getId()));
        if(secondsPLayed > 48*3600) {
            Toast.makeText(getApplicationContext(), "You have successfully completed the TIME PLAYED Achivement!" + secondsPLayed, Toast.LENGTH_LONG).show();
            Global.setAchivement_timePassed(true);
        }
    }
}
