package org.example.practice;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Runnable counter1 = new Counter(10_000_000);
        Thread t1 = new Thread(counter1);
        Thread t2 = new Thread(counter1);

//        t1.start();
//        t2.start();

        Thread t3 = new Thread(() ->{
            System.out.println(Thread.currentThread().getName()+" is printing 10 dots.");
            for(int i=0; i<10; i++){
                System.out.println(" .");
                try{
                    Thread.sleep(500);
                }catch(InterruptedException ie){
                    System.out.println(ie.getMessage());
                }
            }
        });

//        t3.start();

        BankAccount acc = new BankAccount(1_000_000);

        Thread w1 = new Thread(() -> {
            acc.withdraw(10_000);
        });

        Thread w2 = new Thread(() -> {
            acc.withdraw(100_000);
        });

        Thread d1 = new Thread(() -> {
           acc.deposit(10_000);
        });

        Thread d2 = new Thread(() -> {
           acc.deposit(50_000);
        });

        w1.start();
        w2.start();
        d1.start();
        d2.start();
    }
}

class Counter implements Runnable{
    int counter;
    int count=0;

    public Counter(int counter){
        this.counter = counter;
    }

    public void run(){
        for (int i = 0; i < counter; i++) {
            synchronized (this) {
                count++;
            }
        }
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " - " + count);
        }
    }
}

class BankAccount{
    double balance;

    public BankAccount(double balance){
        this.balance = balance;
    }

    public synchronized void withdraw(double amount){
        System.out.println("Withdrawing from account.");
        try{
            TimeUnit.MILLISECONDS.sleep(500);
        }catch(InterruptedException ie){
            System.out.println(ie.getMessage());
        }

        balance -=  amount;
        System.out.println("Current Balance is: "+balance);
    }

    public void deposit(double amount){
        System.out.println("Depositing to the account.");
        try{
            TimeUnit.MILLISECONDS.sleep(500);
        }catch(InterruptedException ie){
            System.out.println(ie.getMessage());
        }

        balance += amount;
        System.out.println("Current Balance is: "+balance);
    }
}
