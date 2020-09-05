package ejercicio1;

/**
 *
 * @author cesar
 */
public class Main {


    public static void main(String[] args) {
        // TODO code application logic here
        termometro t1 = new termometro(0);
        meter_ta m = new meter_ta(0, t1);
        sacar_ta s = new sacar_ta(0, t1);

        m.start();
        s.start();
    }

}
