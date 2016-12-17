package com.example.mental.rpgo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.MarkerOptions;


public class Map extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private Keys key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        key = new Keys();

        String provider = android.provider.Settings.Secure.getString(
                getContentResolver(),
                android.provider.Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if (!provider.contains("gps")) { // if gps is disabled
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            ;
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        //Resources res = getResources();
        //Drawable drawable = res.getDrawable(R.drawable.key);
        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.key);
        Bitmap b=bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 50, 50, false);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        mMap.setMyLocationEnabled(true);
        for(int i=0; i<8; i++) {
            key.generateKey(mMap, smallMarker);
        }

        if (Global.getNogrif().equals("1")) {
            LatLng TEI = new LatLng(41.074033, 23.552689); //1o marker
            mMap.addMarker(new MarkerOptions()
                    .position(TEI)
                    .title("Riddle 1")//.title("TEI Kentrikis Makedomias")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pushpin)));
            if (mMap != null) {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(TEI, 16));
            }
        }else if (Global.getNogrif().equals("2")) {
                LatLng amaks = new LatLng(41.075958, 23.551359);//2o marker
                mMap.addMarker(new MarkerOptions()
                        .position(amaks)
                        .title("Riddle 2")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pushpin)));
                if (mMap != null) {
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(amaks, 16));
                }
        }else if (Global.getNogrif().equals("3")) {
            LatLng nav = new LatLng(41.077753, 23.549063);
            mMap.addMarker(new MarkerOptions()
                    .position(nav)
                    .title("Riddle 3")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pushpin)));
            if(mMap != null) {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nav, 16));
            }
        }else if (Global.getNogrif().equals("4")) {
                LatLng parko = new LatLng(41.079394, 23.548898);
                mMap.addMarker(new MarkerOptions()
                        .position(parko)
                        .title("Riddle 4")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pushpin)));
            if(mMap != null) {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(parko, 16));
            }
        }else if(Global.getNogrif().equals("5")){
            LatLng bp = new LatLng(41.082157, 23.549396);
            mMap.addMarker(new MarkerOptions()
                    .position(bp)
                    .title("Riddle 5")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pushpin)));
            if(mMap != null) {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bp, 16));
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(getApplicationContext(), "Location changed!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}


