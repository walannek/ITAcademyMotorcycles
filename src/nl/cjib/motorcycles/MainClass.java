package nl.cjib.motorcycles;

import java.util.Scanner;

public class MainClass {
    private static boolean exitMenu=false;
    public static void main(String... args){
        Scanner scanner = new Scanner(System.in);
        int selection;

        while (!exitMenu) {
            System.out.println("============================");
            System.out.println("|   MENU Motorcycles       |");
            System.out.println("============================");
            System.out.println("| Options:                 |");
            System.out.println("|   1. Show motorcycles    |");
            System.out.println("|   2. Add motorcycle      |");
            System.out.println("|   3. Delete motorcycle   |");
            System.out.println("|   4. Exit                |");
            System.out.println("============================");
            System.out.println("Selection: ");
            selection = scanner.nextInt();

            switch (selection) {

                case 1:
                    System.out.println("Show motorcycles");
                    // TODO toon de geregisteerde motoren
                    break;
                case 2:
                    System.out.println("Add motorcycle");
                    // TODO registreer een nieuwe motorcycle
                    break;
                case 3:
                    System.out.println("Delete motorcycle");
                    // TODO verwijder een al geregisteerde motorcycle
                    break;
                case 4:
                    System.out.println("Exit Successful");
                    exitMenu = true;

                default:
                    System.out.println("Please enter a valid selection.");
            }
        }
    }
}
