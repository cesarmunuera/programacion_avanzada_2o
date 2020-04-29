/*
 * Programa que lanza cuatro lectores y un escritor.
 * que se comunican a través de un buzón de mensajes.
 * Debe comprobarse que no se pierden los mensajes ni se leen dos veces
 */
package PECL1;

public class PruebaBuzon1
{
    public static void main(String[] s)
    {
        Buzon buzonX = new Buzon();
        Resultado r = new Resultado();
        Productor A = new Productor("A ",80,buzonX, r);
        Productor B = new Productor("B ",80,buzonX, r);
        Productor C = new Productor("C ",80,buzonX, r);
        Consumidor José = new Consumidor(buzonX, "José", r);
        Consumidor Ana = new Consumidor(buzonX, "Ana", r);
        Consumidor María = new Consumidor(buzonX, "María", r);
        A.start();
        B.start();
        C.start();
        José.start();
        Ana.start();
        María.start();
    }
}
