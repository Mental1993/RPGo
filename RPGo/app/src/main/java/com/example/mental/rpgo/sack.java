package com.example.mental.rpgo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * This class handles the inventory system of the user
 */
public class sack extends AppCompatActivity {

    TextView knife,picklock;
    ImageView impick,imknife;
    LinearLayout Lpick,Lknife;

    /**
     * This is the first function to e called after the sack activity is loaded
     * Initializes widgets
     * Sets the corresponding xml layout of the activity
     * Displays all the items that the user has collected so far
     * @param savedInstanceState Bundle: if the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sack);

        knife=(TextView) findViewById(R.id.knifeName);
        picklock=(TextView) findViewById(R.id.pickName);

        impick=(ImageView) findViewById(R.id.imPick);
        imknife=(ImageView) findViewById(R.id.imKn);

        Lpick=(LinearLayout) findViewById(R.id.pickL);
        Lknife=(LinearLayout) findViewById(R.id.knifeL);

        if(Integer.parseInt(Global.getNogrif())>2){
            Lpick.setVisibility(View.VISIBLE);
        }
        if(Integer.parseInt(Global.getNogrif())>3){
            Lknife.setVisibility(View.VISIBLE);
        }

    }
}
