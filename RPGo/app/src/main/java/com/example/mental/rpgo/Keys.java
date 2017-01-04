package com.example.mental.rpgo;

import android.graphics.Bitmap;
import android.location.Location;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Random;

/**
 * This class handles the Keys and their location.
 * @author Mental
 * @version 1.0
 */

public class Keys {


    //Keys attributes
    private String name;


    /**
     * Sets the {@link String} instance of the keys location name
     * @param name String: The keys location name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the {@link String} instance of the keys location name
     * @return The {@link String} instance of the keys location name
     */
    public String getName() {
        return name;
    }


    /**
     * Generates a random keys location
     * @param map GoogleMap: The GoogleMap on which the keys location wil be displayed
     * @param icon Bitmap: The incon of the keys location
     * @param i int: index of keys location array
     */
    public void generateKey(GoogleMap map, Bitmap icon, int i) {
        Location loc = Global.getKeys_loc().get(i);
        LatLng pos = new LatLng(loc.getLatitude(), loc.getLongitude());
        map.addMarker(new MarkerOptions()
                .position(pos)
                .title("Key Location")
                .icon(BitmapDescriptorFactory.fromBitmap(icon))
        );
    }

    /**
     * Gererates a random location
     * @return A {@link Location} instance of the random location
     */
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
