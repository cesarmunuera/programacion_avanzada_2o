// SumaPrimos2 calcula la suma de los primos entre 1 y 10.000.000
// dividiendo la tarea en cinco hilos
package Ejercicio3Bloque4;

import java.util.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SumaPrimos2 {

    public static void main(String[] x) {
        ArrayList<Integer> l1 = new ArrayList<>(100);
        ArrayList<Integer> l2 = new ArrayList<>(100);
        rellenarl1(l1);
        rellenarl2(l2);

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) { //10.000.000 entre 100.000 es 100, por tanto 100 tareas (0-99)
            Calculator tarea = new Calculator(l1.get(i), l2.get(i));
            executor.execute(tarea);
        }
        executor.shutdown();
        try {
            executor.awaitTermination(6, TimeUnit.MINUTES);
        } catch (InterruptedException ex) {
        }
    }

    private static void rellenarl1(ArrayList l1) { // AL con las posiciones de inicio de cada tarea
        l1.add(0, 1);
        for (int i = 1; i < 100; ++i) {
            l1.add(i, (i * 100000) + 1);
            //System.out.println(l1.toString());
        }
    }

    private static void rellenarl2(ArrayList l2) { // AL con las posiciones de fin de cada tarea
        l2.add(0, 100000);
        for (int i = 2; i < 101; i++) {
            l2.add(i - 1, i * 100000);
            //System.out.println(l2.toString());
        }
    }

}
