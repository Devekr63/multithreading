package org.example.runnningThreads;

import java.util.concurrent.TimeUnit;

public class RunningThreads {
    public static void main(String[] args){
        System.out.println("Main Thread is running.");
        try {
            System.out.println("Thread main paused for .5 seconds.");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //passing lambda expression in the thread constructor
        Thread myThread = new Thread(() -> {
            String tname = Thread.currentThread().getName();
            System.out.println(Thread.currentThread().getName() +" thread will print 10 dots.");
            for(int i=0; i<10; i++){
                System.out.print(". ");
                try{
                    TimeUnit.MILLISECONDS.sleep(500);
                }catch(InterruptedException e){
                    System.out.println("\nWhoops! "+tname+" got interrupted.");
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            System.out.println("\n"+tname+" completed.");
        });

        myThread.setName("** My Thread **");

        Thread installationThread = new Thread(() -> {
            try{
                for(int i=0; i<3; i++){
                    System.out.println("Installing.... Step "+(i+1));
                    Thread.sleep(1000);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }, "installThread");

        Thread monitor = new Thread(() -> {
            long now = System.currentTimeMillis();
            while(myThread.isAlive()){
                try {
                    Thread.sleep(1000);
                    if(System.currentTimeMillis()-now > 8000){
                        myThread.interrupt();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println(myThread.getName()+" starting.");
        myThread.start();
        monitor.start();


        try {
            myThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(!myThread.isInterrupted()){
            installationThread.start();
        }else{
            System.out.println("MyThread was interrupted..."+installationThread.getName()+"can't run.");
        }
    }
}
