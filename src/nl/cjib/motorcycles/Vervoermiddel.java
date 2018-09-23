package nl.cjib.motorcycles;

abstract class Vervoermiddel {

    abstract void rijden() throws InterruptedException;
    abstract void afstand(int afstand);
}
