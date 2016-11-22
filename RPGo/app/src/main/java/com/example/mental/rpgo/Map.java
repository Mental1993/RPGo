package com.example.mental.rpgo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

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





       


            if (Global.getId().equals("2")) {
                LatLng amaks = new LatLng(41.075958, 23.551359);//2o marker
                mMap.addMarker(new MarkerOptions()
                        .position(amaks)
                        .title("2os grifos")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pushpin)));
                if (mMap != null) {
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(amaks, 16));
                }
            }

            if (Global.getId().equals("1")) {
                //tsekarw to id tou xristi, an einai 1, bazw to point sto 1o stoxo
                // auto se epomenh ekdosh 8a allaksei, kai 8a pairnei ton ari8mo tou grifou pou 8a eixei na lusei o xristis
                LatLng TEI = new LatLng(41.074033, 23.552689); //1o marker
                mMap.addMarker(new MarkerOptions()
                        .position(TEI)
                        .title("1os grifos")//.title("TEI Kentrikis Makedomias")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pushpin)));
                if (mMap != null) {
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(TEI, 16));
                }


                if (Global.getId().equals("3")) {

                LatLng nav = new LatLng(41.077753, 23.549063);
                mMap.addMarker(new MarkerOptions()
                        .position(nav)
                        .title("3os grifos")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pushpin)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nav, 16));
            }

            if (Global.getId().equals("4")) {

                LatLng parko = new LatLng(41.079394, 23.548898);
                mMap.addMarker(new MarkerOptions()
                        .position(parko)
                        .title("4os grifos")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pushpin)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(parko, 16));
            }
        }

    }
}

