package com.company;
import java.util.Scanner;

public class ProcesReisAanmelden {
    public static void ProcesReisAanmelden(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Welkom in OMW \nMaak hier u keuze");
        System.out.println("1. Nieuwe reis aanmelden");
        System.out.println("2. Opgeslagen reizen bekijken");
        System.out.println("3. Carpool");
        switch (sc.nextLine()){
            case "1":
                nieuw();
                break;
            case "2":
                opgeslagen();
                break;
            case "3":
                carpool();
                break;
            default:
                System.out.println("Sorry dat begreep ik niet, probeer het opnieuw");
                ProcesReisAanmelden();
                break;
        }
    }
    public static void nieuw(){

    }
    public static void opgeslagen(){

    }
    public static void carpool(){

    }
}
