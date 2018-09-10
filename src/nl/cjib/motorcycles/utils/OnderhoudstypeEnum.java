package nl.cjib.motorcycles.utils;

public enum OnderhoudstypeEnum {
    APK("APK"),
    GROOT_ONDERHOUD("GROOT ONDERHOUD"),
    KLEIN_ONDERHOUD("KLEIN ONDERHOUD");

    OnderhoudstypeEnum(String onderhoudsType) {
        this.onderhoudsType = onderhoudsType;
    }

    private String onderhoudsType;

    public String getOnderhoudsType() {
        return onderhoudsType;
    }
}
