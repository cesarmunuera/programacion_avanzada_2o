/*
 * La clase Termometro tiene que estar protegida con un cerrojo
 * El método enviaMensaje debe esperar si el buzón está lleno
 * El método recibeMensaje debe esperar si el buzón está vacío.
 * Cuando un hilo completa su operación, desbloquea a los que estén esperando
 * para que puedan continuar intentando su acción.
 */
package Ejercicio4;

import java.util.ArrayList;

public class Termometro {

    private static int SIZE = 10;
    private ArrayList buffer = new ArrayList(SIZE);

    
    public void printBuffer() {
        System.out.println("Memory: " + buffer.toString());
    }

    public synchronized void enviaMensaje(String num, String Productor) {
        while (buffer.size() == SIZE) {
            try {
                wait();
            } catch (Exception e) {
            }
        }
        buffer.add(num);
        System.out.println(Productor + " genera " + num);
        printBuffer();
        notifyAll();
    }

    public synchronized String recibeMensaje() {
        String mensaje;
        while (buffer.isEmpty()) {
            try {
                wait();
            } catch (Exception e) {
            }
        }
        mensaje = (String) buffer.get(0);
        buffer.remove(0);
        notifyAll();
        return mensaje;
    }
}
