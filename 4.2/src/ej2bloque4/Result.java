// Un objeto de la clase Result es un BigInteger con un método sumar(int) 
package ej2bloque4;

import java.math.BigInteger;

public class Result {

    private BigInteger suma = new BigInteger("0");

    public BigInteger getSuma() {
        return suma;
    }

    public synchronized void sumar(int n) {
        BigInteger bn = new BigInteger(String.valueOf(n));
        suma = suma.add(bn);
    }
}
