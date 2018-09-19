package nl.cjib.motorcycles;

import nl.cjib.motorcycles.utils.InitMotorcycles;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import static nl.cjib.motorcycles.utils.Utils.getRandomGetal;

public class MainClass {
    private static Map<String, ArrayList<Motor>> motorcycles;
    private static boolean exitMenu= false;
    private static final Pattern oneDigitPattern = Pattern.compile("^\\d$");
    private static final int AANTAL_CHAR_BESCHIKBAAR_STANDAARD = 23;
    private static final int AANTAL_CHAR_BESCHIKBAAR_SHOW_MOTOR = 30;
    private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    private static String keyInput;
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String... args){
        int selection;
        motorcycles = new InitMotorcycles().getmotorCycles();

        while (!exitMenu) {
            printLijst(AANTAL_CHAR_BESCHIKBAAR_STANDAARD,"MENU Motorcycles");
            printLijst(AANTAL_CHAR_BESCHIKBAAR_STANDAARD,"Options:","1. Show motorcycles","2. Add motorcycle","3. Ga rijden","4. Delete motorcycle","4. Exit");
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
                        gaRijden();
                        waitAMoment();
                        break;
                    case 4:
                        System.out.println("Delete motorcycle");
                        // TODO verwijder een al geregisteerde motorcycle
                        break;
                    case 5:
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

    private static void gaRijden(){

        String motorMerk = "HONDA";
        String motorType = "CB 1100 RS";
//        motorcycles.keySet().stream()
//                .filter(m -> m.equals(motorMerk))
//                .map(m -> motorcycles.get(m))
//                .filter(m -> motorType.equals(m.iterator().next().getMotorType().getType()))
//                .map(m -> motorcycles.get(m)).forEach(m -> System.out.println(m.size()));

        Motor hondaM = motorcycles.get(motorMerk).get(0);
        List<Motor> hondaMotoren = motorcycles.get(motorMerk);
        Motor hondaMotor = hondaMotoren.get(getRandomGetal(0,2));

        System.out.println("We zijn op een motor gestapt van het merk " + hondaMotor.getMotorType().getBrand() + " en type " + hondaMotor.getMotorType().getType());
        hondaMotor.startEngine();
        hondaMotor.rijden();
        hondaMotor.fill(25);
    }

    private static void showMotorcycles(){
        printLijst(AANTAL_CHAR_BESCHIKBAAR_STANDAARD,"Show Motorcycles");
        System.out.println(" Aantal merken: " + motorcycles.values().size());
        System.out.println(" Merken: " + motorcycles.keySet().toString());
        System.out.println();

        for(String key : motorcycles.keySet()){
            ArrayList<Motor> brands = motorcycles.get(key);
            printLijst(AANTAL_CHAR_BESCHIKBAAR_STANDAARD,key+ " MOTOREN : " + brands.size());
            for(Motor motor : motorcycles.get(key).subList(0,brands.size())){
                printLijst(
                        AANTAL_CHAR_BESCHIKBAAR_SHOW_MOTOR
                        ,"           type:" + motor.getMotorType().getType()
                        ,"             pk:" + motor.getPk()
                        ,"cilinder inhoud:" + motor.getCilinderInhoud()
                        ,"          color:" + motor.getColour()
                        ,"           fuel:" + motor.getFuel()
                        ,"        totalKM:" + motor.getTotalKM()
                        ,"      onderhoud:" + motor.getOnderhoudsStatus().getOnderhoudstypeEnum().showOnderhoud()
                        ,"datum onderhoud:" + DATE_FORMAT.format(motor.getOnderhoudsStatus().getOnderhoudsDatum()));
               System.out.println();
            }
        }
    }

    private static void addMotorCycle() {
        printLijst(AANTAL_CHAR_BESCHIKBAAR_STANDAARD,"Add Motorcycle");
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
            System.out.println("merk bestaat niet!");
            // TODO toevoegen van een nieuw merk motorcycle
        }
    }

    private static void waitAMoment() {

        Scanner scannerWait = new Scanner(System.in);
        System.out.println("druk op een toets om verder te gaan ");
        scannerWait.nextLine();
    }

    private static void printLijst(int aantalBeschikbareKarakters,String... options){
        int aantalTekensKantlijnen = aantalBeschikbareKarakters + 5;
        String menuKantlijnen = new String(new char[aantalTekensKantlijnen]).replace("\0", "=");
        StringBuilder sb = new StringBuilder();
        sb.append(menuKantlijnen).append("\n");
        for(String menuItem : options) {
            sb.append("|   ").append(String.format("%-" + aantalBeschikbareKarakters + "s", menuItem)).append("|").append("\n");
        }
        sb.append(menuKantlijnen).append("\n");
        System.out.println(sb);
    }

    private static boolean merkStaatGeregisteerd(String invoerMerk){
        return motorcycles.keySet().contains(keyInput);
    }
}
