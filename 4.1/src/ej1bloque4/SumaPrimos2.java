// SumaPrimos2 calcula la resultado de los primos entre 1 y 10.000.000
// dividiendo la tarea en cinco hilos

package ej1bloque4;

import java.util.*;

public class SumaPrimos2 {

    public static void main(String[] x) {

        Resultado resultado = new Resultado();
        long t0 = (new Date()).getTime(); //t0=instante de inicio de los cálculos
        Calculador p1 = new Calculador(1, 2000000, resultado);
        Calculador p2 = new Calculador(2000001, 4000000, resultado);
        Calculador p3 = new Calculador(4000001, 6000000, resultado);
        Calculador p4 = new Calculador(6000001, 8000000, resultado);
        Calculador p5 = new Calculador(8000001, 10000000, resultado);
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        try {
            p1.join();
            p2.join();
            p3.join();
            p4.join();
            p5.join(); //esperamos a que terminen todos
        } catch (InterruptedException e) {
        }
        long t1 = (new Date()).getTime(); //t1=instante de final de los cálculos
        System.out.println("La suma de los números primos hasta 10000000 es: " + resultado.getSuma() + " calculado en " + (t1 - t0) + " miliseg.");
    }
}
