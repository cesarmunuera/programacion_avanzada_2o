/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author cesar
 */
public class meter_ta extends Thread {
    float temperatura;
    Lock l1= new ReentrantLock();
    termometro t1;
    
    public meter_ta (float t, termometro t1){
        this.temperatura = t;
        this.t1 = t1;
    }
    
    public void run(){
        while(true){
            try{
                sleep(100 + (int)(200 *Math.random()));
                temperatura = (1 + (int)(29 *Math.random()));
                t1.insertart(temperatura);
            }catch (Exception e){
            }
        }
    }
    
            
    
}
