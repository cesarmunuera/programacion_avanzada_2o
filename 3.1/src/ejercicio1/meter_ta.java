package ejercicio1;

/**
 *
 * @author cesar
 */
public class meter_ta extends Thread {

    float temperatura;
    termometro t1;

    public meter_ta(float t, termometro t1) {
        this.temperatura = t;
        this.t1 = t1;
    }

    public void run() {
        while (true) {
            try {
                sleep(100 + (int) (200 * Math.random()));
                temperatura = (1 + (int) (29 * Math.random()));
                t1.insertart(temperatura);
            } catch (Exception e) {
            }
        }
    }

}
