package com.company;

public class LokaleTelefoonDatabase {
    static Gebruiker huidigeGebruiker = new Gebruiker(null, null, null);
    static String huidigeGebruikersNaam = "";
    static String huidigeWachtwoord = "";

    public static Gebruiker getHuidigeGebruiker() {
        return huidigeGebruiker;
    }

    public static void setHuidigeGebruiker(Gebruiker gebruiker) {
        huidigeGebruiker = gebruiker;
    }

    public static String getHuidigeGebruikersNaam() {
        return huidigeGebruikersNaam;
    }

    public static void setHuidigeGebruikersNaam(String gebruikersNaam) {
        huidigeGebruikersNaam = gebruikersNaam;
    }

    public static String getHuidigeWachtwoord() {
        return huidigeWachtwoord;
    }

    public static void setHuidigeWachtwoord(String wachtwoord) {
        huidigeWachtwoord = wachtwoord;
    }
}
