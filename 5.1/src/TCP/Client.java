package TCP;

import java.io.*;
import java.net.*;

public class Client {

    public static void main(String args[]) {
        Socket cliente;                                               // ADVERTENCIA!!!! ES POSIBLE QUE EL PUERTO QUE USO, LO HAYAS USADO TU EN 
        DataInputStream entrada;                                      // EJECUCIONES ANTERIORES. SI ES ASÍ, CAMBIALO A OTRO QUE NO HAYAS USADO.
        DataOutputStream salida;
        int n1, n2, result1, result2;
        try {
            cliente = new Socket(InetAddress.getLocalHost(), 5000);   //Creamos el socket para conectarnos al puerto 5000 del servidor
            entrada = new DataInputStream(cliente.getInputStream());  //Creamos los canales de entrada/salida
            salida = new DataOutputStream(cliente.getOutputStream());

            n1 = 1;
            n2 = 2;

            salida.writeInt(n1);                                     // Enviamos un mensaje al servidor
            salida.writeInt(n2);

            // ---- TEST ---- //
            result1 = n1 * n2;
            System.out.println("Mi mensaje es: " + result1);
            // ---- TEST ---- //

            result2 = entrada.readInt();                              // Leemos la respuesta
            System.out.println("Respuesta del servidor: " + result2);
            
            cliente.close();                                          // Cerramos la conexión
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
