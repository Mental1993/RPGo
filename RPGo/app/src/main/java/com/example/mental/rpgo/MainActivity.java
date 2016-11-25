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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText ETuser,ETpwd;
    Button btnLogIn, btnSignUp;
    DatabaseHelper mydb;
    String player_Id="0";
    Global gl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DatabaseHelper(this);
        ETuser = (EditText) findViewById(R.id.usr);
        ETpwd = (EditText) findViewById(R.id.pwd);
        btnLogIn = (Button) findViewById(R.id.btnLogIn);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        gl = new Global();
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cs = mydb.getID(ETuser.getText().toString(), ETpwd.getText().toString());

                if((cs!=null) && (cs.moveToFirst()))
                {
                    player_Id = cs.getString(cs.getColumnIndex(DatabaseHelper.USER_COLUMN_ID));
                }
                else
                {player_Id="0";}

                if (!cs.isClosed())  {
                    cs.close();
                }

                if (!player_Id.equals("0")) {
                    Log.d("cat", player_Id);
                    gl.setId(player_Id);
                    Toast.makeText(getApplicationContext(), "Redirection...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Buttons.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials...", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Redirection...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, SignUp.class);
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
