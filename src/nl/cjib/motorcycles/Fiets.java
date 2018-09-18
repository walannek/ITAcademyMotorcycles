package nl.cjib.motorcycles;

public class Fiets extends Vervoermiddel{
    private String zadeltype;
    public void fietsen(){
        setTellerStand(getTellerStand()+1);
    }

    @Override
    void rijden() {
        System.out.println("fiets gaat rijden");
    }
}
