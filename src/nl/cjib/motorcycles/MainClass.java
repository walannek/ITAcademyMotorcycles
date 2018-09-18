package nl.cjib.motorcycles;

import nl.cjib.motorcycles.utils.InitMotorcycles;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MainClass {
    private static Map<String, ArrayList<Motor>> motorcycles;
    private static boolean exitMenu= false;
    private static final Pattern oneDigitPattern = Pattern.compile("^\\d$");
    private static final int AANTAL_CHAR_BESCHIKBAAR = 23;
    private static String keyInput;
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String... args){

        int selection;
        InitMotorcycles initMotorcycles = new InitMotorcycles();
        motorcycles = initMotorcycles.getmotorCycles();

        while (!exitMenu) {
            //printMenuKop("MENU Motorcycles");
            printMenu("MENU Motorcycles");
            printMenu("Options:","1. Show motorcycles","2. Add motorcycle","3. Delete motorcycle","4. Exit");
            System.out.println("Selection: ");
            keyInput = scanner.next();
            if (oneDigitPattern.matcher(keyInput).matches()) {
                selection = Integer.parseInt(keyInput);
                switch (selection) {
                    case 1:
                        showMotorcycles();
                        waitAMoment();
                        break;
                    case 2:
                        addMotorCycle();
                        waitAMoment();
                        break;
                    case 3:
                        System.out.println("Delete motorcycle");
                        // TODO verwijder een al geregisteerde motorcycle
                        break;
                    case 4:
                        exitMenu = true;
                        break;
                    default:
                        System.out.println("Please enter a valid selection.");
                }
            } else {
                System.out.println("Please enter a valid selection.");
            }
        }
        System.out.println("Exit Successful");
        System.exit(0);
    }

    private static void showMotorcycles(){
        printMenu("Show Motorcycles");
        System.out.println(" Aantal merken: " + motorcycles.values().size());
        System.out.println(" Merken: " + motorcycles.keySet().toString());

        for(String key : motorcycles.keySet()){
            ArrayList<Motor> brands = motorcycles.get(key);
            printMenu(key+ " MOTOREN : " + brands.size());
            for(Motor motor : motorcycles.get(key).subList(0,brands.size())){
                System.out.println("           type:" + motor.getMotorType().getType());
                System.out.println("             pk:" + motor.getPk());
                System.out.println("cilinder inhoud:" + motor.getCilinderInhoud());
                System.out.println("          color:" + motor.getColour());
                System.out.println("           fuel:" + motor.getFuel());
                System.out.println("        totalKM:" + motor.getTotalKM());
                System.out.println("      onderhoud:" + motor.getOnderhoudsType());
                System.out.println();
            }
        }
    }

    private static void addMotorCycle() {
        printMenu("Add Motorcycle");
        System.out.println("voer merk in: ");
        Scanner scannerNewMotor = new Scanner(System.in);
        keyInput = scannerNewMotor.next().toUpperCase();
        if (merkStaatGeregisteerd(keyInput)) {
            String merk = keyInput.toUpperCase();
            System.out.println("voer type in: ");
            scannerNewMotor = new Scanner(System.in);
            String merkType = scannerNewMotor.nextLine().toUpperCase();

            ArrayList<Motor> motorList = new ArrayList<>(motorcycles.get(merk).subList(0, motorcycles.get(merk).size()));
            InitMotorcycles.addMotorenToBrand(motorList, merk, merkType);
            motorcycles.replace(merk, motorList);
            System.out.println("Merk " + merk + " en type " + merkType + " is geregisteerd");
        } else {
            System.out.println("merk don't exists");
            // TODO toevoegen van een nieuw merk motorcycle
        }
    }

    private static void waitAMoment() {
        Scanner scannerWait = new Scanner(System.in);
        System.out.println("druk op een toets om verder te gaan ");
        scannerWait.nextLine();
    }

    private static void printMenu(String... options){
        StringBuilder sb = new StringBuilder();
        sb.append("============================");
        sb.append("\n");
        for(String menuItem : options) {
            sb.append("|   ").append(String.format("%-" + AANTAL_CHAR_BESCHIKBAAR + "s", menuItem)).append("|");
            sb.append("\n");
        }
        sb.append("============================");
        System.out.println(sb);
    }

    private static boolean merkStaatGeregisteerd(String invoerMerk){
        return motorcycles.keySet().contains(keyInput);
    }
}
