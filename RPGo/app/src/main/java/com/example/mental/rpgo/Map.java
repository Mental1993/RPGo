package com.example.mental.rpgo;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Map extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        String provider = android.provider.Settings.Secure.getString(
                getContentResolver(),
                android.provider.Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if (!provider.contains("gps")) { // if gps is disabled
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));;
        }
    }
}

mMap.setMyLocationEnabled(true); //deixnw thn 8esh tou xristi


/**
 */
        if (Global.getId().equals("2")) {
        LatLng amaks = new LatLng(41.075958, 23.551359);//2o marker
        mMap.addMarker(new MarkerOptions()
        .position(amaks)
        .title("2os grifos")
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pushpin)));
        if (mMap != null) {
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(amaks, 16));
