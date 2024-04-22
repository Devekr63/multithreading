package org.example.synchronization;

public class Main {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount("Devendra",100000.0);

        Thread t1 = new Thread(() -> {
           bankAccount.withdraw(20000.0);
        });

        Thread t2 = new Thread(()->{
            bankAccount.withdraw(30000.0);
        });

        Thread t3 = new Thread(() -> {
            bankAccount.setName("Devendra Kumar");
        });

        Thread t4 = new Thread(() -> {
            bankAccount.deposit(15000.0);
        });

        //started all the threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        //threads joins main thread
        //until all tasks by threads(t1,t2,t3) are not completed, till then main thread is waiting.
        try{
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }

        System.out.println("Balance in the account: "+bankAccount.getAccountBalance());
    }
}
