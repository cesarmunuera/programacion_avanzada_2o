/*
 * La clase Termometro tiene que estar protegida con un cerrojo
 * El método enviaMensaje debe esperar si el buzón está lleno
 * El método recibeMensaje debe esperar si el buzón está vacío.
 * Cuando un hilo completa su operación, desbloquea a los que estén esperando
 * para que puedan continuar intentando su acción.
 */
package Ejercicio2;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Termometro {

    private Lock cerrojo = new ReentrantLock();
    private Condition hay_hueco = cerrojo.newCondition();
    private Condition hay_mensaje = cerrojo.newCondition();
    private int SIZE = 15;

    ArrayList<Double> AL = new ArrayList<Double>(15);

    public void enviaMensaje(double num) {
        try {
            cerrojo.lock();
            while (AL.size() == SIZE) {
                try {
                    hay_hueco.await(); // espera a que le manden una señal
                } catch (Exception e) {
                }
            }
            AL.add(num);
            System.out.println("Productor genera " + num);
            hay_mensaje.signalAll();
        } finally {
            cerrojo.unlock();
        }
    }

    public double recibeMensaje() {
        double mensaje;
        try {
            cerrojo.lock();
            while (AL.isEmpty()) {
                try {
                    hay_mensaje.await(); // espera a que le manden una señal
                } catch (Exception e) {
                }
            }
            mensaje = AL.get(0);
            AL.remove(0);
            hay_hueco.signalAll();
            return mensaje;
        } finally {
            cerrojo.unlock();
        }
    }
}
