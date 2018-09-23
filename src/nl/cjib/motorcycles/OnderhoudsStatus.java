package nl.cjib.motorcycles;

import nl.cjib.motorcycles.Enums.OnderhoudstypeEnum;

import java.util.Date;

public class OnderhoudsStatus {

    public OnderhoudsStatus(OnderhoudstypeEnum onderhoudstypeEnum, Date onderhoudsDatum) {
        this.onderhoudsDatum = onderhoudsDatum;
        this.onderhoudstypeEnum = onderhoudstypeEnum;
    }

    private final OnderhoudstypeEnum onderhoudstypeEnum;
    private final Date onderhoudsDatum;

    OnderhoudstypeEnum getOnderhoudstypeEnum() {
        return onderhoudstypeEnum;
    }

    Date getOnderhoudsDatum() {
        return onderhoudsDatum;
    }
}
