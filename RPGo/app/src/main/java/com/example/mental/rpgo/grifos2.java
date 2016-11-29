package com.example.mental.rpgo;

/**
 * Created by Azgaard on 29/11/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class grifos2 extends AppCompatActivity {

    Button btn_Answer;
    EditText E_Answer;
    TextView Thetext;
    dbHelper mydb;
    Boolean upd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grifos2);

        btn_Answer= (Button) findViewById(R.id.btnAnswer2);
        E_Answer= (EditText) findViewById(R.id.answer2);
        Thetext=(TextView) findViewById(R.id.thetext2);

        btn_Answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(E_Answer.getText().toString().equals("The human brain")||E_Answer.getText().toString().equals("the human brain")){
                    Toast.makeText(getApplicationContext(), "Here's a pick lock, use it wisely", Toast.LENGTH_SHORT).show();//dinoume ena lock pick
                    //8a desmeutei se allh ekdosh
                    part2();//pername sto kommati 2
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Nope!", Toast.LENGTH_SHORT).show(); //mnm la8ous
                }
            }
        });
    }

    void part2(){
        Thetext.setText(getResources().getString(R.string.grifos2_p2)); //8etoyme allo grifo
        E_Answer.setText(""); //sbinoyme thn prohgoymenh apatisi tou xristi
        btn_Answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //blepoyme ti apantise

                if (E_Answer.getText().toString().equals("Pick") || E_Answer.getText().toString().equals("pick")){
                    Toast.makeText(getApplicationContext(), "Pick what?", Toast.LENGTH_SHORT).show();//an den kserei na xrisimopoiei
                    //swsta to lock pick
                }
                if (E_Answer.getText().toString().equals("Pick lock") || E_Answer.getText().toString().equals("Pick the lock")) {
                    Toast.makeText(getApplicationContext(), "The box contains a piece of map", Toast.LENGTH_SHORT).show(); //otan kserei na
                    //xrisimopoiei swsta to lock picks

                    mydb= new dbHelper(grifos2.this);
                    int setter=Integer.parseInt(Global.getNogrif())+1;
                    upd= mydb.update_user(setter,Integer.parseInt(Global.getId()));
                    Global.setNogrif(String.valueOf(setter));
                    mydb.close();
                    startActivity(new Intent(grifos2.this, buttons.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Think!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}