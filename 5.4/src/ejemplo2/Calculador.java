package ejemplo2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.Period;

public class Calculador extends UnicastRemoteObject implements InterfaceFecha {

    LocalDate fechaHoy;

    public Calculador() throws RemoteException {

        this.fechaHoy = LocalDate.now();

    }                 

    @Override
    public int calcular(int año, int mes, int dia) throws RemoteException // Implementación del método remoto
    {
        LocalDate fecha = LocalDate.of(año, mes, dia);
        Period respuestaP = Period.between(fecha, fechaHoy);
        int respuesta = respuestaP.getYears();
        return respuesta;
    }
}
