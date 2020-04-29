/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PECL1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author cesar
 */
public class Resultado {

    private int resultado;

    private Lock lock = new ReentrantLock();

    public int getResultado() {
        try {
            lock.lock();
            return resultado;
        } finally {
            lock.unlock();
        }
    }

    public void addResultado(int number) {
        try {
            lock.lock();
            resultado = resultado + number;
        } finally {
            lock.unlock();
        }
    }

}
