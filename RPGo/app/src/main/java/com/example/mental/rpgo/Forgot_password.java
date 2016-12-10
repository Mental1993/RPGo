package com.example.mental.rpgo;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Forgot_password extends AppCompatActivity {

    TextView TV_username, TV_email;
    EditText ET_username, ET_email;
    Button btnSend;
    String username_entered, email_entered, password;
    DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        //Initialize widgets
        TV_username = (TextView) findViewById(R.id.TV_username);
        TV_email = (TextView) findViewById(R.id.TV_email);
        ET_username = (EditText) findViewById(R.id.ET_username);
        ET_email = (EditText) findViewById(R.id.ET_email);
        btnSend = (Button) findViewById(R.id.btnSend);
        dbHelper = new DatabaseHelper(this);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cs;
                password = "";
                username_entered = ET_username.getText().toString();
                email_entered = ET_email.getText().toString();
                //Toast.makeText(getApplicationContext(), "this is your id : " + Global.getId(), Toast.LENGTH_LONG).show();
                cs = dbHelper.user_exists(username_entered, email_entered);
                if((cs != null) && cs.moveToFirst()) {
                    password = cs.getString(cs.getColumnIndex(DatabaseHelper.USER_COLUMN_PASSWORD));
                    Toast.makeText(getApplicationContext(), "Your password is " + password, Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
                }

                cs.close();
                dbHelper.close();
            }
        });
    }
}
