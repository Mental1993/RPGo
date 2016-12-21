package com.example.mental.rpgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class gameWin extends AppCompatActivity {

    Button win;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_win);

        win=(Button) findViewById(R.id.btnNice);

        win.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(gameWin.this, Buttons.class));
            }
        });
    }
}