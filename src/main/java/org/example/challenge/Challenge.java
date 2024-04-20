package org.example.challenge;

public class Challenge {
    public static void main(String[] args){
        MyThread evenThread = new MyThread();

        Runnable runnable = () -> {
            for(int i=0; i<5; i++){
                try{
                    System.out.println("Printing odd numbers: "+(i*2+1));
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    System.out.println("Thread was interrupted.");
                    return;
                }
            }
        };

        Thread oddThread = new Thread(runnable);

        evenThread.start();
        oddThread.start();

        oddThread.interrupt();
    }
}
