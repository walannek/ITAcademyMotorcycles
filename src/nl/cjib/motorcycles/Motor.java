package nl.cjib.motorcycles;

import java.util.Arrays;

import static nl.cjib.motorcycles.MainClass.*;
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
    synchronized void rijden() throws InterruptedException{
        int randomAfstand;
        if (getAfstand() <= 0) {
            setTekstGearriveerdEnKilometersAfgelegd();
            stopEngine();
            if (!isHasWinner()) {
                setHasWinner(true);
                System.out.println("WINNAAR IS BEPAALD!");
            }
            getWinnerNames().add((Thread.currentThread().getName()));
            return;
        } else {
            String fuelOutput;
            fuelOutput = String.format("%.2f", getFuel());
            System.out.println(getTekstCheckBenzine(fuelOutput));
            if (fuel < MIN_TANK_VOORRAAD) {
                gaTanken();
                fill(getRandomGetal(10, 45));
            } else {
                System.out.println(getTekstBenzineKleinerDanMinimumVoorraad());
                System.out.println(getThreadName() + getTekstMotorAndType() + " heeft inmiddels " + geredenKilomers + " kilometer afgelegd");
                System.out.println(getThreadName() + "   bepaal volgende afstand voor motor: " + getMotorType().getBrand() + "-" + getMotorType().getType());
                randomAfstand = getRandomAfstand();
                geredenKilomers += randomAfstand;
                setAfstand(getAfstand() - randomAfstand);
                setFuel(getFuel() - (getBrandstofVerbruik() * randomAfstand));
                System.out.println(getThreadName() + getTekstMotorAndType() + " gaat verder...");
            }
        }
        rijden();
    }

    private synchronized void gaTanken() throws InterruptedException {
        try{
            while (fuel < MIN_TANK_VOORRAAD) {
                System.out.println(getThreadName() + getTekstMotorAndType() + " zoekt tankstation");
                Thread.currentThread().wait();

            }
        }
        catch (IllegalMonitorStateException e){ }
    }

    @Override
    void afstand(int afstand) {
        setAfstand(afstand);
        System.out.println(getTekstAfstand());
    }

    @Override
    public synchronized void fill(int liters) {
        try {
            System.out.println(getTekstTanken());
            stopEngine();
            setFuel(getFuel() + liters);
            System.out.println(getTekstLitersBenzineGetankt(liters));
            System.out.println(getTekstLitersBenzineInBenzinetank());
            startEngine();
            System.out.println(getTekstGaatWeerRijden());
        }
        catch (Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
        }finally {
            notify();
        }
    }

    String getThreadName() {
        if("main".equals(Thread.currentThread().getName())){
            return "(Gerben)";
        }
        return "(" + Thread.currentThread().getName() + ")";
    }

    void startEngine() {
        setEngineIsOnOff(true);
        System.out.println(getTekstEngineGestart());
    }

    private void stopEngine() {
        setEngineIsOnOff(false);
        System.out.println(getTekstEngineGestopt());
    }

    private int getRandomAfstand() {
        int randomAfstand;
        if ((getBrandstofVerbruik() * getAfstand()) > getFuel()) {
            double maxAfstand = (getFuel() / getBrandstofVerbruik());
            if (maxAfstand <= 0) return 0;
            randomAfstand = getRandomGetal(1, (int) maxAfstand);
        } else {
            if (getAfstand() == 1) {
                return 1;
            } else if (getAfstand() <= 0) {
                return 0;
            } else {
                randomAfstand = getRandomGetal(1, getAfstand());
            }
        }
        System.out.println(getTekstAfstandBepaald(randomAfstand));
        return randomAfstand;
    }

    private String getTekstAfstand() {
        return getThreadName() +
                "motor " +
                getMotorType().getBrand() +
                "-" + getMotorType().getType() +
                " met benzine verbruik van " +
                String.format("%.2f", getBrandstofVerbruik()) +
                " benzine op 1 kilometer, gaat " +
                afstand +
                " kilometer rijden met " +
                getFuel() +
                " liter benzine in de tank...";
    }

    String getTekstMotorAndType() {
        return "   motor " +
                getMotorType().getBrand() +
                "-" +
                getMotorType().getType();
    }

    private String getTekstAfstandBepaald(int randomAfstand) {
        return getThreadName() +
                "   random afstand bepaald voor motor " +
                getMotorType().getBrand() +
                "-" +
                getMotorType().getType() +
                " : " +
                randomAfstand +
                " kilometers";
    }

    private String getTekstEngineGestopt() {
        return getThreadName() +
                "   engine " +
                getMotorType().getBrand() +
                "-" +
                getMotorType().getType() +
                " is gestopt en heeft inmiddels " +
                geredenKilomers +
                " kilometer afgelegd";
    }

    private String getTekstEngineGestart() {
        return getThreadName() +
                "   engine " +
                getMotorType().getBrand() +
                "-" +
                getMotorType().getType() +
                " is gestart...";
    }

    private String getTekstAfgelegdeKilometers() {
        return getThreadName() +
                getTekstMotorAndType() +
                " heeft inmiddels " +
                geredenKilomers +
                " kilometer afgelegd";
    }

    private String getTekstLitersBenzineGetankt(int liters) {
        return getThreadName() +
                getTekstMotorAndType() +
                " heeft " +
                liters +
                " liter benzine getankt..";
    }

    private String getTekstLitersBenzineInBenzinetank() {
        return getThreadName() +
                getTekstMotorAndType() +
                " heeft nu een totaal van " +
                String.format("%.2f", getFuel()) +
                " liter in de benzinetank...";
    }

    private String getTekstGaatWeerRijden() {
        return getThreadName() +
                getTekstMotorAndType() +
                " gaat weer rijden";
    }

    private String getTekstCheckBenzine(String fuelOutput) {
        return getThreadName() + "CHECK BENZINE STATUS MOTOR: " +
                getMotorType().getBrand() +
                "-" + getMotorType().getType() +
                " moet nog " + getAfstand() +
                " kilometer rijden en heeft nog " +
                fuelOutput +
                " liter benzine in de tank...";
    }

    private String getTekstBenzineKleinerDanMinimumVoorraad() {
        return getThreadName()
                + ((fuel < 10) ? getTekstMotorAndType()
                + " heeft nog voldoende benzine om door te rijden, maar kijk uit naar benzinestation..." : getTekstMotorAndType()
                + " heeft nog genoeg benzine om door te rijden...");
    }

    private String getTekstTanken() {
        return getThreadName() +
                "TANKSTATION GEVONDEN:motor " +
                getMotorType().getBrand() +
                "-" + getMotorType().getType() +
                " gaat tanken, omdat er nog maar " +
                String.format("%.2f", getFuel()) +
                " liter benzine in tank aanwezig is...";
    }

    private void setTekstGearriveerdEnKilometersAfgelegd() {
        System.out.println("\n" + getThreadName() +
                "IS GEARRIVEERD OP MOTOR " +
                getMotorType().getBrand() +
                "-" + getMotorType().getType() +
                " en heeft nog " +
                String.format("%.2f", getFuel()) +
                " liter benzine in tank" +
                "\n");
        System.out.println(getThreadName() +getTekstMotorAndType() + " heeft " + geredenKilomers + " kilometer afgelegd");
    }

    String getColour() {
        return this.colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    int getPk() {
        return this.pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    int getTotalKM() {
        return totalKM;
    }

    public void setTotalKM(int totalKM) {
        this.totalKM = totalKM;
    }

    double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public MotorType getMotorType() {
        return motorType;
    }

    public void setMotorType(MotorType motorType) {
        this.motorType = motorType;
    }

    int getCilinderInhoud() {
        return cilinderInhoud;
    }

    public void setCilinderInhoud(int cilinderInhoud) {
        this.cilinderInhoud = cilinderInhoud;
    }

    public boolean isZijspan() {
        return zijspan;
    }

    public void setZijspan(boolean zijspan) {
        this.zijspan = zijspan;
    }

    OnderhoudsStatus getOnderhoudsStatus() {
        return onderhoudsStatus;
    }

    public void setOnderhoudsStatus(OnderhoudsStatus onderhoudsStatus) {
        this.onderhoudsStatus = onderhoudsStatus;
    }

    private boolean getEngineIsOnOff() {
        return engineIsOnOff;
    }

    private void setEngineIsOnOff(boolean engineIsOnOff) {
        this.engineIsOnOff = engineIsOnOff;
    }

    private int getAfstand() {
        return afstand;
    }

    private void setAfstand(int afstand) {
        this.afstand = afstand;
    }

    private double getBrandstofVerbruik() {
        return brandstofVerbruik;
    }

    void setBrandstofVerbruik(double kilometers) {
        this.brandstofVerbruik = (double) 1 / kilometers;
    }
}