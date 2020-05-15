package TCP;

import java.io.*;
import java.net.*;

public class Server {

    public static void main(String args[]) {
        ServerSocket servidor;                  // ADVERTENCIA!!!! ES POSIBLE QUE EL PUERTO QUE USO, LO HAYAS USADO TU EN 
        Socket conexion;                        // EJECUCIONES ANTERIORES. SI ES ASÍ, CAMBIALO A OTRO QUE NO HAYAS USADO.
        DataOutputStream salida;
        DataInputStream entrada;
        int num = 0;
        try {
            servidor = new ServerSocket(5000); // Creamos un ServerSocket en el puerto 5000
            System.out.println("Servidor Arrancado....");
            while (true) {
                conexion = servidor.accept();     // Esperamos una conexión
                num++;
                System.out.println("Conexión nº " + num + " desde: " + conexion.getInetAddress().getHostName());
                entrada = new DataInputStream(conexion.getInputStream());  // Abrimos los canales de E/S
                salida = new DataOutputStream(conexion.getOutputStream());
                int mensaje1 = entrada.readInt();    //Leemos el mensaje del cliente
                int mensaje2 = entrada.readInt();    //Leemos el mensaje del cliente
                int respuesta = mensaje1 * mensaje2;

                System.out.println("Primer numero recibido: " + mensaje1 + ". Segundo numero recibido: " + mensaje2 + ". La solucion es: " + respuesta);
                salida.writeInt(respuesta);  // Le respondemos
                conexion.close();            // Y cerramos la conexión
            }
        } catch (IOException e) {
            System.out.println("No ha ido como deberia");
        }
    }
}
