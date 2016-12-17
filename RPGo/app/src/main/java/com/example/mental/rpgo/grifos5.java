package com.example.mental.rpgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class grifos5 extends AppCompatActivity {
    Button btn_Answer;
    EditText E_Answer;
    TextView Vtext;
    DatabaseHelper mydb;
    Boolean upd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riddle5);

        btn_Answer = (Button) findViewById(R.id.btnAnswer5);
        E_Answer = (EditText) findViewById(R.id.answer5);
        Vtext = (TextView) findViewById(R.id.thetext5);

        btn_Answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (E_Answer.getText().toString().toLowerCase().equals("your breath")) { //Your mum
                    Toast.makeText(getApplicationContext(), "Yessss!", Toast.LENGTH_SHORT).show();
                    part2();
                } else {
                    Toast.makeText(getApplicationContext(), "Nooo!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    void part2(){
        Vtext.setText(getResources().getString(R.string.riddle5p2));
        E_Answer.setText("");
        btn_Answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //blepoyme ti apantise


                if (E_Answer.getText().toString().equals("skull")||E_Answer.getText().toString().equals("a skull")) {
                    Toast.makeText(getApplicationContext(), "Well done! On to the next one!", Toast.LENGTH_SHORT).show();
                    part3();
                }
                else {
                    Toast.makeText(getApplicationContext(), "It's out of your mind..", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void part3(){
        E_Answer.setText("");
        Vtext.setText(getResources().getString(R.string.riddle5p3));
        btn_Answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //blepoyme ti apantise

                if (E_Answer.getText().toString().equals("racecar")) {
                    Toast.makeText(getApplicationContext(), "Clever boy!", Toast.LENGTH_SHORT).show();//nikaei to game

                    mydb = new DatabaseHelper(grifos5.this);
                    int setter = Integer.parseInt(Global.getNogrif()) + 1;
                    upd = mydb.update_user(setter, Integer.parseInt(Global.getId()));
                    Global.setNogrif(String.valueOf(setter));
                    mydb.close();
                    startActivity(new Intent(grifos5.this, Buttons.class));
                }
                else {
                    Toast.makeText(getApplicationContext(), "So close to the end, yet you are failing", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



}

