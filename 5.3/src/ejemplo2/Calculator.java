package ejemplo2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Calculator extends UnicastRemoteObject implements InterfaceCalcula {

    public Calculator() throws RemoteException {                                //Constructor
    }

    public int multiplicar(int n1, int n2) throws RemoteException // Implementación del método remoto
    {
        return n1 * n2;
    }

    public int elevar(int n1, int n2) throws RemoteException // Implementación del método remoto
    {
        int resultado = (int) Math.pow(n1, n2);
        return resultado;
    }
}
