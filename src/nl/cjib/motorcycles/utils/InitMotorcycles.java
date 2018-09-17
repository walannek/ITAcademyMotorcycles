package nl.cjib.motorcycles.utils;

import com.sun.istack.internal.NotNull;
import nl.cjib.motorcycles.Motor;
import nl.cjib.motorcycles.MotorType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class InitMotorcycles {
    /**
     * lijst met HONDA motoren met type definitie voor initieel vullen van de motoCycles ArrayList
     */
    private static final ArrayList<String> honda = new ArrayList<>(Arrays.asList("HONDA","X-ADV","CB 1000 R","CB 1100 RS","CB 250"));

    /**
     * lijst met BMW motoren met type definitie voor initieel vullen van de motoCycles ArrayList
     */
    private static final ArrayList<String> bmw = new ArrayList<>(Arrays.asList("BMW","C 600 SPORT","C 650 GT"));

    /**
     * Hierin komen alle merken motoren te staan met bijbehorende typen
     */
    private static HashMap<String, ArrayList<Motor>> motorCycles = new HashMap<>();

    static {
        getAllBrandsAndTypen();
    }

    /**
     * methode om de HashMap motorCycles te vullen met alle initieele merken met typen
     */
    private static void getAllBrandsAndTypen(){
        ArrayList<ArrayList<String>> initListOfMotorCycles = new ArrayList<>();
        ArrayList<Motor> motorList;
        addBrandsAndTypen(initListOfMotorCycles);

        for(ArrayList brand : initListOfMotorCycles){
            motorList = new ArrayList<>();
            for(int i =1;i< brand.size();i++){
                addMotorenToBrand(motorList, brand.get(0).toString(),brand.get(i).toString());
            }
            addBrandToMotorCycles(motorList);
        }
    }

    /**
     * tijdelijke ArrayList initListOfMotorCycles vullen met de voorgedefinieerde lijsten van ieder merk
     * @param initListOfMotorCycles de tijdelijke ArrayList initListOfMotorCycles
     */
    private static void addBrandsAndTypen(ArrayList<ArrayList<String>> initListOfMotorCycles) {
        initListOfMotorCycles.add(bmw);
        initListOfMotorCycles.add(honda);
    }

    /**
     * per merk(brand) en typen de motoren registeren in de ArrayList motorList
     * @param motorList de ArrayList die gevuld wordt met {@link Motor}en
     * @param brand het merk die van toepassing is
     * @param brandType het type die van toepssing is
     */
    public static void addMotorenToBrand(ArrayList<Motor> motorList, String brand, String brandType) {
        Motor motor = new Motor();
        MotorType motorType = new MotorType();
        motorType.setBrand(brand);
        motorType.setType(brandType);
        motor.setMotorType(motorType);
        Integer cilinderInhoud = getCilinderInhoudFromType(motorType.getType());
        if (cilinderInhoud != null) {
            motor.setCilinderInhoud(cilinderInhoud);
        }
        motor.setTotalKM(getRandomGetal(200000,0));
        motor.setOnderhoudsType(setRandomOnderhoudsType(getRandomGetal(3,1)));
        motorList.add(motor);
    }

    /**
     * de ArrayList motorList in de HashMap motorCycles plaatsen
     * iedere rij van motorCycles bevat: merk en een ArrayList met motoren van dat merk
     * @param motorList de samengestelde lijst motoren van een merk
     */
    private static void addBrandToMotorCycles(ArrayList<Motor> motorList){
        motorCycles.put(motorList.get(0).getMotorType().getBrand(),motorList);
    }

    /**
     * Hier de te registreren pk's uit het type proberen te halen
     * @param type het type waaruit de pk zal worden gehaald
     * @return de pk uit het type
     */
    private static Integer getCilinderInhoudFromType(@NotNull String type) {
        String cilinderInhoud= "";
        for (char ch : type.toCharArray()) {
            if (Character.isDigit(ch)) {
                cilinderInhoud = cilinderInhoud + ch;
            } else if (checkCilinderInhoud(cilinderInhoud)) {
                return Integer.parseInt(cilinderInhoud);
            } else cilinderInhoud = "";
        }
        if(checkCilinderInhoud(cilinderInhoud)) {
            return Integer.parseInt(cilinderInhoud);
        }
        return null;
    }

    /**
     * check of de pk een waarde bevat en deze groter is dan 49
     * @param pk de te checken pk
     * @return true als deze groter is dan 49 anders false
     */
    private static boolean checkCilinderInhoud(@NotNull String cilinderInhoud){
        return !cilinderInhoud.equals("") && Integer.parseInt(cilinderInhoud) > 49;
    }

    /**
     * haal een random getal waarbij geldt dat deze >= getal en <= tot
     * @param tot het random gekozen getal zal nooit groter zijn dan deze waarde
     * @param vanaf het random gekozen getal zal nooit kleiner zijn dan deze waarde
     * @return een random getal
     */
    private static int getRandomGetal(@NotNull int tot,@NotNull int vanaf){
        return new Random().nextInt(tot) + vanaf;
    }

    /**
     * geef een onderhoudstype a.d.h.v. het random gekozen onderhoudsgetal
     * @param onderhoudsGetal getal voor het random vullen van het onderhoudstype
     * @return een onderhoudsEnum op basis van een random gekozen onderhoudsgetal
     */
    private static OnderhoudstypeEnum setRandomOnderhoudsType(@NotNull int onderhoudsGetal){
        switch(onderhoudsGetal){
            case 1:
                return OnderhoudstypeEnum.APK;
            case 2:
                return OnderhoudstypeEnum.GROTE_BEURT;
            case 3:
                return OnderhoudstypeEnum.KLEINE_BEURT;
             default:
                 return null;
        }
    }

    public HashMap<String,ArrayList<Motor>> getmotorCycles() { return motorCycles; }
}