package com.example.mental.rpgo;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This class handles the Forgot Password functionality.
 * @author Mental
 * @version 1.1
 */
public class Forgot_password extends AppCompatActivity {

    TextView TV_username, TV_email;
    EditText ET_username, ET_email;
    Button btnSend;
    String username_entered, email_entered, password;
    DatabaseHelper dbHelper;


    /**
     * This is the first function to be called when the Forgot_password activity is loaded.
     * Initializes the widgets
     * Askes user to enter his username and Email
     * If they are correct, the password is shown, if not, display a proper message
     * @param savedInstanceState Bundle: if the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
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
