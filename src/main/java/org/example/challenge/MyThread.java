package org.example.challenge;

public class MyThread extends Thread{
    @Override
    public void run() {
        for(int i=1; i<=5; i++){
            try{
                System.out.println("Printing even numbers: "+i*2);
                Thread.sleep(500);
            }catch (InterruptedException e){
                System.out.println("Thread was interrupted.");
                return;
            }
        }
    }
}
