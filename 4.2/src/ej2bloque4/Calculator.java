//Calculador sirve para sumar los números primos que hay en un intervalo (x,y)
package ej2bloque4;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Calculator extends Thread {

    private int desde, hasta, id;
    private Result r;
    private CyclicBarrier barrier;

    public Calculator(int id, int x, int y, Result r, CyclicBarrier barrier) {
        desde = x;
        hasta = y;
        this.r = r;
        this.barrier = barrier;
        this.id = id;
    }

    public void run() {
        try {
            for (int i = desde; i <= hasta; i++) {
                if (esPrimo(i)) {
                    r.sumar(i);
                }
            }
            System.out.println("Thread number " + id + " has arrived to the barrier.");
            barrier.await();
        } catch (InterruptedException ex) {
            Logger.getLogger(Calculator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BrokenBarrierException ex) {
            Logger.getLogger(Calculator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean esPrimo(int n) {
        int raiz = (int) Math.sqrt((double) n);
        boolean primo = true;
        int i = 2;
        while (primo && i <= raiz) {
            if (n % i == 0) {
                primo = false;
            }
            i++;
        }
        return primo;
    }
}
