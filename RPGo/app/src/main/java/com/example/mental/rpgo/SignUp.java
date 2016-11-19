package com.example.mental.rpgo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Dimitris on 14/11/2016.
 */

public class SignUp extends AppCompatActivity {
    EditText pass, secPwd, userN;
    TextView stre, match;
    Button btnDone;
    Integer num;
    dbHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);
        mydb = new dbHelper(this);
        pass = (EditText) findViewById(R.id.Pass);
        secPwd = (EditText) findViewById(R.id.secP);
        userN = (EditText) findViewById(R.id.userN);
        stre = (TextView) findViewById(R.id.Str);
        match = (TextView) findViewById(R.id.match);
        btnDone = (Button) findViewById(R.id.button);
        num = 0;

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
                }

                if (num > 4) {
                    stre.setText("Weak");
                }

                if (num > 6) {
                    stre.setText("Medium");
                }

                if (num >= 10) {
                    stre.setText("Strong");
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
                    match.setText("Match");
                    if (num > 4) {
                        btnDone.setEnabled(true);
                    }

                } else {
                    match.setText("Mismatch");
                    btnDone.setEnabled(false);
                }
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String name = userN.getText().toString();
                String pwd = pass.getText().toString();
                boolean isOK= mydb.insert_user(name,pwd);
                if(isOK)
                {
                    Toast.makeText(getApplicationContext(), "Eggrafikate swsta", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUp.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Paroysiastike problima", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onBackPressed(){

        AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
        myAlert.setMessage("Are you sure you want to cancel sign up?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setTitle("Are you sure?")
                .create();
        myAlert.show();
    }
}
