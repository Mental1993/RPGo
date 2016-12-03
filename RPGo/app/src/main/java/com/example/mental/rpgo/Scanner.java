package com.example.mental.rpgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


/**
 * Created by Zoi on 27/11/2016.
 */

public class Scanner extends AppCompatActivity implements ZXingScannerView.ResultHandler
{
    private ZXingScannerView mScannerView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mScannerView = new ZXingScannerView(this);

        setContentView(mScannerView);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        mScannerView.setResultHandler(this);

        mScannerView.startCamera();
    }

    @Override
    public void onPause()
    {
        super.onPause();

        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult)
    {
        if(rawResult.getText().equals("grifos1")){
            startActivity(new Intent(Scanner.this,grifos1.class));
        }
        else if(rawResult.getText().equals("grifos2")) {
            startActivity(new Intent(Scanner.this, grifos2.class));
        } else {
            Toast.makeText(getApplicationContext(), "Can't identify this QR code", Toast.LENGTH_SHORT).show();
        }

        finish();
    }

}
