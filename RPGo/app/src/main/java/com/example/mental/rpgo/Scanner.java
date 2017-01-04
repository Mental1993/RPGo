package com.example.mental.rpgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


/**
 * This class handles the QR Scanner, opens the camera of the smartphone and tries to recognize the QR code given.
 * @author Mental
 * @version 1.1
 */

public class Scanner extends AppCompatActivity implements ZXingScannerView.ResultHandler
{
    private ZXingScannerView mScannerView;

    /**
     * This is the first function to be called after the Scanner activity is loaded
     * Initializes widgets
     * Sets the corresponding xml layout for the activity
     * @param savedInstanceState Bundle: if the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mScannerView = new ZXingScannerView(this);

        setContentView(mScannerView);
    }

    /**
     * Called after the camera is closed and reopened
     */
    @Override
    public void onResume()
    {
        super.onResume();

        mScannerView.setResultHandler(this);

        mScannerView.startCamera();
    }

    /**
     * Called after the camera is closed
     */
    @Override
    public void onPause()
    {
        super.onPause();

        mScannerView.stopCamera();
    }

    /**
     * Handles the QR code result
     * @param rawResult Result: The raw result of the QR Code
     */
    @Override
    public void handleResult(Result rawResult)
    {
        if(rawResult.getText().equals("grifos1")) {
            Global.setGrifLoad(1);
        }
        else if(rawResult.getText().equals("grifos2")) {
            Global.setGrifLoad(2);
        }
        else if(rawResult.getText().equals("grifos3")) {
            Global.setGrifLoad(3);
        }else if(rawResult.getText().equals("grifos4")) {
            Global.setGrifLoad(4);
        }
        else if(rawResult.getText().equals("grifos5")) {
            Global.setGrifLoad(5);
        }
        else {
            Toast.makeText(getApplicationContext(), "Can't identify this QR code", Toast.LENGTH_SHORT).show();
        }
        startActivity(new Intent(Scanner.this,grifoi.class));
    }

}
