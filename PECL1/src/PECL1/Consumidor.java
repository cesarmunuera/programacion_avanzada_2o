/*
 * La clase Consumidor define hilos que leen mensajes de un buzón de mensajes
 * y los muestran por pantalla.
 * El buzón y el número de mensajes, los reciben como parámetros del constructor
 * antes de terminar.
 * Entre lectura y lectura, esperan un tiempo aleatorio entre 0.5 y 1 seg.
 */
package PECL1;

public class Consumidor extends Thread
{
    private int numMensajes;
    private Buzon miBuzon;
    private String nombre;
    private Resultado resultado;

    public Consumidor(Buzon miBuzon, String nombre, Resultado resultado)
    {
        this.miBuzon=miBuzon;
        this.nombre=nombre;
        this.resultado=resultado;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                sleep((int)(300 + 400*Math.random()));
            } catch(InterruptedException e){ }
                System.out.println(nombre+" ha leído "+ miBuzon.recibeMensaje()+" , resultado: "+resultado.getResultado());
        }
    }
}
