package nl.cjib.motorcycles;

import static nl.cjib.motorcycles.MainClass.gaRijden;

class MyThread implements Runnable {

    private final String name;

    MyThread(String threadname) {
        name = threadname;
        Thread t = new Thread(this, name);
        System.out.println("Teamlid: " + name + " is toegevoegd");
        t.start();
    }
    @Override
    public void run() {

        try {
            gaRijden();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(name + "Interrupted");
        }
    }
}
