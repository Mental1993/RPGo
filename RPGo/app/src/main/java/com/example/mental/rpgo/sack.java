package com.example.mental.rpgo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class sack extends AppCompatActivity {

    TextView knife,picklock;
    ImageView impick,imknife;
    LinearLayout Lpick,Lknife;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sack);

    }
}
