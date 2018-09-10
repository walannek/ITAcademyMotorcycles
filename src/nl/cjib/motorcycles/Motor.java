package nl.cjib.motorcycles;

import java.sql.SQLOutput;

public class Motor {

    public static void main(String[] args) {

        Belasting zaken = new Belasting();
        zaken.setName("Wegenbeslasting");
        zaken.setBedrag(70);
        zaken.setPeriode("Maandelijks");

        System.out.println(zaken.getName());
        System.out.println(zaken.getBedrag());
        System.out.println(zaken.getPeriode());



    }





}
