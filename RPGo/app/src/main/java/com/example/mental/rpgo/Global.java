package com.example.mental.rpgo;



public class Global{ //boh8itikh klassi opou mpainoun oi static metablites

    private static String id;
    private static String Nogrif ;
    private static float riddle_progress;

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

}