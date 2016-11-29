package com.example.mental.rpgo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class grifos2 extends AppCompatActivity {

    Button btn_Answer;
    EditText E_Answer;
    TextView Thetext;
    DatabaseHelper mydb;
    Boolean upd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riddle2);

       btn_Answer= (Button) findViewById(R.id.btnAnswer2);
        E_Answer= (EditText) findViewById(R.id.answer2);
        Thetext=(TextView) findViewById(R.id.thetext2);

        btn_Answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(E_Answer.getText().toString().equals("The human brain")||E_Answer.getText().toString().equals("the human brain")){
                    Toast.makeText(getApplicationContext(), "Here's a pick lock, use it wisely", Toast.LENGTH_SHORT).show();
        ;//to part 2 edw
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Nope!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //part 2

}