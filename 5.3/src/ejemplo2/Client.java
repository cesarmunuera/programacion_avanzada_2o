package ejemplo2;

import java.rmi.*;
import static java.lang.Thread.sleep;

public class Client {

    public static void main(String args[]) {
        int respuesta = 0;
        try {
            int n1 = 2;
            int n2 = 3;

            InterfaceCalcula obj = (InterfaceCalcula) Naming.lookup("//127.0.0.1/ObjetoCalcula"); //Localiza el objeto distribuido
            
            respuesta = obj.multiplicar(n1, n2);                                                  //Llama al método saludar
            System.out.println("El resultado de multiplicar " + n1 + " * " + n2 + " = " + respuesta);
            sleep(2000);                                        //Para que dé tiempo a leer la respuesta antes de que se cierre la ventana

            respuesta = obj.elevar(n1, n2);                                                        //Llama al método saludar
            System.out.println("El resultado de elevar " + n1 + "^" + n2 + " = " + respuesta);
            sleep(2000);                                        //Para que dé tiempo a leer la respuesta antes de que se cierre la ventana
        } catch (Exception e) {
            System.out.println("Excepción : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
