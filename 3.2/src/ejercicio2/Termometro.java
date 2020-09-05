package ejercicio2;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Termometro {

    private final Lock CERROJO = new ReentrantLock();
    private final Condition HAY_HUECO = CERROJO.newCondition();
    private final Condition HAY_MENSAJE = CERROJO.newCondition();
    private final int SIZE = 10;

    ArrayList<Double> AL = new ArrayList<>(SIZE);

    public void enviaMensaje(double num) {
        try {
            CERROJO.lock();
            while (AL.size() == SIZE) {
                try {
                    HAY_HUECO.await(); // espera a que le manden una señal, avisando de que hay hueco
                } catch (InterruptedException e) {
                }
            }
            AL.add(num);
            System.out.println("Productor genera " + num);
            HAY_MENSAJE.signalAll();
        } finally {
            CERROJO.unlock();
        }
    }

    public double recibeMensaje() {
        double mensaje;
        try {
            CERROJO.lock();
            while (AL.isEmpty()) {
                try {
                    HAY_MENSAJE.await(); // espera a que le manden una señal, avisando de que ya hay mensajes
                } catch (Exception e) {
                }
            }
            mensaje = AL.get(0);
            AL.remove(0);
            HAY_HUECO.signalAll();
            return mensaje;
        } finally {
            CERROJO.unlock();
        }
    }
}
