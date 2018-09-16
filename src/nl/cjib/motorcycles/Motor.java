package nl.cjib.motorcycles;

import nl.cjib.motorcycles.utils.OnderhoudstypeEnum;

public class Motor extends Motorvoertuig {

    private String colour;
    private int pk;
    private int totalKM;
    private String fuel;
    private MotorType motorType;
    private String onderhoudsType;

    String getColour() {
        return this.colour;
    }
    public void setColour (String colour) {
        this.colour = colour;
    }
    int getPk() {
        return this.pk;
    }
    public void setPk (int pk) {
        this.pk = pk;
    }
    int getTotalKM() {
        return totalKM;
    }
    public void setTotalKM(int totalKM) {
        this.totalKM = totalKM;
    }
    String getFuel() {
        return fuel;
    }
    public void setFuel(String fuel) {
        this.fuel = fuel;
    }
    String getOnderhoudsType() { return onderhoudsType;}
    public void setOnderhoudsType(OnderhoudstypeEnum onderhoudstypeEnum) { this.onderhoudsType = onderhoudstypeEnum.name(); }
    public MotorType getMotorType() { return motorType; }
    public void setMotorType(MotorType motorType) { this.motorType = motorType; }
}