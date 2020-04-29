/*
 * La clase Productor define hilos que envían mensajes a un buzón de mensajes.
 * Los mensajes constan de un prefijo String y un sufijo que es un entero del 1 al 5
 * El prefijo, el número de mensajes a escribir y el buzón donde hacerlo,
 *  se reciben como parámetros en el constructor.
 * Entre mensaje y mensaje, esperan un tiempo aleatorio entre 0.5 y 1 seg.
 */
package PECL1;

public class Productor extends Thread {

    private String prefijo;
    private int numMensajes;
    private Buzon miBuzon;
    private Resultado resultado;

    public Productor(String prefijo, int n, Buzon buzon,Resultado resultado) {
        this.prefijo = prefijo;
        numMensajes = n;
        miBuzon = buzon;
        this.resultado = resultado;
    }

    public void run() {
        int num = 0;
        String numS = "";
        for (int i = 1; i <= numMensajes; i++) {
            try {
                num = (int) (Math.random() * 20);
                
                numS = num + "";
                sleep((int) (200 + 600 * Math.random()));
            } catch (InterruptedException e) {
            }
            miBuzon.enviaMensaje(numS, prefijo);
            resultado.addResultado(num);
        }
    }

}
