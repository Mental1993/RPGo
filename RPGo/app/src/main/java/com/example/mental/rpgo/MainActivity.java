package com.example.mental.rpgo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

/**
 * This is the MainActivity, it is the first class to be called everytime the application starts
 * It is the login screen of the application and handles all the logic behild it
 *
 */
public class MainActivity extends AppCompatActivity {
    EditText ETuser,ETpwd;
    TextView TV_pwdforgot, TV_signup;
    Button btnLogIn;
    DatabaseHelper mydb;
    String player_Id="0";
    String player_nogrif="0";
    Global gl;
    String username_entered, password_entered, email_entered;

    /**
     * This is the first function to be called after the MainActivity  activity is called.
     * Initializes widgets
     * Handles the login system. If the credentials ebeterd are correct, the user is prompt to the main menu, if not, a proper message is displayed.
     * Gives the user the ability to sign up, and remember his password.
     * @param savedInstanceState Bundle: if the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DatabaseHelper(this);
        ETuser = (EditText) findViewById(R.id.usr);
        ETpwd = (EditText) findViewById(R.id.pwd);
        btnLogIn = (Button) findViewById(R.id.btnLogIn);
        TV_signup = (TextView) findViewById(R.id.TV_signup);
        TV_pwdforgot = (TextView) findViewById(R.id.TV_pwdforgot);
        gl = new Global();

        mydb.fillKeysLoc(Global.getKeys_loc());


        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username_entered = ETuser.getText().toString();
                password_entered = ETpwd.getText().toString();
                email_entered = ETuser.getText().toString();
                Cursor cs = mydb.getID(username_entered, password_entered, email_entered);

                if((cs!=null) && (cs.moveToFirst())) {
                    player_Id = cs.getString(cs.getColumnIndex(DatabaseHelper.USER_COLUMN_ID));
                    player_nogrif=cs.getString(cs.getColumnIndex(DatabaseHelper.USER_COLUMN_NOGRIF));
                } else {
                    player_Id="0";
                }

                if (!cs.isClosed())  {
                    cs.close();
                }

                if (!player_Id.equals("0")) {
                    Log.d("cat", player_Id);
                    Log.d("cat", player_nogrif);
                    gl.setId(player_Id);
                    gl.setNogrif(player_nogrif);
                    if(player_nogrif.equals("-1"))/// new
                    {
                        Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(com.example.mental.rpgo.MainActivity.this, CharSetUp.class);
                        startActivity(intent);/// new
                    }else{
                        Toast.makeText(getApplicationContext(), "Successful Login", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(com.example.mental.rpgo.MainActivity.this, Buttons.class);

                        startActivity(intent);}
                    Intent intent = new Intent(MainActivity.this, Buttons.class);
                    mydb.close();
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TV_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
                finish();
            }
        });

        TV_pwdforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Forgot_password.class);
                startActivity(intent);
            }
        });
    }

    private Boolean exit = false;

    /**
     * Called when the back button of the smartphone is clicked.
     * Exists the application after a short confirmation
     */
    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press back again to exit the app",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);
        }
    }


}
