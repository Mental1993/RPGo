package com.example.mental.rpgo;

import android.content.Intent;
import android.database.Cursor;
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
}
