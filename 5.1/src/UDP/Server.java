package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    public static void main(String args[]) {                         // ADVERTENCIA!!!! ES POSIBLE QUE EL PUERTO QUE USO, LO HAYAS USADO TU EN 
        final int PUERTO = 5105;                                     // EJECUCIONES ANTERIORES. SI ES ASÍ, CAMBIALO A OTRO QUE NO HAYAS USADO.
        byte[] buffer = new byte[1024];

        try {
            System.out.println("Iniciado el servidor UDP");
            //Creacion del socket
            DatagramSocket socketUDP = new DatagramSocket(PUERTO);

            //Siempre atendera peticiones
            while (true) {

                //Preparo la respuesta
                DatagramPacket paquete =new DatagramPacket(buffer, buffer.length);
                
                //Recibo el datagrama
                socketUDP.receive(paquete);
                System.out.println("Recibo la informacion del cliente");

                //Convierto lo recibido y mostrar el mensaje
                String mensajeRecibido = new String(paquete.getData());
                System.out.println("Mensaje recibido del cliente: " + mensajeRecibido);

                //Obtengo el puerto y la direccion de origen
                //Sino se quiere responder, no es necesario
                int puertoCliente = paquete.getPort();
                InetAddress direccion = paquete.getAddress();

                String mensajeAEnviar = Operator.operation(mensajeRecibido);
                buffer = mensajeAEnviar.getBytes();
                System.out.println("El resultado de la operación es: " + mensajeAEnviar);

                //Creo el datagrama
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);

                //Envio la información
                System.out.println("Envio la informacion del cliente");
                socketUDP.send(respuesta);

            }

        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
