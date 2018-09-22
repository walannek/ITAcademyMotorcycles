package nl.cjib.motorcycles;

import static nl.cjib.motorcycles.MainClass.isHasWinner;
import static nl.cjib.motorcycles.MainClass.setHasWinner;
import static nl.cjib.motorcycles.MainClass.setWinnerName;
import static nl.cjib.motorcycles.utils.NumbersUtil.getRandomGetal;

public class Motor extends Vervoermiddel implements Fillable {
    private static final int MIN_TANK_VOORRAAD = 2;
    private String colour;
    private int pk;
    private int cilinderInhoud;
    private int totalKM;
    private double fuel;
    private MotorType motorType;
    private boolean zijspan;
    private OnderhoudsStatus onderhoudsStatus;
    private boolean engineIsOnOff;
    private int afstand;
    private double brandstofVerbruik;
    private int geredenKilomers;

    @Override
    void rijden() {
        int randomAfstand;
        if(getAfstand()<=0){
            System.out.println("\n" + getThreadName()
                    + "IS GEARRIVEERD OP MOTOR "
                    + getMotorType().getBrand()
                    + "-" + getMotorType().getType()
                    + " en heeft nog "
                    + String.format("%.2f",getFuel())
                    + " benzine in tank"
                    + "\n");
            System.out.println(getMotorAndType() + " heeft " + geredenKilomers + " kilometer afgelegd");
            stopEngine();
            if(!isHasWinner()){
                setWinnerName(Thread.currentThread().getName());
                setHasWinner(true);
                System.out.println("WINNER IS BEPAALD!");
            }
            return;
        }
        else {
            String fuelOutput;
            fuelOutput = String.format("%.2f",getFuel());
            System.out.println(getCheckBenzine(fuelOutput));
            if (fuel < MIN_TANK_VOORRAAD) {
                fill(getRandomGetal(10, 45));
            } else {
                System.out.println(getBenzineKleinerDanMinimumVoorraad());
                System.out.println(getThreadName()+ getMotorAndType() + " heeft inmiddels " + geredenKilomers + " kilometer afgelegd");
                System.out.println(getThreadName()+ "   bepaal volgende afstand voor motor: " + getMotorType().getBrand() + "-" + getMotorType().getType());
                randomAfstand = getRandomAfstand();
                geredenKilomers+=randomAfstand;
                setAfstand(getAfstand()-randomAfstand);
                setFuel(getFuel() - (getBrandstofVerbruik()* randomAfstand));
                System.out.println(getThreadName()+ getMotorAndType()+" gaat verder...");
            }
        }
        rijden();
    }

    @Override
    void afstand(int afstand) {
        setAfstand(afstand);
        System.out.println(getThreadName() + "motor "  + getMotorType().getBrand() + "-" + getMotorType().getType() + " met benzine verbruik van " + String.format("%.2f",getBrandstofVerbruik()) + " benzine op 1 kilometer, gaat " + afstand + " kilometer rijden met "
                + getFuel() + " liter benzine in de tank...");
    }

    @Override
    public void fill(int liters) {
        System.out.println(getTanken());
        stopEngine();
        System.out.println(getAfgelegdeKilometers());
        setFuel(getFuel() + liters);
        System.out.println(getLitersBenzineGetankt(liters));
        System.out.println(getLitersBenzineInBenzinetank());
        startEngine();
        System.out.println(getGaatWeerRijden());
    }

    String getThreadName() {
        return "(" + Thread.currentThread().getName() + ")";
    }

    void startEngine() {
        setEngineIsOnOff(true);
        System.out.println(getEngineGestart());
    }
    private void stopEngine() {
        setEngineIsOnOff(false);
        System.out.println(getEngineGestopt());
    }

