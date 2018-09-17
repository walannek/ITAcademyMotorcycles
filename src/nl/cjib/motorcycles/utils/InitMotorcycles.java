package nl.cjib.motorcycles.utils;

import com.sun.istack.internal.NotNull;
import nl.cjib.motorcycles.Motor;
import nl.cjib.motorcycles.MotorType;

import java.util.*;

public class InitMotorcycles {

    /**
     * lijst met HONDA motoren met type definitie voor initieel vullen van de motoCycles ArrayList
     */
    private static final ArrayList<String> honda = new ArrayList<>(Arrays.asList("HONDA","CB 1000 R","CB 1100 RS","CB 250"));

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
        if (getCilinderInhoudFromType(motorType.getType()).isPresent() ) motor.setCilinderInhoud(getCilinderInhoudFromType(motorType.getType()).get());
        motor.setTotalKM(getRandomGetal(200000,0));
        if(setRandomOnderhoudsType(getRandomGetal(3,1)).isPresent() ) motor.setOnderhoudsType(setRandomOnderhoudsType(getRandomGetal(3,1)).get());
        motorList.add(motor);
    }

    /**
     * de ArrayList motorList in de HashMap motorCycles plaatsen
     * iedere rij van motorCycles bevat: merk en een bijbehorende ArrayList met motoren van type {@link Motor}
     * @param motorList de samengestelde lijst motoren van een merk
     */
    private static void addBrandToMotorCycles(ArrayList<Motor> motorList){
        motorCycles.put(motorList.get(0).getMotorType().getBrand(),motorList);
    }

    /**
     * Hier de te registreren cilinderinhoud uit het type proberen te halen
     * @param type het type waaruit de pk zal worden gehaald
     * @return de cilinderinhoud uit het type
     */
    private static Optional<Integer> getCilinderInhoudFromType(@NotNull String type) {
        String cilinderInhoud= "";
        for (char ch : type.toCharArray()) {
            if (Character.isDigit(ch)) {
                cilinderInhoud = cilinderInhoud + ch;
            } else if (checkCilinderInhoud(cilinderInhoud)) {
                return Optional.of(Integer.parseInt(cilinderInhoud));
            } else cilinderInhoud = "";
        }
        if(checkCilinderInhoud(cilinderInhoud)) {
            return Optional.of(Integer.parseInt(cilinderInhoud));
        }
        return Optional.empty();
    }

    /**
     * check of de pk een waarde bevat en deze groter is dan 49
     * @param cilinderInhoud de te checken cilinderinhoud
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
    private static int getRandomGetal(@NotNull int tot,@NotNull int vanaf){ return new Random().nextInt(tot) + vanaf; }

    /**
     * geef een onderhoudstype a.d.h.v. het random gekozen onderhoudsgetal
     * @param onderhoudsGetal getal voor het random vullen van het onderhoudstype
     * @return een onderhoudsEnum op basis van een random gekozen onderhoudsgetal
     */
    private static Optional<OnderhoudstypeEnum> setRandomOnderhoudsType(@NotNull int onderhoudsGetal){
        switch(onderhoudsGetal){
            case 1:
                return Optional.of(OnderhoudstypeEnum.APK);
            case 2:
                return Optional.of(OnderhoudstypeEnum.GROTE_BEURT);
            case 3:
                return Optional.of(OnderhoudstypeEnum.KLEINE_BEURT);
             default:
                 return Optional.empty();
        }
    }

    public HashMap<String,ArrayList<Motor>> getmotorCycles() { return motorCycles; }
}