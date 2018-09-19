package nl.cjib.motorcycles;

public class Fiets extends Vervoermiddel{
    private String zadeltype;

    @Override
    void rijden() {
        System.out.println("fiets gaat rijden");
    }

    public String getZadeltype() { return zadeltype; }
    public void setZadeltype(String zadeltype) { this.zadeltype = zadeltype; }
}
