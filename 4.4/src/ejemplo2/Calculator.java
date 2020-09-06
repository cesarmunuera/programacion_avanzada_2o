//Calculador sirve para sumar los números primos que hay en un intervalo (x,y)
package ejemplo2;

import java.math.BigInteger;
import java.util.concurrent.Callable;

public class Calculator implements Callable<BigInteger> {

    int from, to;
    BigInteger r = BigInteger.ZERO;

    public Calculator(int x, int y) {
        from = x;
        to = y;

    }

    @Override
    public BigInteger call() {
        for (int i = from; i <= to; i++) {
            if (esPrimo(i)) {
                r = sum(i, r);
            }
        }
        return r;
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

    private synchronized BigInteger sum(int n, BigInteger result) {
        BigInteger bn = new BigInteger(String.valueOf(n)); // Transformo en numero en un BI
        return result.add(bn);                             // Se lo añado al resultado anterior, como un contador
    }

}
