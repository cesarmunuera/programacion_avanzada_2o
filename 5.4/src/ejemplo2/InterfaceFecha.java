package ejemplo2;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceFecha extends Remote
{
    int calcular(int año,int mes,int dia) throws RemoteException;
}
