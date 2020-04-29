// SumaPrimos2 calcula la suma de los primos entre 1 y 10.000.000
// dividiendo la tarea en cinco hilos
package ej2bloque4;

import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class SumaPrimos2 {

    public static void main(String[] x) throws InterruptedException, BrokenBarrierException {

        Result suma1 = new Result();
        Result suma2 = new Result();
        Result suma3 = new Result();
        Result suma4 = new Result();
        Result suma5 = new Result();
        HiloFinal hf = new HiloFinal(suma1, suma2, suma3, suma4, suma5);
        CyclicBarrier barrera = new CyclicBarrier(5, hf);

        Calculator p1 = new Calculator(1, 1, 2000000, suma1, barrera);
        Calculator p2 = new Calculator(2, 2000001, 4000000, suma2, barrera);
        Calculator p3 = new Calculator(3, 4000001, 6000000, suma3, barrera);
        Calculator p4 = new Calculator(4, 6000001, 8000000, suma4, barrera);
        Calculator p5 = new Calculator(5, 8000001, 10000000, suma5, barrera);
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
    }
}
