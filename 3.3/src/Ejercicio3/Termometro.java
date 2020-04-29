/*
 * La clase Termometro tiene que estar protegida con un cerrojo
 * El método enviaMensaje debe esperar si el buzón está lleno
 * El método recibeMensaje debe esperar si el buzón está vacío.
 * Cuando un hilo completa su operación, desbloquea a los que estén esperando
 * para que puedan continuar intentando su acción.
 */
package Ejercicio3;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Termometro {

    private static int escritores = 10;
    private static int lectores = 10;
    private Semaphore semaphoreEscritores = new Semaphore(escritores);
    private Semaphore semaphoreLectores = new Semaphore(lectores);
    private Semaphore semaphoreMemory = new Semaphore(1, true);
    ArrayList<Double> memory = new ArrayList<>(10);

    public Termometro() {
        try {
            semaphoreLectores.acquire(lectores);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void escribir(double num) {
        try {
            semaphoreMemory.acquire();
            memory.add(num);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            semaphoreMemory.release();
        }
    }

    private double leer() {
        double r = 0;
        try {
            semaphoreMemory.acquire();
            r = memory.remove(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            semaphoreMemory.release();
        }
        return r;
    }

    public void imprimirMemoria() {
        System.out.println("Memoria: " + memory.toString());
    }

    public void escritores(double num, String nombre) {
        try {
            semaphoreEscritores.acquire();
            escribir(num);
            System.out.println("Productor " + nombre + " numero: " + num);
            imprimirMemoria();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            semaphoreLectores.release();
        }
    }

    public double lectores() {
        double leido = -999.99;
        try {
            semaphoreLectores.acquire(); 
            leido = leer();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            semaphoreEscritores.release();
        }
        return leido;
    }
}
