package ejemplo2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceCalcula extends Remote {

    int multiplicar(int n1, int n2) throws RemoteException;

    int elevar(int n1, int n2) throws RemoteException;
}
