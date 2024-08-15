package org.example.multiThreading;

public class MultiThreading {
    public static void main(String[] args){

        //using Runnable interface
        Runnable counter = new Counter(10000);
        Thread counter1 = new Thread(counter);
        counter1.setName("Thread 1");
        Thread counter2 = new Thread(counter);
        counter2.setName("Thread 2");

        counter1.start();
        counter2.start();

        try{
            counter1.join();
            counter2.join();
        }catch (InterruptedException ie){
            System.out.println(ie.getMessage());
        }

        //using Thread class
//        MyTask counter3 = new MyTask(20000);
//        MyTask counter4 = new MyTask(15000);
//
//        counter3.start();
//        counter4.start();

        SyncCounter syncCount = new SyncCounter(10000);
        Thread syncCounter1 = new Thread(syncCount);
        syncCounter1.setName("Thread 3");

        Thread syncCounter2 = new Thread(syncCount);
        syncCounter2.setName("Thread 4");


        syncCounter1.start();
        syncCounter2.start();

        try{
            syncCounter1.join();
            syncCounter2.join();
        }catch(InterruptedException ie){
            System.out.println(ie.getMessage());
        }

        System.out.println(syncCount.getCount());
    }
}

class Counter implements Runnable{
    private int count;
    private int counter;

    public Counter(int counter){
        this.counter = counter;
    }

    @Override
    public void run(){
        for(int i=0; i<counter; i++){
            count++;
        }
        System.out.println("Count is : "+Thread.currentThread().getName()+" "+count);
    }
}

class SyncCounter implements Runnable{
    private int count;
    private int counter;

    public SyncCounter(int counter){
        this.counter = counter;
    }

    @Override
    public void run(){
        for(int i=0; i<counter; i++){
            synchronized (this){
                this.count++;
            }
        }
        synchronized (this){
            System.out.println("Count is : "+Thread.currentThread().getName()+" "+count);
        }
    }

    public synchronized int getCount(){
        return this.count;
    }
}

class MyTask extends Thread{
    static int count;
    private int steps;

    public MyTask(int steps){
        this.steps = steps;
    }

    @Override
    public void run() {
        for(int i=0; i<steps; i++){
            count++;
        }
        System.out.println(Thread.currentThread().getName()+" : "+count);
    }
}
