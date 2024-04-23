package org.example.revise.sync;

public class BankManager {
    public static void main(String[] args) {
        BankAccount account = new BankAccount( 100000.0, "Devendra");

        System.out.println("Devendra bank account is created. Balance in account is: "+account.getBalance());

        Thread t1 = new Thread(()-> account.deposit(10000.0));
        Thread t2 = new Thread(() -> account.withdraw(50000.0));
        Thread t3 = new Thread(() -> account.withdraw(20000.0));
        Thread t4 = new Thread(() -> account.deposit(15000.0));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try{
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }

        System.out.println("\nDevendra is done banking. Account balance is: "+ account.getBalance());
    }
}
