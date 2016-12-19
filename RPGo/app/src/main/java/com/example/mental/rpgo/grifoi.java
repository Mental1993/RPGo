package com.example.mental.rpgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class grifoi extends AppCompatActivity {

    Button btn_Answer;
    EditText E_Answer;
    TextView Vtext;
    DatabaseHelper mydb;
    Boolean upd;
    int grifLoad; //ari8mos grifou pou prepei na forto8ei

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riddle);

        grifLoad=Global.getGrifLoad(); //ari8mos grfou p einai o xristis
        btn_Answer= (Button) findViewById(R.id.RiddleSubmit);
        E_Answer= (EditText) findViewById(R.id.RiddleAnswer);
        Vtext=(TextView) findViewById(R.id.RiddleText);

        if(grifLoad==1){

            Vtext.setText(getResources().getString(R.string.riddle1));
            btn_Answer.setOnClickListener(new View.OnClickListener(){

                @Override
                public  void onClick(View v){
                    if(E_Answer.getText().toString().toLowerCase().equals("maid of orleans")){
                        Toast.makeText(getApplicationContext(),"You are... correct!",Toast.LENGTH_SHORT).show();
                        dbUpdate();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Nooo!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        if(grifLoad==2){

            Vtext.setText(getResources().getString(R.string.riddle2));
            btn_Answer.setOnClickListener(new View.OnClickListener(){

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

        if(grifLoad==3) {
            Vtext.setText(getResources().getString(R.string.riddle3));
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
        }}

    public void part2() { //se merika qr pros8etoyme perissoterous grifous gia auksisi dyskolias
        E_Answer.setText("");

        if (grifLoad == 2) {
            Vtext.setText(getResources().getString(R.string.riddle2p2));
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
            Vtext.setText(getResources().getString(R.string.riddle3p2));
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
    }

    public void dbUpdate() {

        mydb = new DatabaseHelper(grifoi.this);
        int setter = Integer.parseInt(Global.getNogrif()) + 1;
        upd = mydb.update_user(setter, Integer.parseInt(Global.getId()));
        Global.setNogrif(String.valueOf(setter));
        mydb.close();
        startActivity(new Intent(grifoi.this, Buttons.class));
    }
    }
