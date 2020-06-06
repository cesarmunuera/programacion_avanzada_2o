/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import static java.lang.Thread.sleep;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author cesar
 */
public class sacar_ta extends Thread {

    float temperatura;
    termometro t1;

    public sacar_ta(float t, termometro t1) {
        this.temperatura = t;
        this.t1 = t1;
    }

    public void run() {
        while (true) {
            try {
                sleep(100 + (int) (200 * Math.random()));
                t1.sacart();
            } catch (Exception e) {
            }
        }
    }
}
