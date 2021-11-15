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

    public static boolean checkGebruikersNaam(String gebruikersNaam) {
        for(int i = 0; i < gebruikers.size(); i++) {
            if(gebruikersNaam.equals(gebruikers.get(i).getGebruikersNaam())) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkWachtwoord(String wachtwoord) {
        for(int i = 0; i < gebruikers.size(); i++) {
            if(wachtwoord.equals(gebruikers.get(i).getWachtwoord())) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkEmail(String email) {
        for(int i = 0; i < gebruikers.size(); i++) {
            if(email.equals(gebruikers.get(i).getEmail())) {
                return true;
            }
        }
        return false;
    }

    //Methode die de info van alle gebruikers print(Deze functie is bedoelt om te testen)
    public static void printFullList(){
        String naam = "Naam";
        String wachtwoord = "Wachtwoord";
        String email = "email";
        System.out.printf("%50s%50s%50s\n\n", naam, wachtwoord, email);
        for(int i = 0; i < gebruikers.size(); i++) {
            naam = gebruikers.get(i).getGebruikersNaam();
            wachtwoord = gebruikers.get(i).getWachtwoord();
            email = gebruikers.get(i).getEmail();
            System.out.printf("%50s%50s%50s\n", naam, wachtwoord, email);
        }
    }
}
