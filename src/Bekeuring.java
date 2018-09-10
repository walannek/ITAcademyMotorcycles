public class Bekeuring {
    private String kenteken;
    private double bedrag;

    // niet geformateerd
    public void setKenteken(String kenteken) {
        this.kenteken = kenteken;
    }
    public String getKenteken() {
        return kenteken;
    }
    // boete
    public double getBedrag() {
        return bedrag;
    }
    public void setBedrag(double bedrag) {
        this.bedrag = bedrag;
    }

}
