/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

/**
 *
 * @author cesar
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        termometro t1 = new termometro(0);
        meter_ta m = new meter_ta(0, t1);
        sacar_ta s = new sacar_ta(0, t1);

        m.start();
        s.start();
    }

}
