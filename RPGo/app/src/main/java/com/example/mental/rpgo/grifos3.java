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
 * Created by Zoi on 4/12/2016.
 */

public class grifos3 extends AppCompatActivity
{
    Button btn_Answer;
    EditText E_Answer;
    TextView Vtext;
    DatabaseHelper mydb;
    Boolean upd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riddle3);

        btn_Answer= (Button) findViewById(R.id.btnAnswer3);
        E_Answer= (EditText) findViewById(R.id.answer3);
        Vtext=(TextView) findViewById(R.id.theText3);

        btn_Answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (E_Answer.getText().toString().equals("Pick") || E_Answer.getText().toString().equals("pick")){
                    Toast.makeText(getApplicationContext(), "Pick WHAT??", Toast.LENGTH_SHORT).show();
                }

                if(E_Answer.getText().toString().equals("Pick lock") || E_Answer.getText().toString().equals("Pick the lock")){
                    Toast.makeText(getApplicationContext(), "The door's unlocked", Toast.LENGTH_SHORT).show();
                    part2();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Dude..", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    void part2()
    {
        Vtext.setText(getResources().getString(R.string.grifos3_p2));
        E_Answer.setText("");
        btn_Answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (E_Answer.getText().toString().equals("3")) {
                    Toast.makeText(getApplicationContext(), "You're good", Toast.LENGTH_SHORT).show();
                    part3();
                }
                else {
                    Toast.makeText(getApplicationContext(), "How many people were there?", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void part3()
    {
        Vtext.setText(getResources().getString(R.string.grifos3_p3));
        E_Answer.setText("");
        btn_Answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (E_Answer.getText().toString().equals("Fossils")||E_Answer.getText().toString().equals("fossils")) {
                    Toast.makeText(getApplicationContext(), "Here's a knife, go to the next one now", Toast.LENGTH_SHORT).show();

                    mydb= new DatabaseHelper(grifos3.this);
                    int setter=Integer.parseInt(Global.getNogrif())+1;
                    upd= mydb.update_user(setter,Integer.parseInt(Global.getId()));
                    Global.setNogrif(String.valueOf(setter));

                    startActivity(new Intent(grifos3.this, Buttons.class));
                }
                else {
                    Toast.makeText(getApplicationContext(), "Dinosaurs..", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
