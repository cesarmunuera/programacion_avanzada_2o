package ejemplo2;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;                                              //ADVERTENCIA!!!!!! SI NO TE EJECUTA, TIENES QUE CERRAR NETBEANS Y VOLVERLO A ABRIR
                                                                                // ESTO SE DEBE A QUE IGUAL HAS USADO EL PUERTO 1099 ANTES. TIENES QUE REINICIAR NETBEANS

public class Server {

    public static void main(String args[]) {
        try {
            Calculator obj = new Calculator();                                  //Crea una instancia del objeto que implementa la interfaz, como objeto a registrar
            Registry registry = LocateRegistry.createRegistry(1099);            //Arranca rmiregistry local en el puerto 1099
            Naming.rebind("//localhost/ObjetoCalcula", obj);                    //rebind sólo funciona sobre una url del equipo local 
            System.out.println("El Objeto Calcula ha quedado registrado");
        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
