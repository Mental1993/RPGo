package com.example.mental.rpgo;



public class Global{ //boh8itikh klassi opou mpainoun oi static metablites

    private static String id;

    private static String Nogrif ;

    public static String getId() {
        return id;
    }

    public static String getNogrif() {
        return Nogrif;
    }

    public static void setNogrif(String nogrif) {
        Nogrif = nogrif;
    }

    public void setId(String id) {
        this.id = id;
    }

}