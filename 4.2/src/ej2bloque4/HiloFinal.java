/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ej2bloque4;

import java.math.BigInteger;

/**
 *
 * @author
 */
public class HiloFinal extends Thread {

    private Result r1, r2, r3, r4, r5;

    public HiloFinal(Result r1, Result r2, Result r3, Result r4, Result r5) {
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.r4 = r4;
        this.r5 = r5;
    }

    @Override
    public void run() {
        System.out.println("Executing final thread ... ");
        try {
            System.out.println("......");
            BigInteger result = sum();

            System.out.println("The final result is: " + result + " ");
        } catch (Exception e) {
        }
    }

    private BigInteger sum() {
        System.out.println("funcion de sum");
        BigInteger rb1 = r1.getSuma();
        System.out.println("suma del resultado 1");
        BigInteger rb2 = r2.getSuma();
        System.out.println("suma del resultado 2");
        BigInteger rb3 = r3.getSuma();
        System.out.println("suma del resultado 3");
        BigInteger rb4 = r4.getSuma();
        System.out.println("suma del resultado 4");
        BigInteger rb5 = r5.getSuma();
        System.out.println("suma del resultado 5");

        rb1 = rb1.add(rb2).add(rb5).add(rb4).add(rb3);
        System.out.println("final de la funcion de sum");
        return rb1;

    }
}
