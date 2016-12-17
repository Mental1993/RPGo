package com.example.mental.rpgo;



public class Global{ //boh8itikh klassi opou mpainoun oi static metablites

    private static String id;
    private static String Nogrif ;
    private static boolean achivement_timePassed = false, achivement_locVisited = false, achivement_riddlesSolved = false;

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





}