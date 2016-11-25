package com.example.mental.rpgo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Buttons extends AppCompatActivity {

    Button scanner,sack,map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons);

        scanner= (Button) findViewById(R.id.btnScan);
        sack= (Button) findViewById(R.id.btnSack);
        map= (Button) findViewById(R.id.btnMap);


        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(buttons.this, scanner.class));
            }
        });

        sack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "I'm not ready yet", Toast.LENGTH_SHORT).show();
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(buttons.this, map.class));
            }
        });
    }
}
