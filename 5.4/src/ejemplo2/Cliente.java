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
            int año = 1975;
            int mes = 11;
            int dia = 20;
            InterfaceFecha obj = (InterfaceFecha) Naming.lookup("//127.0.0.1/ObjetoSaluda"); //Localiza el objeto distribuido
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

