package nl.cjib.motorcycles.utils;

import java.util.concurrent.ThreadLocalRandom;

public class Utils {
    /**
     * haal een random getal waarbij geldt dat deze >= min en <= max
     * @param min het random gekozen getal zal nooit kleiner zijn dan deze waarde
     * @param max het random gekozen getal zal nooit groter zijn dan deze waarde
     * @return een random getal
     */
    public static int getRandomGetal(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
