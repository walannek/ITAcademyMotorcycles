package nl.cjib.motorcycles;

import static nl.cjib.motorcycles.MainClass.*;

class MyThread implements Runnable {

    private String name;
    private Thread t;

    MyThread(String threadname){
        name = threadname;
        t = new Thread(this, name);
        t.start();
    }

    public void run() {

        try {
            System.out.println(name);
            gaRijden();
            Thread.sleep(100);
        }
        catch (InterruptedException e) {
            System.out.println(name + "Interrupted");
        }

        System.out.println(name + " exiting.");
    }
}
