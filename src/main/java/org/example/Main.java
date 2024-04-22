package org.example;

import java.util.concurrent.TimeUnit;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        var currThread = Thread.currentThread();
        System.out.println(currThread.getClass().getName());
//        printThreadInfo(currThread);

        currThread.setName("Main Guy");
        currThread.setPriority(Thread.MIN_PRIORITY);
//        printThreadInfo(currThread);

        //Custom thread
        ParentCustom cusThread = new CustomThread();
        ParentCustom ct2  = new CustomThread();

        cusThread.update(2);
        ct2.update(10);
//        cusThread.joinWith(ct2);
        ct2.start();
        cusThread.start();

        //Passing lambda expression to thread
//        Runnable runnable = () -> {
//            for(int i=0; i<8; i++){
//                System.out.println(2);
//                try{
//                    TimeUnit.MILLISECONDS.sleep(200);
//                }catch (InterruptedException e){
//                    e.printStackTrace();
//                }
//            }
//        };
//
//        Thread myThread = new Thread(runnable);
//        System.out.println(myThread.getPriority());
//        myThread.start();
//
//        //Printing the main thread
//        for(int i=0; i<3; i++){
//            System.out.println(0);
//            try{
//                TimeUnit.SECONDS.sleep(1);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//        }
    }

    public static void printThreadInfo(Thread thread){
        System.out.println("_____________________");
        System.out.println("Thread ID: "+thread.getId());
        System.out.println("Thread name: "+thread.getName());
        System.out.println("Thread Priority: "+thread.getPriority());
        System.out.println("Thread State: "+thread.getState());
        System.out.println("Thread Group: "+thread.getThreadGroup());
        System.out.println("Thread is Alive: "+thread.isAlive());
        System.out.println("______________________");
    }
}