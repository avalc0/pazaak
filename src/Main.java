import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args){
        PazaakManager pazaak = new PazaakManager();

        Scanner scnr = new Scanner(System.in);
        System.out.println("NEW HAND? (y/n)");
        String t = scnr.nextLine().toLowerCase();
        while(t.equals("y")) {
            System.out.println("STARTING NEW ROUND");
            pazaak.playPazaakRound();
            System.out.println("NEW HAND? (y/n)");
            t = scnr.nextLine().toLowerCase();
        }
        System.out.println("ENDING PROGRAM...");
    }
}
