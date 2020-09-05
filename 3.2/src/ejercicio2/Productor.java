package ejercicio2;

import static java.lang.Thread.sleep;

public class Productor extends Thread {

    private Termometro termometro;

    public Productor(Termometro termometro) {
        this.termometro = termometro;
    }

    public void run() {
        double num = 0;
        while (true) {
            try {
                num = (Math.random() * 30);
                sleep((int) (200 + 600 * Math.random()));
            } catch (InterruptedException e) {
            }
            termometro.enviaMensaje(num);
        }
    }

}
