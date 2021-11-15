package com.company;

import java.util.ArrayList;

public class GebruikersDatabase {
    static ArrayList<Gebruiker> gebruikers = new ArrayList<>();

    public static void addGebruiker(Gebruiker gebruiker) {
        gebruikers.add(gebruiker);
    }
    public static ArrayList<Gebruiker> getGebruikers(){
        return gebruikers;
    }
}
