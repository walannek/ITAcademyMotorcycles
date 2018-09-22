package nl.cjib.motorcycles;

import nl.cjib.motorcycles.utils.InitMotorcycles;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import static nl.cjib.motorcycles.utils.MotorUtil.*;
import static nl.cjib.motorcycles.utils.MotorUtil.getRandomMotorMerk;
import static nl.cjib.motorcycles.utils.NumbersUtil.getRandomGetal;

public class MainClass {
    private static Map<String, List<Motor>> motorcycles;
    private static boolean exitMenu= false;
    private static final Pattern oneDigitPattern = Pattern.compile("^\\d$");
    private static final int AANTAL_CHAR_BESCHIKBAAR_STANDAARD = 23;
    private static final int AANTAL_CHAR_BESCHIKBAAR_SHOW_MOTOR = 30;
    private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    private static String keyInput;
    private static boolean hasWinner;
    private static String winnerName;
    private static String motorType;
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String... args){
        int selection;
        motorcycles = new InitMotorcycles().getmotorCycles();

        while (!exitMenu) {
            printLijst(AANTAL_CHAR_BESCHIKBAAR_STANDAARD
                    ,"MENU Motorcycles");
            printLijst(AANTAL_CHAR_BESCHIKBAAR_STANDAARD
                    ,"Options:"
                    ,"1. Show motorcycles"
                    ,"2. Add motorcycle"
                    ,"3. Ga rijden"
                    ,"4. Ga rijden(threading)"
                    ,"5. Delete motorcycle"
                    ,"6. Exit");
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
                        rijden();
                        waitAMoment();
                        break;
                    case 5:
                        System.out.println("Delete motorcycle nog niet geimplementeerd");
                        // TODO verwijder een al geregisteerde motorcycle
                        break;
                    case 6:
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

    private static void rijden(){
        setHasWinner(false);
        setMotorType("");
        new MyThread("Gerben");
        new MyThread("Rense");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }
        System.out.println("Main thread exiting.");
        System.out.println("WINNER=" + getWinnerName());
    }

    static void gaRijden(){
        Motor zoekMotor;
        if (getMotorType().equals(""))
            zoekMotor = getRandomMotorTypeFromMerk(getRandomMotorMerk());
        else zoekMotor = getRandomMotorTypeFromMerk(getRandomMotorMerk(), getMotorType());

        assert zoekMotor != null;
        zoekMotor.setBrandstofVerbruik(1,getRandomGetal(1,10));
        System.out.println(zoekMotor.getThreadName() + " is op op een motor gestapt van het merk " + zoekMotor.getMotorType().getBrand()
                + " en type " + zoekMotor.getMotorType().getType());
        zoekMotor.afstand(getRandomGetal(100,1000));
        zoekMotor.startEngine();
        System.out.println(zoekMotor.getThreadName()+ zoekMotor.getMotorAndType() + " gaat rijden");
        zoekMotor.rijden();
    }

    private static void showMotorcycles(){
        printLijst(AANTAL_CHAR_BESCHIKBAAR_STANDAARD,"Show Motorcycles");
        System.out.println(" Aantal merken: " + motorcycles.values().size());
        System.out.println(" Merken: " + motorcycles.keySet().toString());
        System.out.println();

        for(String key : motorcycles.keySet()){
            List<Motor> brands = motorcycles.get(key);
            printLijst(AANTAL_CHAR_BESCHIKBAAR_STANDAARD,key+ " MOTOREN : " + brands.size());
            for(Motor motor : motorcycles.get(key).subList(0,brands.size())){
                printLijst(
                        AANTAL_CHAR_BESCHIKBAAR_SHOW_MOTOR
                        ,"           type:" + motor.getMotorType().getType()
                        ,"             pk:" + motor.getPk()
                        ,"cilinder inhoud:" + motor.getCilinderInhoud()
                        ,"          color:" + motor.getColour()
                        ,"           fuel:" + String.format("%.2f",motor.getFuel())
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

    private static boolean merkStaatGeregisteerd(String invoerMerk){ return motorcycles.keySet().contains(keyInput); }
    public static Map<String, List<Motor>> getMotorcycles() { return motorcycles; }
    private static String getWinnerName() { return winnerName; }
    static void setWinnerName(String winName) { winnerName = winName; }
    static boolean isHasWinner() { return hasWinner; }
    static void setHasWinner(boolean hasWinner) { MainClass.hasWinner = hasWinner; }
    private static String getMotorType() { return motorType; }
    private static void setMotorType(String motorType) { MainClass.motorType = motorType; }
}
