package com.example.mental.rpgo;


import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

/**
 * Created by Azgaard on 14.12.2016.
 */

public class AboutUs extends AppCompatActivity {

    Button Back;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.aboutus);
        Back = (Button) findViewById(R.id.Back);
    }

    @Override
    public void onBackPressed() {

                        Intent intent = new Intent(AboutUs.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

    }

