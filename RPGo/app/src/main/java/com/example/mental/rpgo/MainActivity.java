package com.example.mental.rpgo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText ETuser,ETpwd;
    TextView TV_pwdforgot;
    Button btnLogIn, btnSignUp;
    DatabaseHelper mydb;
    String player_Id="0";
    String player_nogrif="0";
    Global gl;
    String username_entered, password_entered, email_entered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DatabaseHelper(this);
        ETuser = (EditText) findViewById(R.id.usr);
        ETpwd = (EditText) findViewById(R.id.pwd);
        btnLogIn = (Button) findViewById(R.id.btnLogIn);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        TV_pwdforgot = (TextView) findViewById(R.id.TV_pwdforgot);
        gl = new Global();
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username_entered = ETuser.getText().toString();
                password_entered = ETpwd.getText().toString();
                email_entered = ETuser.getText().toString();
                Cursor cs = mydb.getID(username_entered, password_entered, email_entered);

                if((cs!=null) && (cs.moveToFirst())) {
                    player_Id = cs.getString(cs.getColumnIndex(DatabaseHelper.USER_COLUMN_ID));
                    player_nogrif=cs.getString(cs.getColumnIndex(DatabaseHelper.USER_COLUMN_NOGRIF));
                } else {
                    player_Id="0";
                }

                if (!cs.isClosed())  {
                    cs.close();
                }

                if (!player_Id.equals("0")) {
                    Log.d("cat", player_Id);
                    Log.d("cat", player_nogrif);
                    gl.setId(player_Id);
                    gl.setNogrif(player_nogrif);
                    Toast.makeText(getApplicationContext(), "Successful Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Buttons.class);
                    mydb.close();
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
                finish();
            }
        });

        TV_pwdforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Forgot_password.class);
                startActivity(intent);
            }
        });



    }

    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press back again to exit the app",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }
}
