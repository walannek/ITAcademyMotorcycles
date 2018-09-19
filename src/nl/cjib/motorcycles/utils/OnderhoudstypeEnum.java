package nl.cjib.motorcycles.utils;

public enum OnderhoudstypeEnum {
    APK("Apk-keuring"),
    GROTE_BEURT("Grote beurt"),
    KLEINE_BEURT("Kleine beurt");

    String onderhoudsType;
    OnderhoudstypeEnum(String onderhoudsType) {
        this.onderhoudsType = onderhoudsType;
    }
    public String showOnderhoud() {
        return onderhoudsType;
    }
}
