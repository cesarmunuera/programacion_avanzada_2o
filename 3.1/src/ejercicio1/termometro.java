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
public class termometro {

    float temp;
    Lock l = new ReentrantLock();

    public termometro(float temperatura) {
        this.temp = temp;
    }

    public void insertart(float temperatura) throws InterruptedException {
        l.lock();
        try {
            sleep(100 + (int) (200 * Math.random()));
            temp = temperatura;
        } catch (InterruptedException e) {
        } finally {
            l.unlock();
        }
    }

    public void sacart() throws InterruptedException {
        l.lock();
        try {
            sleep(100 + (int) (200 * Math.random()));
            System.out.println("temperatura= " + temp);
        } catch (InterruptedException e) {
        } finally {
            l.unlock();
        }
    }

}
