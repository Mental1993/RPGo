package com.example.mental.rpgo;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by angel on 20/12/2016.
 */

public class CharSetUp extends AppCompatActivity {

    ImageView image;
    int num=0;

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
