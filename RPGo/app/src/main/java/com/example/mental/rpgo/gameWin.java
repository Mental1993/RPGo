package com.example.mental.rpgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * This class simply loads the activity when the user has successfully solved all riddles and finished the game
 * @author Mental
 * @version 1.0
 */
public class gameWin extends AppCompatActivity {

    Button win;

    /**
     * Called when the gameWin activity is loaded
     * Loads the corresponing xlm layout
     * @param savedInstanceState Bundle: if the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
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