package com.example.mental.rpgo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import tourguide.tourguide.Overlay;
import tourguide.tourguide.Pointer;
import tourguide.tourguide.ToolTip;
import tourguide.tourguide.TourGuide;

import static com.example.mental.rpgo.R.id.PB_riddle;
import static com.example.mental.rpgo.R.id.TV_logout;
import static com.example.mental.rpgo.R.id.TV_mainenu;

/**
 * Created by Dimitris on 12/12/2016.
 */

public class IntroSequenceActivity extends ActionBarActivity {
    public TourGuide mTutorialHandler, mTutorialHandler2;
    public Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mActivity = this;
        setContentView(R.layout.activity_buttons);

        final Button button = (Button)findViewById(R.id.btnScan);
        final Button button2 = (Button)findViewById(R.id.btnSack);
        final Button button3 = (Button)findViewById(R.id.btnMap);
        final Button button4 = (Button)findViewById(R.id.btnAchivements);
        final TextView textView = (TextView)findViewById(TV_mainenu);
        final TextView textView1 = (TextView)findViewById(TV_logout);
        final ProgressBar progressBar = (ProgressBar)findViewById(PB_riddle);

        Animation enterAnimation = new AlphaAnimation(0f, 1f);
        enterAnimation.setDuration(600);
        enterAnimation.setFillAfter(true);

        Animation exitAnimation = new AlphaAnimation(1f, 0f);
        exitAnimation.setDuration(600);
        exitAnimation.setFillAfter(true);

        mTutorialHandler = TourGuide.init(this).with(TourGuide.Technique.Click)
                .setPointer(new Pointer())
                .setToolTip(new ToolTip()
                        .setTitle("Welcome!")
                        .setDescription("This is the main menu. I'll guide you through.")
                        .setGravity(Gravity.BOTTOM|Gravity.CENTER)
                )
                .setOverlay(new Overlay()
                        .setEnterAnimation(enterAnimation)
                        .setExitAnimation(exitAnimation)
                );
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTutorialHandler.cleanUp();
                mTutorialHandler.setToolTip(new ToolTip()
                        .setTitle("This is your progress bar.")
                        .setDescription("Fill it by solving riddles.")
                        .setGravity(Gravity.RIGHT))
                        .playOn(progressBar);
            }
        });
        progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTutorialHandler.cleanUp();
                mTutorialHandler.setToolTip(new ToolTip()
                .setTitle("Scan")
                .setDescription("Scan a QR code and reveal the riddle.")
                .setGravity(Gravity.BOTTOM|Gravity.LEFT))
                        .playOn(button);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTutorialHandler.cleanUp();
                mTutorialHandler.setToolTip(new ToolTip()
                .setTitle("Inventory")
                .setDescription("This is where your items are stored")
                .setGravity(Gravity.TOP|Gravity.RIGHT))
                        .playOn(button2);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTutorialHandler.cleanUp();
                mTutorialHandler.setToolTip(new ToolTip()
                .setTitle("Map")
                .setDescription("This is where it all starts!")
                .setGravity(Gravity.BOTTOM|Gravity.RIGHT))
                        .playOn(button3);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTutorialHandler.cleanUp();
                mTutorialHandler.setToolTip(new ToolTip()
                .setTitle("Achievements")
                .setDescription("See which achievements you have unlocked")
                .setGravity(Gravity.BOTTOM|Gravity.CENTER))
                        .playOn(button4);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTutorialHandler.cleanUp();
                mTutorialHandler.setToolTip(new ToolTip()
                        .setTitle("Log Out")
                        .setDescription("Log out and return to log in screen")
                        .setGravity(Gravity.BOTTOM|Gravity.LEFT))
                        .playOn(textView1);
            }
        });

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTutorialHandler.cleanUp();
                finish();
            }
        });

        mTutorialHandler.playOn(textView);


            }
        }