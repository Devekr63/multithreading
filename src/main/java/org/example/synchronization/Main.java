package org.example.synchronization;

public class Main {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(100000.0);

        Thread t1 = new Thread(() -> {
           bankAccount.deposit(20000.0);
        });

        Thread t2 = new Thread(()->{
            bankAccount.withdraw(30000.0);
        });

        Thread t3 = new Thread(() -> {
            bankAccount.deposit(15000.0);
        });

        t1.start();
        t2.start();
        t3.start();

        try{
            t1.join();
            t2.join();
            t3.join();
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }

        System.out.println("Balance in the account: "+bankAccount.getAccountBalance());
    }
}
