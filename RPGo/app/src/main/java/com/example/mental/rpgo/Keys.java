package com.example.mental.rpgo;

import android.graphics.Bitmap;
import android.location.Location;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Random;

/**
 * Created by Mental on 17/12/2016.
 */

public class Keys {


    //Keys attributes
    private String name;


    //Setters and Getters
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void generateKey(GoogleMap map, Bitmap icon, int i) {
        Location loc = Global.getKeys_loc().get(i);
        LatLng pos = new LatLng(loc.getLatitude(), loc.getLongitude());
        map.addMarker(new MarkerOptions()
                .position(pos)
                .title("Key Location")
                .icon(BitmapDescriptorFactory.fromBitmap(icon))
        );
    }

    //Generate a random location fairly close to the user's location
    public Location getRandomLocation() {
        Random lat = new Random();
        double lowLat = 40.5000000;
        double resLat = 1.001*lat.nextDouble() + lowLat;

        Random lng = new Random();
        double lowLng = 23.2000000;
        double resLng = 1.001*lng.nextDouble() + lowLng;
        Location loc = new Location("Key");
        loc.setLatitude(resLat);
        loc.setLongitude(resLng);
        return loc;
    }
}
