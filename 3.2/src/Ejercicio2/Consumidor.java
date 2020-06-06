/*
 * La clase Consumidor define hilos que leen mensajes de un buzón de mensajes
 * y los muestran por pantalla.
 * El buzón y el número de mensajes, los reciben como parámetros del constructor
 * antes de terminar.
 * Entre lectura y lectura, esperan un tiempo aleatorio entre 0.5 y 1 seg.
 */
package Ejercicio2;

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
