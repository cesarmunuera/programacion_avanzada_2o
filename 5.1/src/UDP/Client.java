package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    public static void main(String args[]) {                // ADVERTENCIA!!!! ES POSIBLE QUE EL PUERTO QUE USO, LO HAYAS USADO TU EN 
        //puerto del servidor                               // EJECUCIONES ANTERIORES. SI ES ASÍ, CAMBIALO A OTRO QUE NO HAYAS USADO.
        final int PUERTO_SERVIDOR = 5105;
        //buffer donde se almacenara los mensajes
        byte[] buffer = new byte[1024];

        try {
            //Obtengo la localizacion de localhost
            InetAddress direccionServidor = InetAddress.getByName("localhost");

            //Creo el socket de UDP
            DatagramSocket socketUDP = new DatagramSocket();

            String numeros = "3,2";
            
            String respuestaEsperada = Operator.operation(numeros);
            String mensaje = numeros;

            //Convierto el mensaje a bytes
            buffer = mensaje.getBytes();

            //Creo un datagrama
            DatagramPacket datagramaEnvio = new DatagramPacket(buffer, buffer.length, direccionServidor, PUERTO_SERVIDOR);

            //Lo envio con send
            System.out.println("Envio el datagrama");
            socketUDP.send(datagramaEnvio);

            //Preparo la respuesta
            DatagramPacket datagramaRespuesta = new DatagramPacket(buffer, buffer.length);

            //Recibo la respuesta
            socketUDP.receive(datagramaRespuesta);
            System.out.println("Recibo la peticion");

            //Cojo los datos y lo muestro
            mensaje = new String(datagramaRespuesta.getData());
            System.out.println("La respuesta esperada es: " + respuestaEsperada);
            System.out.println("La respuesta del servidor es: " + mensaje);

            //Cierro el socket
            socketUDP.close();

        } catch (SocketException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
