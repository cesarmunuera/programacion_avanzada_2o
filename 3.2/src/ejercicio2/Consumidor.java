package ejercicio2;

import static java.lang.Thread.sleep;

public class Consumidor extends Thread {

    private Termometro termometro;
    private String nombre;

    public Consumidor(Termometro termometro) {
        this.termometro = termometro;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep((int) (300 + 400 * Math.random()));
            } catch (InterruptedException e) {
            }
            System.out.println("Lector ha leído " + termometro.recibeMensaje());
        }
    }
}

