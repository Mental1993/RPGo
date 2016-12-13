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
 * Created by angel on 27/11/2016.
 */

public class grifos1 extends AppCompatActivity {

    Button btn_Answer;
    EditText E_Answer;
    TextView Text;
    DatabaseHelper mydb;
    Boolean upd;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_riddle1);

        btn_Answer= (Button) findViewById(R.id.button4);
        E_Answer= (EditText) findViewById(R.id.answer);
        Text= (TextView) findViewById(R.id.Text);

        btn_Answer.setOnClickListener(new View.OnClickListener(){

            @Override
            public  void onClick(View v){
                if(E_Answer.getText().toString().equals("maid of Orleans") || E_Answer.getText().toString().equals("maid of orleans")){
                    mydb = new DatabaseHelper(grifos1.this);
                    int setter = Integer.parseInt(Global.getNogrif()) + 1;
                    upd = mydb.update_user(setter, Integer.parseInt(Global.getId()));
                    Global.setNogrif(String.valueOf(setter));
                    mydb.close();
                    Toast.makeText(getApplicationContext(),"You are... correct! Next riddle is " + setter,Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(grifos1.this,Buttons.class));
                }

                else

                {
                    Toast.makeText(getApplicationContext(), "Nope!", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }
}
