package com.example.mental.rpgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * This class handles the character icon selection from the user, and stores the icon into the database
 * @author Mental
 * @version 1.0
 */

public class CharSetUp extends AppCompatActivity {

    ImageButton Bleft,Bright;
    ImageView image;
    int num=0;
    Button Bselect;
    DatabaseHelper mydb;

    /**
     * This is the first function to be called, when the CharSetUp activity is loaded.
     * Sets the corresponding xml layout
     * Initializes widgets
     * Click listeners for right, left and select buttons
     * Stores the image into the database
     * @param savedInstanceState Bundle: if the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_set_up);

        Bleft=(ImageButton) findViewById(R.id.arrowLeft);
        Bright=(ImageButton) findViewById(R.id.arrowRight);
        image=(ImageView) findViewById(R.id.charImage);
        Bselect=(Button) findViewById(R.id.Select);
        mydb=new DatabaseHelper(this);

        Bright.setOnClickListener(new View.OnClickListener() { //an pati8ei to deksi belaki
            @Override
            public void onClick(View v) {
                num++; //auksanetae to num.
                if(num>3){num=0;} // An einai megalutero apo 3 paei sto 0
                picLoader();

            }
        });

        Bleft.setOnClickListener(new View.OnClickListener() {//an pati8ei to aristero
            @Override
            public void onClick(View v) {
                num--; //ellatonete to num.
                if(num<0){num=3;} //An einai mikrotero apo 0, paei sto 3
                picLoader();
            }

        });

        Bselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // o xristis epilegei ton eikona p 8elei
                int id= Integer.parseInt(Global.getId());
                mydb.update_user(1,id);
                mydb.insertImage(num,id);
                mydb.close();
                Global.setImage(num);
                startActivity(new Intent(CharSetUp.this,Buttons.class)); // metabainei sthn Buttons
            }
        });



    }

    /**
     * Loads the image that the user has selected
     */
    public void picLoader() {
        if (num==0){
            image.setImageResource(R.drawable.mario);
        }
        if(num==1){
            image.setImageResource(R.drawable.luigi);
        }
        if(num==2){
            image.setImageResource(R.drawable.peach);
        }
        if(num==3){
            image.setImageResource(R.drawable.rosalina);
        }
    }
}
