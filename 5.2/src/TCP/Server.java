package TCP;
import java.io.*;
import java.net.*;
import java.time.LocalDate;
import java.time.Period;

public class Server                                                             //ADVERTENCIA!!!!!! SI TE SALTA ERROR ES POR EL PUERTO. 
{                                                                               //CAMBIALO A UNO QUE NO HAYAS USADO O REINICIA NETBEANS
    public static void main(String args[])
    {
        ServerSocket servidor;
        Socket conexion;
        DataOutputStream salida;
        DataInputStream entrada;
        int num = 0;
        int day,month,year;
        
        try
        { 
            servidor = new ServerSocket(5087);                                  // Creamos un ServerSocket en el puerto 5000
            System.out.println("Servidor Arrancado....");
            
            while (true){
                conexion = servidor.accept();                                   // Esperamos una conexión
                num++;
                System.out.println("Conexión nº "+num+" desde: "+conexion.getInetAddress().getHostName());
                
                entrada = new DataInputStream(conexion.getInputStream());       // Abrimos los canales de E/S
                salida  = new DataOutputStream(conexion.getOutputStream());
                
                day = entrada.readInt();                                        // Leemos el mensaje del cliente
                month = entrada.readInt();                                      // Leemos el mensaje del cliente
                year = entrada.readInt();                                       // Leemos el mensaje del cliente
                
                LocalDate fecha = LocalDate.of(year,month,day);
                LocalDate fechaHoy = LocalDate.now();
                Period respuestaP = Period.between(fecha,fechaHoy);
                int respuesta = respuestaP.getYears();
                
                salida.writeInt(respuesta);                                     // Le respondemos
                
                conexion.close();                                               // Y cerramos la conexión
            }
        } catch (IOException e) {
            System.out.println("No ha ido como deberia, algo ha fallado");
        }
    }
}
