// SumaPrimos calcula la suma de los primos entre 1 y 10.000.000
// dividiendo la tarea en cinco hilos
package ejemplo2;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SumaPrimos {

    public static void main(String[] x) throws InterruptedException, ExecutionException {

        ArrayList<Integer> l1 = new ArrayList<>(100);
        ArrayList<Integer> l2 = new ArrayList<>(100);
        ArrayList<Future<BigInteger>> ALFuture = new ArrayList<>();

        ExecutorService executor = Executors.newFixedThreadPool(10);

        BigInteger resultadoFB = BigInteger.ZERO;
        BigInteger finalResult = BigInteger.ZERO;

        rellenarl1(l1);
        rellenarl2(l2);

        for (int i = 0; i < 100; i++) { //Creo las 100 tareas y las almaceno en un future de tipo BigInteger. Luego lo almaceno en un AL
            Calculator tarea = new Calculator(l1.get(i), l2.get(i));
            Future<BigInteger> resultadoF = executor.submit(tarea);
            ALFuture.add(resultadoF);
        }

        executor.shutdown();

        for (int i = 0; i < ALFuture.size(); i++) {
            Future<BigInteger> result = ALFuture.get(i); // Saco del AL las tareas
            resultadoFB = result.get();                  // De cada tarea, saco su correspondiente resultado
            finalResult = add(finalResult, resultadoFB); // Lo voy almacenando en un contador
        }
        System.out.println("La suma de los numeros primos del 1 al 10.000.000 es: " + finalResult.toString());
    }

    private static void rellenarl1(ArrayList l1) { //ArrayList con la cifra desde donde debe comenzar
        l1.add(0, 1);
        for (int i = 1; i < 100; ++i) {
            l1.add(i, (i * 100000) + 1);
        }
    }

    private static void rellenarl2(ArrayList l2) { //ArrayList con la cifra hasta donde debe acabar
        l2.add(0, 100000);
        for (int i = 2; i < 101; i++) {
            l2.add(i - 1, i * 100000);
        }
    }

    private static BigInteger add(BigInteger n1, BigInteger n2) {
        return n1.add(n2);
    }
}
