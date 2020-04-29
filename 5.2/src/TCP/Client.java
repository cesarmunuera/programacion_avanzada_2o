package TCP;

import java.io.*;
import java.net.*;

public class Client {

    public static void main(String args[])                                       //ADVERTENCIA!!!!!! SI TE SALTA ERROR ES POR EL PUERTO.
    {                                                                           //CAMBIALO A UNO QUE NO HAYAS USADO O REINICIA NETBEANS
        Socket cliente;
        DataInputStream entrada;
        DataOutputStream salida;
        int day, month, year, result;
        try {
            cliente = new Socket(InetAddress.getLocalHost(), 5087);              //Creamos el socket para conectarnos al puerto 5000 del servidor
            entrada = new DataInputStream(cliente.getInputStream());            //Creamos los canales de entrada/salida
            salida = new DataOutputStream(cliente.getOutputStream());

            day = 06;
            month = 06;
            year = 1944;

            salida.writeInt(day);                                               // Enviamos un mensaje al servidor
            salida.writeInt(month);
            salida.writeInt(year);
            result = entrada.readInt(); // Leemos la respuesta
            System.out.println("Server said u r " + result + " years old");
            cliente.close();                                                    // Cerramos la conexión
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
