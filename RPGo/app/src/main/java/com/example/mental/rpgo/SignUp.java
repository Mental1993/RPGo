package com.example.mental.rpgo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import static android.graphics.Color.GRAY;

/**
 * Created by Dimitris on 14/11/2016.
 */

public class SignUp extends AppCompatActivity {
    EditText pass, secPwd, userN, ET_email;
    TextView stre, match, TV_email;
    Button btnDone;
    Integer num;
    DatabaseHelper mydb;
    Keys key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);
        mydb = new DatabaseHelper(this);
        pass = (EditText) findViewById(R.id.pass);
        secPwd = (EditText) findViewById(R.id.secP);
        userN = (EditText) findViewById(R.id.userN);
        stre = (TextView) findViewById(R.id.TV_str);
        match = (TextView) findViewById(R.id.match);
        btnDone = (Button) findViewById(R.id.btnDone);
        ET_email = (EditText) findViewById(R.id.ET_email);
        TV_email = (TextView) findViewById(R.id.TV_email);
        num = 0;
        key = new Keys();

        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                num = s.toString().length();
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (num <= 4) {
                    stre.setText("Too weak");
                    stre.setTextColor(Color.GRAY);
                }

                if (num > 4) {
                    stre.setText("Weak");
                    stre.setTextColor(Color.RED);
                }

                if (num > 6) {
                    stre.setText("Medium");
                    stre.setTextColor(Color.YELLOW);
                }

                if (num >= 10) {
                    stre.setText("Strong");
                    stre.setTextColor(Color.GREEN);
                }
            }
        });

        secPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (pass.getText().toString().equals(s.toString())) {
                    match.setTextColor(Color.GREEN);
                    match.setText("Match");
                    if (num > 4) {
                        btnDone.setEnabled(true);
                    }
                } else {
                    match.setTextColor(Color.RED);
                    match.setText("Mismatch");
                    btnDone.setEnabled(false);
                }
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                boolean isOK, isDuplicateName, isDuplicateEmail;
                String name = userN.getText().toString();
                String pwd = pass.getText().toString();
                String email = ET_email.getText().toString();
                isDuplicateName = mydb.check_duplicate("name", name);
                isDuplicateEmail = mydb.check_duplicate("email", email);
                isOK= mydb.insert_user(name, pwd, email, 0, Global.KEYS_REGENERATE_INTERVAL, TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
                if(isOK && !isDuplicateName && !isDuplicateEmail)
                {
                    Toast.makeText(getApplicationContext(), "Successful Sign up!", Toast.LENGTH_SHORT).show();
                    for(int i=0; i<8; i++) {
                        Global.setKeys_location(key.getRandomLocation());
                    }
                    Intent intent = new Intent(SignUp.this, MainActivity.class);
                    startActivity(intent);
                }
                else if((isDuplicateName || isDuplicateEmail) && isOK)
                {
                    Toast.makeText(getApplicationContext(), "Sign up failed. Username or Email already exists.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Sign up failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onBackPressed(){

        AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
        myAlert.setMessage("Are you sure you want to cancel sign up?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(SignUp.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setTitle("Are you sure?")
                .create();
        myAlert.show();
    }
}
