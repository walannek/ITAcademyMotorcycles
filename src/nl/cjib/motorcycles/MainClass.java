package nl.cjib.motorcycles;

import nl.cjib.motorcycles.utils.OnderhoudstypeEnum;

public class MainClass {

    public static void main(String... args){
        Motor motor = new Motor();
        motor.setOnderhoudsType(OnderhoudstypeEnum.GROTE_BEURT);

        System.out.println("motor heeft type " + motor.getOnderhoudsType());
    }
}
