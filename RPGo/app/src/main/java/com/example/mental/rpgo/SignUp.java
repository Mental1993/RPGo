package com.example.mental.rpgo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
 * This class witch handles the sign up of the user
 * @author Mental
 * @version 1.1
 */

public class SignUp extends AppCompatActivity {
    EditText pass, secPwd, userN, ET_email;
    TextView stre, match, TV_email;
    Button btnDone;
    Integer num;
    DatabaseHelper mydb;
    Keys key;

    /**
     * This is the first function to be called after the SignUp activity is called
     * initializes widgets
     * Sets the corresponding xml layout for the activity
     * Adds a text Changed Listener
     * Handles the Sign up functionality
     * @param savedInstanceState Bundle: if the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
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
                isOK= mydb.insert_user(name, pwd, email, Global.KEYS_REGENERATE_INTERVAL);
                if(isOK && !isDuplicateName && !isDuplicateEmail)
                {
                    Toast.makeText(getApplicationContext(), "Successful Sign up!", Toast.LENGTH_SHORT).show();
                    mydb.fillKeysLoc(Global.getKeys_loc());
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

    /**
     * Called when the back button is pressed
      */
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
