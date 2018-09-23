package nl.cjib.motorcycles.utils;

import nl.cjib.motorcycles.Motor;
import nl.cjib.motorcycles.MotorType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static nl.cjib.motorcycles.MainClass.getMotorcycles;
import static nl.cjib.motorcycles.MainClass.getSelectedMotorTypen;
import static nl.cjib.motorcycles.utils.NumbersUtil.getRandomGetal;

public class MotorUtil {
    public synchronized  static Motor getRandomMotorTypeFromMerk(String merk) {
        Motor m;
        Map<String, List<Motor>> motorcycles = getMotorcycles();
        for (String key : motorcycles.keySet()) {
            SelectRandomTypeFromMerk selectRandomTypeFromMerk = new SelectRandomTypeFromMerk(merk, motorcycles, key).invoke();
            if (selectRandomTypeFromMerk.is()) {
                 return motorcycles.get(key).get(selectRandomTypeFromMerk.getIndexRandomType());
            }
        }
        return null;
    }

    private static boolean isSelectedInExcluded(MotorType selected,List<MotorType> excluded){
        return excluded.contains(selected);
    }

    public static String getRandomMotorMerk() {
        Map<String, List<Motor>> motorcycles = getMotorcycles();
        List<String> merken = new ArrayList<>(motorcycles.keySet());
        return merken.get(getRandomGetal(0, merken.size() - 1));
    }

    private static class SelectRandomTypeFromMerk {
        private boolean myResult;
        private final String merk;
        private final Map<String, List<Motor>> motorcycles;
        private final String key;
        private int indexRandomType;

        SelectRandomTypeFromMerk(String merk, Map<String, List<Motor>> motorcycles, String key) {
            this.merk = merk;
            this.motorcycles = motorcycles;
            this.key = key;
        }

        boolean is() {
            return myResult;
        }

        int getIndexRandomType() {
            return indexRandomType;
        }

        SelectRandomTypeFromMerk invoke() {
            if (key.equals(merk)) {
                MotorType selectedMotorType;
                indexRandomType = 0;
                do{
                    indexRandomType = getRandomGetal(0, motorcycles.get(key).size() - 1);
                    selectedMotorType = motorcycles.get(key).get(indexRandomType).getMotorType();
                }while (isSelectedInExcluded(selectedMotorType,getSelectedMotorTypen()));
                myResult = true;
                return this;
            }
            myResult = false;
            return this;
        }
    }
}
