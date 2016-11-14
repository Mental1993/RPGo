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
    Button btn,btn2;
    DatabaseHelper mydb;
    String player_Id="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DatabaseHelper(this);
        ETuser = (EditText) findViewById(R.id.editText);
        ETpwd = (EditText) findViewById(R.id.editText2);
        btn = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cs = mydb.getData(ETuser.getText().toString(), ETpwd.getText().toString());
                cs.moveToFirst();
                player_Id = cs.getString(cs.getColumnIndex(DatabaseHelper.USER_COLUMN_ID));
                if (!cs.isClosed())  {
                    cs.close();
                }

                if (!player_Id.equals("0")) {
                    Log.d("cat", player_Id);
                    Toast.makeText(getApplicationContext(), "Redirection...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Map.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials...", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void onButtonClick(View v)
    {
        if(v.getId() == R.id.button2)
        {
            Intent i = new Intent(MainActivity.this, SignUp.class);
            startActivity(i);
        }
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
