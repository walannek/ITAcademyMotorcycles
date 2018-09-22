package nl.cjib.motorcycles;

public class Auto extends Vervoermiddel{

    private String colour;
    private int pk;
    private int cilinderInhoud;
    private int totalKM;
    private String fuel;
    private MotorType motorType;
    private int aantalDeuren;

    public String getColour() {
        return colour;
    }
    public void setColour(String colour) {
        this.colour = colour;
    }
    public int getPk() {
        return pk;
    }
    public void setPk(int pk) {
        this.pk = pk;
    }
    public int getCilinderInhoud() {
        return cilinderInhoud;
    }
    public void setCilinderInhoud(int cilinderInhoud) {
        this.cilinderInhoud = cilinderInhoud;
    }
    public int getTotalKM() {
        return totalKM;
    }
    public void setTotalKM(int totalKM) {
        this.totalKM = totalKM;
    }
    public String getFuel() {
        return fuel;
    }
    public void setFuel(String fuel) {
        this.fuel = fuel;
    }
    public MotorType getMotorType() {
        return motorType;
    }
    public void setMotorType(MotorType motorType) { this.motorType = motorType; }
    public int getAantalDeuren() {
        return aantalDeuren;
    }
    public void setAantalDeuren(int aantalDeuren) {
        this.aantalDeuren = aantalDeuren;
    }


    @Override
    public void rijden() {
            System.out.println("auto gaat rijden");
    }

    @Override
    void afstand(int afstand) {

    }
}
