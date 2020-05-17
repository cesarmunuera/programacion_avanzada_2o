package ejemplo2;
import java.rmi.*;
import java.io.*;
import static java.lang.Thread.sleep;

public class Cliente
{
    public static void main(String args[])
    {
        int respuesta = 0;
        try
        {
            int año = 1999;
            int mes = 07;
            int dia = 28;
            InterfaceFecha obj = (InterfaceFecha) Naming.lookup("//127.0.0.1/ObjetoFecha"); //Localiza el objeto distribuido
            respuesta = obj.calcular(año,mes,dia);                                                   //Llama al método saludar
            System.out.println("Tienes: " + respuesta + " años.");
            sleep(1000); //Para que dé tiempo a leer la respuesta antes de que se cierre la ventana
        }
        catch (Exception e)
        {
            System.out.println("Excepción : " + e.getMessage());
            e.printStackTrace();
        }
    }
}

