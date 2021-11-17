package com.company;

public class Main {

    public static void main(String[] args) {
        ProcesAanmelden.addGebruikersTest();
        ProcesAanmelden.setHuidigeGebruiker("Piet", "Wachtwoord123");
        while (true) {
            ProcesAanmelden.startAanmeldProces();
        }
    }
}
