package ej2bloque4;

import java.math.BigInteger;

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
        System.out.println("Starting final sum() function");
        BigInteger res1 = r1.getSuma();
        System.out.println("The sum of 1st result is: " + res1);
        BigInteger res2 = r2.getSuma();
        System.out.println("The sum of 2nd result is: " + res2);
        BigInteger res3 = r3.getSuma();
        System.out.println("The sum of 3rd result is: " + res3);
        BigInteger res4 = r4.getSuma();
        System.out.println("The sum of 4th result is: " + res4);
        BigInteger res5 = r5.getSuma();
        System.out.println("The sum of 5th result is: " + res5);

        BigInteger res = res1.add(res2).add(res3).add(res4).add(res5);
        System.out.println("End sum() function");
        return res;

    }
}
