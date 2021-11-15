package com.company;

import java.util.Scanner;

public class ProcesAanmelden {
    //Aantal inlogpoginen
    private static int pogingLogin = 3;

    //Deze methode is om te testen. Zet deze in de main functie om een lijst gebruikers toe te voegen aan GebruikersDatabase
    public static void addGebruikersTest() {
        GebruikersDatabase.addGebruiker(new Gebruiker("Piet", "Wachtwoord123", "pietjemietje@gmail.com"));
        GebruikersDatabase.addGebruiker(new Gebruiker("Arold", "woordje321", "AroldDeHarold@live.nl"));
        GebruikersDatabase.addGebruiker(new Gebruiker("Hannah", "Pindakaas45", "Hannanah@gmail.com"));
        GebruikersDatabase.addGebruiker(new Gebruiker("Vince", "OranjeKoek69", "vincenator@hotmail.com"));
    }

    //Hier start het aanmeldproces
    public static void startAanmeldProces() {
        //Scanner maken
        Scanner scanner = new Scanner(System.in);

        //Weergeven aanmeldpagina
        System.out.println("\n\nWelkom op de aanmeldpagina!\n" +
                "1. Login\n" +
                "2. Registreren\n" +
                "3. Gast login\n\n" +
                "0. Afsluiten");

        //Maken keuze 1. Inloggen   2. Registreren  3. Gast login   0. applicatie sluiten
        switch (scanner.nextLine()) {
            case "1": //Login
                login();
                break;
            case "2": //Registreren
                break;
            case "3": //Gast login
                break;
            case "0": //Afsluiten
                System.out.println("Tot ziens!");
                break;
            default:  //Ongeldige invoer
                System.out.println("Geen geldige invoer. Probeer het opnieuw\n\n");
                startAanmeldProces(); //Start opnieuw
                break;
        }
    }

    //Hier start het inlogproces
    private static void login() {
        //Scanner maken
        Scanner scanner = new Scanner(System.in);

        //Login pagina weergeven
        System.out.println("\n\nWelkom op de inlog pagina!\n");

        //Gebruikersnaam invoeren
        System.out.print("Gebruikersnaam: ");
        String gebruikersnaam = scanner.nextLine();

        //Wachtwoord invoeren
        System.out.print("    Wachtwoord: ");
        String wachtwoord = scanner.nextLine();

        //Bevestiggen invoer
        System.out.println( "1. Bevestig\n" +
                "2. Probeer opnieuw\n\n" +
                "0. Stop met inloggen");
        String bevestig = scanner.nextLine();
        switch (bevestig) {
            case "1":   //Bevestigt
                if(checkJuisteLogin(gebruikersnaam, wachtwoord)) { //Checken of combinatie gebruikersnaam en wachtwoord voorkomt in database
                    //Ingelogd
                    System.out.println("Correcte Login");
                    welkomScherm(); //Ga naar welkom scherm
                }else{
                    //Foute login
                    pogingLogin--;

                    //Print hieronder de juiste reactie
                    if (pogingLogin == 1) { //1 poging over
                        System.out.println("Onjuiste Login, u heeft nog " + pogingLogin + " poging over.");
                    }else if (pogingLogin <=0){ //Geen poginen over
                        System.out.println("Onjuiste Login, u heeft geen pogingen meer over.");
                    }else {//2 of meer pogingen over
                        System.out.println("Onjuiste Login, u heeft nog " + pogingLogin + " pogingen over.");
                    }

                    //Checken of er nog pogingen over zijn
                    if(pogingLogin <= 0) { //Geen poginen meer over
                        System.out.println("U kunt niet meer inloggen voor 1 uur"); //Stoppen met login
                    }else{ //Wel pogingen over
                        System.out.println("Probeer het opnieuw");
                        login(); //probeer opnieuw
                    }
                }
                break;
            default: //Geen geldige invoer voor bevestig gaat vervolgens door naar case "2" doordat break; er niet is.
                System.out.println("Geen geldige invoer. Probeer het opnieuw");
            case "2": //Opnieuw inloggen
                System.out.println("\n\n");
                login();
                break;
            case "0": //Stoppen met inloggen
                startAanmeldProces(); //Ga terug naar het aanmeld proces
                break;
        }
    }

    //Checken of combinatie gebruikersnaam en wachtwoord juist is
    private static boolean checkJuisteLogin(String gebruikersnaam, String wachtwoord) {
        for(int i = 0; i < GebruikersDatabase.getGebruikers().size(); i++) { //Loop door GebruikersDatabase
            if(gebruikersnaam.equals(GebruikersDatabase.getGebruikers().get(i).getGebruikersNaam())) {  //Checken of gebruikersnaam voorkomt in GebruikersDatavase
                if(wachtwoord.equals(GebruikersDatabase.getGebruikers().get(i).getWachtwoord())) {  //Checken of wachtwoord klopt voor gegeven gebruikersnaam
                    //Zet de data neer in de lokale opslag van de telefoon
                    LokaleTelefoonDatabase.setHuidigeGebruiker(GebruikersDatabase.getGebruikers().get(i));
                    LokaleTelefoonDatabase.setHuidigeGebruikersNaam(gebruikersnaam);
                    LokaleTelefoonDatabase.setHuidigeWachtwoord(wachtwoord);
                    return true; //Combinatie gebruikersnaam en wachtwoord zat wel in de GebruikersDatabase
                }
            }
        }
        return false; //Combinatie gebruikersnaam en wachtwoord zat niet in de GebruikersDatabase
    }

    //Welkomsbericht na succesvolle login
    private static void welkomScherm() {
        System.out.printf("Welkom %s in OMW", LokaleTelefoonDatabase.getHuidigeGebruikersNaam());
    }
}
