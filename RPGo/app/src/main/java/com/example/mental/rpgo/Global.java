package com.example.mental.rpgo;



public class Global{ //boh8itikh klassi opou mpainoun oi static metablites

    private static String id;
    private static String Nogrif ;
    private static boolean achivement_timePassed;

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

    public static boolean getAchivement_timePassed () { return achivement_timePassed; }

    public static void setAchivement_timePassed (boolean value) { achivement_timePassed = value; }



}