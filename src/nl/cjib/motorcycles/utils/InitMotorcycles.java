package nl.cjib.motorcycles.utils;

import com.sun.istack.internal.NotNull;
import nl.cjib.motorcycles.Enums.OnderhoudstypeEnum;
import nl.cjib.motorcycles.Motor;
import nl.cjib.motorcycles.MotorType;
import nl.cjib.motorcycles.OnderhoudsStatus;

import java.util.*;

import static nl.cjib.motorcycles.utils.NumbersUtil.getRandomGetal;

public class InitMotorcycles {
    /**
     * lijst met HONDA motoren met type definitie voor initieel vullen van de motoCycles ArrayList
     */
    private static final List<String> honda = new ArrayList<>(Arrays.asList("HONDA","CB 1000 R","CB 1100 RS","CB 250","CB 1100 SF X-11","CB 1100","CB 300 R","CB 300 F","CB 450"));

    /**
     * lijst met BMW motoren met type definitie voor initieel vullen van de motoCycles ArrayList
     */
    private static final List<String> bmw = new ArrayList<>(Arrays.asList("BMW","C 600 SPORT","C 650 GT","C 650 SPORT","C EVOLUTION","F 650 CS SCARVER","F 650 GS","F 650"));

    /**
     * Hierin komen alle merken motoren te staan met bijbehorende typen
     */
    private static HashMap<String, List<Motor>> motorCycles = new HashMap<>();

    static {
        getAllBrandsAndTypen();
    }

    /**
     * methode om de HashMap motorCycles te vullen met alle initieele merken met typen
     */
    private static void getAllBrandsAndTypen(){
        List<List<String>> initListOfMotorCycles = new ArrayList<>();
        List<Motor> motorList;
        addBrandsAndTypen(initListOfMotorCycles);

        for(List brand : initListOfMotorCycles){
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
    private static void addBrandsAndTypen(List<List<String>> initListOfMotorCycles) {
        initListOfMotorCycles.add(bmw);
        initListOfMotorCycles.add(honda);
    }

    /**
     * per merk(brand) en typen de motoren registeren in de ArrayList motorList
     * @param motorList de ArrayList die gevuld wordt met {@link Motor}en
     * @param brand het merk die van toepassing is
     * @param brandType het type die van toepssing is
     */
    public static void addMotorenToBrand(List<Motor> motorList, String brand, String brandType) {
        Motor motor = new Motor();
        MotorType motorType = new MotorType();
        motorType.setBrand(brand);
        motorType.setType(brandType);
        motor.setMotorType(motorType);
        if (getCilinderInhoudFromType(motorType.getType()).isPresent() )
            motor.setCilinderInhoud(getCilinderInhoudFromType(motorType.getType()).get());
        motor.setColour("geen kleur");
        motor.setPk(getRandomGetal(80,125));
        motor.setFuel(getRandomGetal(1,12));
        motor.setTotalKM(getRandomGetal(0,200000));
        Calendar onderhoudsDatum = Calendar.getInstance();
        onderhoudsDatum.set(getRandomGetal(2016,2019),getRandomGetal(1,12),getRandomGetal(1,30));
        if(setRandomOnderhoudsType(getRandomGetal(1,3)).isPresent()) motor.setOnderhoudsStatus(new OnderhoudsStatus(setRandomOnderhoudsType(getRandomGetal(1, 3)).get(), onderhoudsDatum.getTime()));
        motorList.add(motor);
    }

    /**
     * de ArrayList motorList in de HashMap motorCycles plaatsen
     * iedere rij van motorCycles bevat: merk en een bijbehorende ArrayList met motoren van type {@link Motor}
     * @param motorList de samengestelde lijst motoren van een merk
     */
    private static void addBrandToMotorCycles(List<Motor> motorList){
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
     * geef een onderhoudstype a.d.h.v. het random gekozen onderhoudsgetal
     * @param onderhoudsgetal getal voor het random vullen van het onderhoudstype
     * @return een onderhoudsEnum op basis van een random gekozen onderhoudsgetal
     */
    private static Optional<OnderhoudstypeEnum> setRandomOnderhoudsType(@NotNull int onderhoudsgetal){
        switch(onderhoudsgetal){
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
    public HashMap<String,List<Motor>> getmotorCycles() { return motorCycles; }
}