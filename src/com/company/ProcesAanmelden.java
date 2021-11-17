package com.company;

import java.util.Scanner;
import java.util.regex.Pattern;

import static java.lang.Character.*;

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

        //TODO add checker die kijkt of er een gebruiker opgeslagen staat in LokaleTelefoonDatabase en log daarmee in als gegevens kloppen met GebruikersDatabase

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
                registreren();
                break;
            case "3": //Gast login
                LokaleTelefoonDatabase.setIsGast(true);
                welkomScherm();
                break;
            case "0": //Afsluiten
                System.out.println("Tot ziens!");
                break;
            case "9": //Geeft de lijst met gebruikers TEST
                System.out.println("\n\n\n\n\n");
                GebruikersDatabase.printFullList();
                System.out.print("\n<Press ENTER to continue>");
                scanner.nextLine();
                System.out.println("\n\n\n");
                startAanmeldProces();
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
        System.out.println("\nKloppen de bovenstaande gegevens?\n1. Bevestig\n" +
                "2. Probeer opnieuw\n\n" +
                "0. Stop met inloggen");
        String bevestig = scanner.nextLine();
        switch (bevestig) {
            case "1":   //Bevestigt
                if(checkJuisteLogin(gebruikersnaam, wachtwoord)) { //Checken of combinatie gebruikersnaam en wachtwoord voorkomt in database
                    //Ingelogd
                    System.out.println("Correcte Login");
                    System.out.print("\n<Press ENTER to continue>");
                    scanner.nextLine();
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
                        System.out.print("\n<Press ENTER to continue>");
                        scanner.nextLine();
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
        System.out.printf("\n\n\nWelkom %s in OMW", LokaleTelefoonDatabase.getHuidigeGebruikersNaam());
    }
    //Hier eindigd het login proces



    //Hier begint het registreren proces
    private static void registreren() {
        //Variabelen aanmaken
        Scanner scanner = new Scanner(System.in);
        String gebruikersnaam = null;
        String wachtwoord = null;
        String email = null;
        boolean valid = false;  //Variabele om te checken of de invoer geldig is


        String registratieBericht = "\n\nWelkom op de registratiepagina!\n" +
                                    "Vul uw gegevens in (\"*\" zijn verplichte velden)\n\n" +
                                    "Gebruikersnaam*: ";    //Dit is het registratiebericht


        //Gebruikersnaam invoeren + checken
        while (!valid) {//Blijft in de loop zolang invoer fout is
            System.out.print(registratieBericht); //Print het registratieBericht
            gebruikersnaam = scanner.nextLine(); //Invoeren gebruikersnaam
            if (GebruikersDatabase.checkGebruikersNaam(gebruikersnaam)) {   //Checken of gebruikersnaam in de GebruikersDatabase voorkomt
                //gebruikersnaam komt wel voor in de GebruikersDatabase
                System.out.println("\n\"" + gebruikersnaam + "\" is al in gebruik.\n" +
                        "Probeer het opnieuw!");
                System.out.print("\n<Press ENTER to continue>");
                scanner.nextLine();
            } else if (gebruikersnaam.length() < 3) { //Checken of gebruikersnaam 3 tekens of langer is
                //gebruikersnaam is korter dan 3 tekens
                System.out.println("\nUw gebruikersnaam moet minimaal 3 tekens lang zijn.\n" +
                        "Probeer het opnieuw!");
                System.out.print("\n<Press ENTER to continue>");
                scanner.nextLine();
            } else {
                //Gebruikersnaam is goedgekeurd
                registratieBericht = registratieBericht + gebruikersnaam + "\n    Wachtwoord*: "; //Voeg gebruikersnaam toe aan het registratieBericht
                valid = true;
            }
        }
        valid = false; //Waarde weer resetten voor volgende loop
        System.out.print("    Wachtwoord*: ");

        //Wachtwoord invoeren + checken
        while (!valid) {//Blijft in de loop zolang invoer fout is
            wachtwoord = scanner.nextLine(); //Invoeren wachtwoord
            if (!wachtwoordEisen(wachtwoord)) {
                //Wachtwoord voldoet niet aan de eisen
                System.out.print(registratieBericht);
            } else {
                //Gebruikersnaam is goedgekeurd
                registratieBericht = registratieBericht + wachtwoord + "\n         email*: "; //Voeg wachtwoord toe aan het registratieBericht
                valid = true;
            }
        }
        valid = false;

        //Email invoeren + checken
        System.out.print("         email*: ");
        while (!valid) {
            email = scanner.nextLine();
            if(isValidEmail(email)) {
                valid = true;
                registratieBericht = registratieBericht + email;
            }else {
                System.out.println( "\n\nOngeldige email.\n" +
                                    "Probeer opnieuw!");
                System.out.print("\n<Press ENTER to continue>");
                scanner.nextLine();
                System.out.print(registratieBericht);
            }
        }

        //TODO Geboortedatum(optioneel) invoeren + checken

        //TODO keuze OV-chipkaart toevoegen
        //Bevestig registratie
        valid = false;
        while (!valid) {
            System.out.println(registratieBericht + "\n\nKloppen de bovenstaande gegevens?\n" +
                    "1. Ja\n" +
                    "2. Nee\n\n" +
                    "0. Annuleer registratie");
            switch (scanner.nextLine()) {
                case "1":
                    valid = true;
                    GebruikersDatabase.addGebruiker(new Gebruiker(gebruikersnaam, wachtwoord, email));
                    System.out.println("Registratie compleet");
                    System.out.print("\n<Press ENTER to continue>");
                    scanner.nextLine();
                    System.out.println("\n\n\n\n\n\n");
                    startAanmeldProces();
                    break;
                case "2":
                    valid = true;
                    System.out.println("\n\n\n\n\n\n\n\n");
                    registreren();
                    break;
                case "0":
                    valid = true;
                    System.out.println("\n\n\n\n\n\n\n\n");
                    startAanmeldProces();
                    break;
                default:
                    System.out.println("Ongeldige invoer, probeer opnieuw");
                    System.out.print("\n<Press ENTER to continue>");
                    scanner.nextLine();
            }
        }
    }

    private static boolean wachtwoordEisen(String wachtwoord) {
        Scanner scanner = new Scanner(System.in);
        //Hier komen de eisen
        int minLengte = 8;
        int minLetters = 2;
        int minCijfers = 2;
        int minHoofdletters = 1;


        String eisen =  "    Wachtwoord moet minimaal " + minLengte + " tekens lang zijn.\n" +
                        "    Wachtwoord moet minimaal " + minLetters + " letters en " + minCijfers + " cijfers bevatten.\n" +
                        "    Wachtwoord moet minimaal " + minHoofdletters + " hoofdletter bevatten.\n";

        if(!checkWachtwoordEisen(wachtwoord, minLengte, minLetters, minCijfers, minHoofdletters)) {
            System.out.println("\nOngeldig wachtwoord.\n" +
                    "Dit zijn de eisen:\n" + eisen +
                    "\n\nProbeer het opnieuw!");
            System.out.print("\n<Press ENTER to continue>");
            scanner.nextLine();
            return false;
        }
        return true; //Wachtwoord voldoet aan de eisen
    }

    private static boolean checkWachtwoordEisen(String wachtwoord, int minLengte, int minLetters, int minCijfers, int minHoofdletters) {
        int charCount = 0;  //Telt letters
        int numCount = 0;   //Telt cijfers
        int upperCount = 0; //Telt hoofdletters

        if(wachtwoord.length() >= minLengte) { //Checken lengte wachtwoord
            for (int i = 0; i < wachtwoord.length(); i++) { //Loop door het wachtwoord
                char ch = wachtwoord.charAt(i); //Pakt letter i en stopt het in char ch

                if (isDigit(ch)) numCount++; //Kijken of cijfer
                else if (isLetter(ch)) { //Kijken of letter
                    charCount++;
                    if (isUpperCase(ch)) upperCount++; //Kijken of hoofdletter
                }
            }
            return (charCount >= minLetters) && (numCount >= minCijfers) && (upperCount >= minHoofdletters); //Checken of voldoet aan alle eisen
        }else { //Wachtwoord is niet lang genoeg
            return false;
        }
    }

    public static boolean isValidEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