    private int getRandomAfstand() {
        int randomAfstand;
        if((getBrandstofVerbruik()* getAfstand())>getFuel()){
            double maxAfstand = (getFuel()/getBrandstofVerbruik());
            if(maxAfstand <=0) return 0;
            randomAfstand = getRandomGetal(0, (int)maxAfstand);
        }else{
            if(getAfstand() ==1) {
                return 1;
            }
            else if(getAfstand() <=0) {
                return 0;
            }else{
                randomAfstand = getRandomGetal(1, getAfstand());
            }
        }

        System.out.println(getThreadName() + "   random afstand bepaald voor motor " + getMotorType().getBrand() + "-" + getMotorType().getType() + " : " + randomAfstand + " kilometers");
        return randomAfstand;
    }

    String getMotorAndType() {return "   motor " + getMotorType().getBrand() + "-" + getMotorType().getType();}
    private String getEngineGestopt(){ return getThreadName() + "   engine " + getMotorType().getBrand() + "-" + getMotorType().getType() + " is gestopt...";}
    private String getEngineGestart(){ return getThreadName() + "   engine " + getMotorType().getBrand() + "-" + getMotorType().getType() + " is gestart...";}
    private String getAfgelegdeKilometers(){ return getThreadName()+ getMotorAndType() + " heeft inmiddels " + geredenKilomers + " kilometer afgelegd";}
    private String getLitersBenzineGetankt(int liters){ return getThreadName()+ getMotorAndType() + " heeft " + liters + " liter benzine getankt..";}
    private String getLitersBenzineInBenzinetank(){ return getThreadName()+ getMotorAndType() + " heeft nu een totaal van " + String.format("%.2f",getFuel()) + " liter in de benzinetank...";}
    private String getGaatWeerRijden(){ return getThreadName()+ getMotorAndType() + " gaat weer rijden";}
    private String getCheckBenzine(String fuelOutput){ return getThreadName()+ "CHECK BENZINE STATUS MOTOR: " + getMotorType().getBrand() + "-" + getMotorType().getType() +" moet nog " + getAfstand() + " kilometer rijden en heeft nog " + fuelOutput + " liter benzine in de tank...";}
    private String getBenzineKleinerDanMinimumVoorraad(){ return getThreadName()
            + ((fuel < 10) ? getMotorAndType()
            + " heeft nog voldoende benzine om door te rijden, maar kijk uit naar benzinestation..." : getMotorAndType()
            + " heeft nog genoeg benzine om door te rijden...");}
    private String getTanken() { return getThreadName()
                    + "TANKEN:motor " + getMotorType().getBrand()
                    + "-" + getMotorType().getType()
                    + " gaat tanken, omdat er nog maar "
                    + String.format("%.2f", getFuel())
                    + " liter benzine in tank aanwezig is...";
    }

    String getColour() { return this.colour; }
    public void setColour(String colour) { this.colour = colour; }
    int getPk() { return this.pk; }
    public void setPk(int pk) { this.pk = pk; }
    int getTotalKM() { return totalKM; }
    public void setTotalKM(int totalKM) { this.totalKM = totalKM; }
    double getFuel() { return fuel; }
    public void setFuel(double fuel) { this.fuel = fuel; }
    public MotorType getMotorType() { return motorType; }
    public void setMotorType(MotorType motorType) { this.motorType = motorType; }
    int getCilinderInhoud() { return cilinderInhoud; }
    public void setCilinderInhoud(int cilinderInhoud) { this.cilinderInhoud = cilinderInhoud; }
    public boolean isZijspan() { return zijspan; }
    public void setZijspan(boolean zijspan) { this.zijspan = zijspan; }
    OnderhoudsStatus getOnderhoudsStatus() { return onderhoudsStatus; }
    public void setOnderhoudsStatus(OnderhoudsStatus onderhoudsStatus) { this.onderhoudsStatus = onderhoudsStatus; }
    private boolean getEngineIsOnOff() { return engineIsOnOff; }
    private void setEngineIsOnOff(boolean engineIsOnOff) { this.engineIsOnOff = engineIsOnOff;}
    private int getAfstand() { return afstand; }
    private void setAfstand(int afstand) { this.afstand = afstand; }
    private double getBrandstofVerbruik() { return brandstofVerbruik; }
    void setBrandstofVerbruik(double liters, double kilometers) { this.brandstofVerbruik = liters/kilometers; }
}