package com.example.mental.rpgo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Dimitris on 14/11/2016.
 */

public class SignUp extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
    }

    public void onSignUpClick(View v)
    {
        if(v.getId()== R.id.btnSignUp)
        {
            EditText name = (EditText)findViewById(R.id.TFname);
            EditText email = (EditText)findViewById(R.id.TFemail);
            EditText uname = (EditText)findViewById(R.id.TFuname);
            EditText pass1 = (EditText)findViewById(R.id.TFpass1);
            EditText pass2 = (EditText)findViewById(R.id.TFpass2);

            String namestr = name.getText().toString();
            String emailstr = email.getText().toString();
            String unamestr = uname.getText().toString();
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();

            if(!pass1str.equals(pass2str))
            {
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
            }

            if(namestr.isEmpty() || emailstr.isEmpty() || unamestr.isEmpty() || pass1str.isEmpty() || pass2str.isEmpty())
            {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onCancelButtonClick(View c)
    {
        if(c.getId() == R.id.btnCancel)
        {
            AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
            myAlert.setTitle("Are you Sure?").setMessage("Are you sure you want to cancel Sign Up?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            })
                    .create();
            myAlert.show();
        }
    }
}
