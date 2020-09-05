//Calculador sirve para sumar los números primos que hay en un intervalo (x,y)

package Ejercicio3Bloque4;

public class Calculator extends Thread {

    int from, to;

    public Calculator(int x, int y) {
        from = x;
        to = y;
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

    public void run() {
        for (int i = from; i <= to; i++) {
            if (esPrimo(i)) {
                System.out.println("El número: " + i + " es primo.");
            }
        }
    }
}
