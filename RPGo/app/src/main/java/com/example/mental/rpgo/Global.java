package com.example.mental.rpgo;


import android.location.Location;

import java.util.ArrayList;
import java.util.List;

public class Global{ //boh8itikh klassi opou mpainoun oi static metablites

    public static double KEYS_REGENERATE_INTERVAL = 86400;
    private static String id;
    private static String Nogrif ;
    private static boolean achivement_timePassed = false, achivement_locVisited = false, achivement_riddlesSolved = false, achivement_keysCollected;
    private static int keys_count;
    private static List<Location> keys_loc = new ArrayList<Location>();

    public static String getId() {
        return id;
    }

    public static void setId(String newId) { id = newId; }

    public static String getNogrif() {
        return Nogrif;
    }

    public static void setNogrif(String newNogrif) {
        Nogrif = newNogrif;
    }

    //Achivement variables
    public static boolean getAchivement_timePassed () { return achivement_timePassed; }
    public static void setAchivement_timePassed (boolean value) { achivement_timePassed = value; }

    public static boolean getAchivement_locVisited () { return achivement_locVisited; }
    public static void setAchivement_locVisited (boolean value) { achivement_locVisited = value; }

    public static boolean getAchivement_riddlesSolved () { return achivement_riddlesSolved; }
    public static void setAchivement_riddlesSolved (boolean value) { achivement_riddlesSolved = value; }

    public static void setAchivement_keysCollected(boolean achivement_keysCollected) { Global.achivement_keysCollected = achivement_keysCollected; }
    public static boolean getAchivement_keysCollected() { return achivement_keysCollected; }

    public static int getKeys_count() { return keys_count; }
    public static void setKeys_count(int keys_count) { Global.keys_count = keys_count; }

    public static List<Location> getKeys_loc() {
        return keys_loc;
    }
}