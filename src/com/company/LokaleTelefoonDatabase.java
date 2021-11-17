package com.company;

public class LokaleTelefoonDatabase {
    static Gebruiker huidigeGebruiker = new Gebruiker(null, null, null);
    static String huidigeGebruikersNaam = "";
    static String huidigeWachtwoord = "";
    static String huidigeEmail = "";
    static boolean isGast = false;

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

    public static String getHuidigeEmail() {
        return huidigeEmail;
    }

    public static void setHuidigeEmail(String huidigeEmail) {
        LokaleTelefoonDatabase.huidigeEmail = huidigeEmail;
    }

    public static boolean getIsGast() {
        return isGast;
    }

    public static void setIsGast(boolean setisGast) {
        isGast = setisGast;
        if(isGast) {
            huidigeGebruikersNaam = "Gast";
            huidigeWachtwoord = null;
            huidigeEmail = null;
            huidigeGebruiker = new Gebruiker(huidigeGebruikersNaam, huidigeWachtwoord, huidigeEmail);
        }
    }
}
