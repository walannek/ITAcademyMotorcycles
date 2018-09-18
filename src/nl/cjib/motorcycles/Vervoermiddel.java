package nl.cjib.motorcycles;

public class Vervoermiddel {
    private int aantalWielen;
    private int tellerStand;
    private String kenteken;
    private String chassisnummer;
    private String categorie;
    private String merk;
    private String merkType;
    private long eigenGewicht;
    private boolean gemotoriseerd;
    private boolean brandstof;
    private String motorisering;

    public int getAantalWielen() { return aantalWielen; }
    public void setAantalWielen(int aantalWielen) { this.aantalWielen = aantalWielen; }
    public String getKenteken() { return kenteken; }
    public void setKenteken(String kenteken) { this.kenteken = kenteken; }
    public String getChassisnummer() { return chassisnummer; }
    public void setChassisnummer(String chassisnummer) { this.chassisnummer = chassisnummer; }
    public int getTellerStand() { return tellerStand; }
    public void setTellerStand(int tellerStand) { this.tellerStand = tellerStand; }
    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }
    public String getMerk() { return merk; }
    public void setMerk(String merk) { this.merk = merk; }
    public String getMerkType() { return merkType; }
    public void setMerkType(String merkType) { this.merkType = merkType; }
    public long getEigenGewicht() { return eigenGewicht; }
    public void setEigenGewicht(long eigenGewicht) { this.eigenGewicht = eigenGewicht; }
    public boolean isGemotoriseerd() { return gemotoriseerd; }
    public void setGemotoriseerd(boolean gemotoriseerd) { this.gemotoriseerd = gemotoriseerd; }
    public boolean isBrandstof() { return brandstof; }
    public void setBrandstof(boolean brandstof) { this.brandstof = brandstof; }
    public String getMotorisering() { return motorisering; }
    public void setMotorisering(String motorisering) { this.motorisering = motorisering; }

}
