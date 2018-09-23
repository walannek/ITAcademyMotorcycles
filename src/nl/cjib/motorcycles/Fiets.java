package nl.cjib.motorcycles;

class Fiets extends Vervoermiddel {
    private String zadeltype;

    @Override
    void rijden() {
        System.out.println("fiets gaat rijden");
    }

    @Override
    void afstand(int afstand) {

    }

    public String getZadeltype() {
        return zadeltype;
    }

    public void setZadeltype(String zadeltype) {
        this.zadeltype = zadeltype;
    }
}
