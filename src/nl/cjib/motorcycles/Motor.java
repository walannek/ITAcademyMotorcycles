package nl.cjib.motorcycles;

public class Motor extends Vervoermiddel implements Fillable {

    private String colour;
    private int pk;
    private int cilinderInhoud;
    private int totalKM;
    private int fuel;
    private MotorType motorType;
    private boolean zijspan;
    private OnderhoudsStatus onderhoudsStatus;
    private boolean engineIsOnOff;

    String getColour() {
        return this.colour;
    }

    public void setColour (String colour) {
        this.colour = colour;
    }
    int getPk() {
        return this.pk;
    }
    public void setPk (int pk) { this.pk = pk; }
    int getTotalKM() {
        return totalKM;
    }
    public void setTotalKM(int totalKM) {
        this.totalKM = totalKM;
    }
    int getFuel() {
        return fuel;
    }
    public void setFuel(int fuel) {
        this.fuel = fuel;
    }
    public MotorType getMotorType() { return motorType; }
    public void setMotorType(MotorType motorType) { this.motorType = motorType; }
    int getCilinderInhoud() { return cilinderInhoud; }
    public void setCilinderInhoud(int cilinderInhoud) { this.cilinderInhoud = cilinderInhoud; }
    public boolean isZijspan() { return zijspan; }
    public void setZijspan(boolean zijspan) { this.zijspan = zijspan; }
    OnderhoudsStatus getOnderhoudsStatus() { return onderhoudsStatus; }
    public void setOnderhoudsStatus(OnderhoudsStatus onderhoudsStatus) { this.onderhoudsStatus = onderhoudsStatus; }
    private boolean getEngineIsOnOff() { return engineIsOnOff; }
    private void setEngineIsOnOff(boolean engineIsOnOff) { this.engineIsOnOff = engineIsOnOff; }

    @Override
    void rijden() {
         System.out.println("motor gaat rijden");
    }

    void startEngine() {
        setEngineIsOnOff(!getEngineIsOnOff());
        System.out.println(Boolean.TRUE.equals(getEngineIsOnOff()) ? "Engine is gestart... " : "Engine is gestopt... ");
    }

    @Override
    public void fill(int liters) {
        System.out.println("motor gaat tanken");
        System.out.println("motor heeft nog " + getFuel() + " benzine");
        setFuel(getFuel()+liters);
        System.out.println("auto heeft " + liters + " getankt");
        System.out.println("auto heeft nu " + getFuel() + " in de tank");
    }
}