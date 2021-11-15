package com.company;

public class Gebruiker {
    private String gebruikersNaam, wachtwoord, email;

    public Gebruiker(String gebruikersNaam, String wachtwoord, String email) {
        this.gebruikersNaam = gebruikersNaam;
        this.wachtwoord = wachtwoord;
        this.email = email;
    }

    public String getGebruikersNaam() {
        return gebruikersNaam;
    }

    public void setGebruikersNaam(String gebruikersNaam) {
        this.gebruikersNaam = gebruikersNaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
