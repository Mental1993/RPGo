package com.example.mental.rpgo;


import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

/**
 * Contains the About us Activity. Information about the application are stored here
 *
 * @author Mental
 * @version 1.0
 */
public class AboutUs extends AppCompatActivity {

    Button Back;

    /**
     *
     * Called when the About us activity is loaded.
     * Loads the corresponding xml layout and instantiates the Back button
     *
     * @param savedInstanceState Bundle: if the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     * @param persistentState PersistableBundle: if the activity is being re-initialized after previously being shut down or powered off then this Bundle contains the data it most recently supplied to outPersistentState in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.about_us);
        Back = (Button) findViewById(R.id.Back);
    }

    /**
     * Called when the user presses the back button
     */
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(AboutUs.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}

