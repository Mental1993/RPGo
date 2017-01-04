package com.example.mental.rpgo;


import android.location.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * This class stores all the global variables and their corresponding functions required thoughout the application
 * @author Mental
 * @version 1.1
 */
public class Global{ //boh8itikh klassi opou mpainoun oi static metablites

    public static double KEYS_REGENERATE_INTERVAL = 86400;
    private static String id;
    private static String Nogrif ;
    private static boolean achivement_timePassed = false, achivement_locVisited = false, achivement_riddlesSolved = false, achivement_keysCollected;
    private static int keys_count;
    private static int image;
    private static List<Location> keys_loc = new ArrayList<Location>();

    private static int grifLoad;

    /**
     * Gets the {@link String} instance of the user's id
     * @return The {@link String} instance of the user's id
     */
    public static String getId() {
        return id;
    }

    /**
     * Gets the {@link Integer} instance of the next riddle id to show
     * @return The {@link Integer} instance of the next riddle id to show
     */
    public static int getGrifLoad() {
        return grifLoad;
    }

    /**
     * Sets the {@link Integer} instance of the next riddle id to show
     * @param grifLoad int: The new value of the riddle id to be shown
     */
    public static void setGrifLoad(int grifLoad) {
        Global.grifLoad = grifLoad;
    }

    /**
     * Sets the {@link String } instance of the user's id
     * @param newId String: The new value of the user's id
     */
    public static void setId(String newId) { id = newId; }

    /**
     * Gets the {@link String} instance of the current riddle id
     * @return The {@link String} of the current riddle id
     */
    public static String getNogrif() {
        return Nogrif;
    }

    /**
     * Sets the {@link String} instance of the current riddle id
     * @param newNogrif String: The new value of the current riddle id
     */
    public static void setNogrif(String newNogrif) {
        Nogrif = newNogrif;
    }

    //Achivement variables

    /**
     * Gets the {@link Boolean} instance of the time passed achivement
     * @return The {@link} instance of the time passed achivement
     */
    public static boolean getAchivement_timePassed () { return achivement_timePassed; }

    /**
     * Sets the {@link Boolean} instance of the time passed achivement
     * @param value Boolean: The new value of the time passed achivement
     */
    public static void setAchivement_timePassed (boolean value) { achivement_timePassed = value; }

    /**
     * Gets the {@link Boolean} instance of the locations visited achivement
     * @return The {@link Boolean} instance of the locations visited achivement
     */
    public static boolean getAchivement_locVisited () { return achivement_locVisited; }

    /**
     * Sets the {@link Boolean} instance of the locations visited achivement
     * @param value Boolean: The new value of the locations visited achivement
     */
    public static void setAchivement_locVisited (boolean value) { achivement_locVisited = value; }

    /**
     * Gets the {@link Boolean} instance of the riddles solved achivement
     * @return The {@link Boolean} instance of the riddles solved achivement
     */
    public static boolean getAchivement_riddlesSolved () { return achivement_riddlesSolved; }

    /**
     * Sets the {@link Boolean} instance of the riddles solved achivement
     * @param value Boolean: The new value of the riddles solved achivement
     */
    public static void setAchivement_riddlesSolved (boolean value) { achivement_riddlesSolved = value; }

    /**
     * Sets the {@link Boolean} instance of the keys collected achivement
     * @param achivement_keysCollected Boolean: The new value of the keys collected achivement
     */
    public static void setAchivement_keysCollected(boolean achivement_keysCollected) { Global.achivement_keysCollected = achivement_keysCollected; }

    /**
     * Gets the {@link Boolean} instance of the keys collected achivement
     * @return The {@link Boolean} instance of the keys collected achivement
     */
    public static boolean getAchivement_keysCollected() { return achivement_keysCollected; }

    /**
     * Gets the {@link Integer} instance of the keys collected by the user
     * @return The {@link Integer} instance of the keys collected by the user
     */
    public static int getKeys_count() { return keys_count; }

    /**
     * Sets the {@link Integer} instance of the keys collected by the user
     * @param keys_count int: The new value of the keys collected by the user
     */
    public static void setKeys_count(int keys_count) { Global.keys_count = keys_count; }

    /**
     * Gets the {@link List} instance of the keys locations
     * @return The {@link List} instance of the keys locations
     */
    public static List<Location> getKeys_loc() {
        return keys_loc;
    }

    /**
     * Gets the {@link Integer} instance of the image selected by the user
     * @return The {@link Integer} instance of the image selected by the user
     */
    public static int getImage() {
        return image;
    }

    /**
     * Sets the {@link Integer} instance of the image selected by the user
     * @param image int: The new value of the image selected by the user
     */
    public static void setImage(int image) {
        Global.image = image;
    }
}