package nl.cjib.motorcycles;

public class Motor {

    private String colour;
    private int pk;
    private int totalKM;
    private String fuel;

    public String getColour() {
        return this.colour;
    }

    public void setColour (String colour) {
        this.colour = colour;
    }

    public int getPk () {
        return this.pk;
    }

    public void setPk (int pk) {
        this.pk = pk;
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
}
