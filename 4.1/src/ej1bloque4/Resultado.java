// Un objeto de la clase Resultado es un BigInteger con un método sumar(int) 

package ej1bloque4;

import java.math.BigInteger;

public class Resultado {

    private AtomicBigInteger suma = new AtomicBigInteger("0");

    public void sumar(int n) {
        BigInteger num = new BigInteger(String.valueOf(n));
        suma.getAndAdd(num);
    }
    
    public BigInteger getSuma() {
        return suma.get();
    }
}
