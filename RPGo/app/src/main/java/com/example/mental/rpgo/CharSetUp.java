package com.example.mental.rpgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class CharSetUp extends AppCompatActivity {

    ImageButton Bleft,Bright;
    ImageView image;
    int num=0;
    Button Bselect;
    DatabaseHelper mydb;

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
                num++;
                if(num>3){num=0;}
                picLoader();

            }
        });

        Bleft.setOnClickListener(new View.OnClickListener() {//an pati8ei to aristero
            @Override
            public void onClick(View v) {
                num--;
                if(num<0){num=3;}
                picLoader();
            }

        });

        Bselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id= Integer.parseInt(Global.getId());
                mydb.update_user(1,id);
                mydb.insertImage(num,id);
                mydb.close();
                Global.setImage(num);
                startActivity(new Intent(CharSetUp.this,Buttons.class));
            }
        });



    }


    public void picLoader(){
        if(num==0){
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