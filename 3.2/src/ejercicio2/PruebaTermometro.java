package ejercicio2;

/**
 *
 * @author cesar
 */
public class PruebaTermometro {

    public static void main(String[] s) {
        Termometro termometro = new Termometro();
        Productor Sensor = new Productor(termometro);
        Consumidor Display = new Consumidor(termometro);
        Sensor.start();
        Display.start();
    }
}
