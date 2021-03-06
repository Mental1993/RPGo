package com.example.mental.rpgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This class inclues all the logic behild the riddles and their solution.
 */

public class grifoi extends AppCompatActivity {

    Button btn_Answer;
    EditText E_Answer;
    TextView RiddleText;
    DatabaseHelper mydb;
    Boolean upd;
    int grifLoad; //ari8mos grifou pou prepei na forto8ei

    /**
     * This function starts when the grifoi activity loads.
     * Loads the corresponding layout
     * Instantiates all the widgets
     * Depending on the current user's riddle, the corresponding riddle is loaded.
     * @param savedInstanceState Bundle: if the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grifoi);

        grifLoad = Global.getGrifLoad(); //ari8mos grfou p einai o xristis
        btn_Answer = (Button) findViewById(R.id.RiddleSubmit);
        E_Answer = (EditText) findViewById(R.id.RiddleAnswer);
        RiddleText = (TextView) findViewById(R.id.RiddleText);

        if (grifLoad == 1) {

            RiddleText.setText(getResources().getString(R.string.riddle1));
            btn_Answer.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (E_Answer.getText().toString().toLowerCase().equals("maid of orleans")) {
                        Toast.makeText(getApplicationContext(), "You are... correct!", Toast.LENGTH_SHORT).show();
                        dbUpdate();
                    } else {
                        Toast.makeText(getApplicationContext(), "Nooo!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        if (grifLoad == 2) {

            RiddleText.setText(getResources().getString(R.string.riddle2));
            btn_Answer.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (E_Answer.getText().toString().toLowerCase().equals("the human brain")) {
                        Toast.makeText(getApplicationContext(), "Here's a pick lock, use it wisely", Toast.LENGTH_SHORT).show();
                        part2();
                    } else {
                        Toast.makeText(getApplicationContext(), "Nope!", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

        if (grifLoad == 3) {
            RiddleText.setText(getResources().getString(R.string.riddle3));
            btn_Answer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (E_Answer.getText().toString().toLowerCase().equals("pick") || E_Answer.getText().toString().equals("pick it")) {
                        Toast.makeText(getApplicationContext(), "Pick WHAT??", Toast.LENGTH_SHORT).show();
                    }

                    if (E_Answer.getText().toString().toLowerCase().equals("pick lock") || E_Answer.getText().toString().equals("pick the lock")) {
                        Toast.makeText(getApplicationContext(), "The door's unlocked", Toast.LENGTH_SHORT).show();
                        part2();
                    } else {
                        Toast.makeText(getApplicationContext(), "Dude..", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        if(grifLoad==4){
            RiddleText.setText(getResources().getString(R.string.riddle4));
            btn_Answer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (E_Answer.getText().toString().toLowerCase().trim().equals("untie") || E_Answer.getText().toString().trim().equals("untie it")){
                        Toast.makeText(getApplicationContext(), "Untie what?", Toast.LENGTH_SHORT).show();
                    }

                    if (E_Answer.getText().toString().toLowerCase().trim().equals("cut") || E_Answer.getText().toString().trim().equals("cut it")){
                        Toast.makeText(getApplicationContext(), "Cut what?", Toast.LENGTH_SHORT).show();
                    }

                    if(E_Answer.getText().toString().toLowerCase().trim().equals("untie the knot") || E_Answer.getText().toString().trim().equals("untie knot")){
                        Toast.makeText(getApplicationContext(), "You cannot find the starting point, or the ending", Toast.LENGTH_SHORT).show();
                    }
                    if(E_Answer.getText().toString().toLowerCase().trim().equals("use knife")|| E_Answer.getText().toString().toLowerCase().trim().equals("use the knife")){
                        Toast.makeText(getApplicationContext(), "Not many has found out the solution", Toast.LENGTH_SHORT).show();
                        dbUpdate();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Dude..", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        if(grifLoad==5){
            RiddleText.setText(getResources().getString(R.string.riddle5));
            btn_Answer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (E_Answer.getText().toString().toLowerCase().trim().equals("your breath")) { //Your mum
                        Toast.makeText(getApplicationContext(), "Yessss!", Toast.LENGTH_SHORT).show();
                        part2();
                    } else {
                        Toast.makeText(getApplicationContext(), "Nooo!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * Part 2 of each riddle (If exists)
     */
    public void part2() { //se merika qr pros8etoyme perissoterous grifous gia auksisi dyskolias
        E_Answer.setText("");

        if (grifLoad == 2) {
            RiddleText.setText(getResources().getString(R.string.riddle2p2));
            btn_Answer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { //blepoyme ti apantise

                    if (E_Answer.getText().toString().toLowerCase().equals("pick") || E_Answer.getText().toString().equals("pick it")) {
                        Toast.makeText(getApplicationContext(), "Pick what?", Toast.LENGTH_SHORT).show();
                        //an den kserei na xrisimopoiei swsta to lock pick
                    }
                    if (E_Answer.getText().toString().toLowerCase().equals("pick lock") || E_Answer.getText().toString().equals("pick the lock")) {
                        Toast.makeText(getApplicationContext(), "The box contains a piece of map", Toast.LENGTH_SHORT).show();
                        //otan kserei na xrisimopoiei swsta to lock picks
                        dbUpdate();

                    } else {
                        Toast.makeText(getApplicationContext(), "Think!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        if (grifLoad == 3) {
            RiddleText.setText(getResources().getString(R.string.riddle3p2));
            btn_Answer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { //blepoyme ti apantise


                    if (E_Answer.getText().toString().equals("Fossils") || E_Answer.getText().toString().equals("fossils")) {
                        Toast.makeText(getApplicationContext(), "Here's a knife, go to the next one now", Toast.LENGTH_SHORT).show();
                        dbUpdate();
                    } else {
                        Toast.makeText(getApplicationContext(), "Dinosaurs..", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        if (grifLoad == 5) {
            RiddleText.setText(getResources().getString(R.string.riddle5p2));
            btn_Answer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { //blepoyme ti apantise


                    if (E_Answer.getText().toString().trim().equals("skull") || E_Answer.getText().toString().trim().equals("a skull")) {
                        Toast.makeText(getApplicationContext(), "Well done! On to the next one!", Toast.LENGTH_SHORT).show();
                        part3();
                    } else {
                        Toast.makeText(getApplicationContext(), "It's out of your mind..", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * Part 3 of each riddle (If exists)
     */
     public void part3() {
            E_Answer.setText("");
            RiddleText.setText(getResources().getString(R.string.riddle5p3));
            btn_Answer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { //blepoyme ti apantise

                    if (E_Answer.getText().toString().trim().equals("racecar")) {
                        Toast.makeText(getApplicationContext(), "Clever boy!", Toast.LENGTH_SHORT).show();//nikaei to game
                        //startActivity(new Intent(grifoi.this, gameWin.class));
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "So close to the end, yet you are failing", Toast.LENGTH_SHORT).show();
                    }
                }});

     }


    /**
     * Updates the current riddle of the user.
     */
    public void dbUpdate() {

        mydb = new DatabaseHelper(grifoi.this);
        int setter = Integer.parseInt(Global.getNogrif()) + 1;
        upd = mydb.update_user(setter, Integer.parseInt(Global.getId()));
        Global.setNogrif(String.valueOf(setter));
        mydb.close();
        startActivity(new Intent(grifoi.this, Buttons.class));
    }
    }
