package nl.cjib.motorcycles.utils;

import nl.cjib.motorcycles.Motor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static nl.cjib.motorcycles.MainClass.getMotorcycles;
import static nl.cjib.motorcycles.utils.NumbersUtil.getRandomGetal;

public class MotorUtil {
    public static Motor getRandomMotorTypeFromMerk(String merk,String excludedType){
        Map<String, List<Motor>> motorcycles = getMotorcycles();
        for(String key : motorcycles.keySet()){
            if (key.equals(merk)) {
                int indexRandomType = getRandomGetal(0,motorcycles.get(key).size()-1);
                int sizeTypen = motorcycles.get(key).size();
                if(excludedType.equals(motorcycles.get(key).get(indexRandomType).toString())){
                    if(indexRandomType+1<sizeTypen){
                        return motorcycles.get(key).get(indexRandomType+1);
                    }else if(indexRandomType+1==sizeTypen){
                        return motorcycles.get(key).get(indexRandomType-1);
                    }else{
                        return motorcycles.get(key).get(indexRandomType);
                    }
                }else{
                    return motorcycles.get(key).get(indexRandomType);
                }
            }
        }
        return null;
    }
    public static Motor getRandomMotorTypeFromMerk(String merk){
        Map<String, List<Motor>> motorcycles = getMotorcycles();
        for(String key : motorcycles.keySet()){
            if (key.equals(merk)) {
                return motorcycles.get(key).get(getRandomGetal(0,motorcycles.get(key).size()-1));
            }
        }
        return null;
    }

    public static String getRandomMotorMerk(){
        Map<String, List<Motor>> motorcycles = getMotorcycles();
        List<String> merken = new ArrayList<>(motorcycles.keySet());
        return merken.get(getRandomGetal(0,merken.size()-1));
    }
}
