package nl.cjib.motorcycles;

public class Motorvoertuig {
    private int aantalWielen;
    private int tellerStand;
    private String kenteken;
    private String chassisnummer;

    public int getAantalWielen() { return aantalWielen; }
    public void setAantalWielen(int aantalWielen) { this.aantalWielen = aantalWielen; }
    public String getKenteken() { return kenteken; }
    public void setKenteken(String kenteken) { this.kenteken = kenteken; }
    public String getChassisnummer() { return chassisnummer; }
    public void setChassisnummer(String chassisnummer) { this.chassisnummer = chassisnummer; }
    public int getTellerStand() { return tellerStand; }
    public void setTellerStand(int tellerStand) { this.tellerStand = tellerStand; }

}
