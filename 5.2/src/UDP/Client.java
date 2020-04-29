package UDP;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;                                                    //ADVERTENCIA!!!!!! SI NO TE FUNCIONA, ES POR EL PUERTO.
import java.net.SocketException;                                                //PON UNO QUE NO HAYAS USADO ANTES, O REINICIA NETBEANS
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    public static void main(String args[]) {
        //puerto del servidor
        final int PUERTO_SERVIDOR = 5098;
        //buffer donde se almacenara los mensajes
        byte[] buffer = new byte[1024];

        try {
            System.out.println("Iniciado cliente UDP");
            //Obtengo la localizacion de localhost
            InetAddress direccionServidor = InetAddress.getByName("localhost");

            //Creo el socket de UDP
            DatagramSocket socketUDP = new DatagramSocket();

            //YEAR - MONTH - DAY
            String mensaje = "2000,01,01";
            String respuestaEsperada = Operator.operation(mensaje);

            //Convierto el mensaje a bytes
            buffer = mensaje.getBytes();

            //Creo un datagrama
            DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length, direccionServidor, PUERTO_SERVIDOR);

            //Lo envio con send
            System.out.println("Envio el datagrama");
            socketUDP.send(pregunta);

            //Preparo la respuesta
            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);

            //Recibo la respuesta
            socketUDP.receive(peticion);
            System.out.println("Recibo la peticion");

            //Cojo los datos y lo muestro
            String age = new String(peticion.getData(), 0,peticion.getLength());
            System.out.println("La edad esperada es: " + respuestaEsperada);
            System.out.println("Server said u r " + age + " years old");

            //cierro el socket
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

