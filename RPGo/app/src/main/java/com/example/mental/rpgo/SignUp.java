package com.example.mental.rpgo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Dimitris on 14/11/2016.
 */

public class SignUp extends AppCompatActivity {
    EditText pass, sec, userN;
    TextView stre, match;
    Button butt;
    Integer num;
    DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mydb = new DatabaseHelper(this);
        pass = (EditText) findViewById(R.id.Pass);
        sec = (EditText) findViewById(R.id.secP);
        userN = (EditText) findViewById(R.id.userN);
        stre = (TextView) findViewById(R.id.Str);
        match = (TextView) findViewById(R.id.match);
        butt = (Button) findViewById(R.id.button);
        num = 0;

        sec.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (pass.getText().toString().equals(s.toString())) {
                    match.setText("Match");
                    if (num > 4) {
                        butt.setEnabled(true);
                    }

                } else {
                    match.setText("Mismatch");
                    butt.setEnabled(false);
                }
            }
        });


    }
}
