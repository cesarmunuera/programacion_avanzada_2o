/*Programa que lanza cuatro lectores y un escritor.
 * que se comunican a través de un buzón de mensajes.
 * Debe comprobarse que no se pierden los mensajes ni se leen dos veces
 */

package Ejercicio3;

public class PruebaTermometro {

    public static void main(String[] s) {
        Termometro termometro = new Termometro();

        Productor sensor;
        for (int i = 0; i < 10; i++) {
            sensor = new Productor("Sensor" + i, 15, termometro);
            sensor.start();
        }

        Consumidor display;
        for (int i = 0; i < 10; i++) {
            display = new Consumidor(termometro, "Display" + i);
            display.start();
        }

    }
}
