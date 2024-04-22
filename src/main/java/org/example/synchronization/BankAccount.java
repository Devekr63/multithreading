package org.example.synchronization;

public class BankAccount {

    private Double accountBalance;
    private String name;

    private final Object lockName = new Object();
    private final Object lockAccountBalance = new Object();


    public BankAccount(String name, double accountBalance) {
        this.name = name;
        this.accountBalance = accountBalance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        synchronized(lockName){
            this.name = name;
            System.out.printf("Updated name of the account: %s",name);
        }

    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public  void deposit(double balance){
        try{
            System.out.println("\nDepositing: adding balance to the account.");
            Thread.sleep(1000);
        }catch (InterruptedException ie){
            throw new RuntimeException(ie);
        }

        //synchronising all threads that update the currentBalance.
        //a thread locks the shared variable before execution.
        synchronized(lockAccountBalance) {
            double currentBalance = accountBalance;
            accountBalance += balance;
            System.out.printf(
                    "%nMoney deposited to the account. Previous: %.0f, added: %.0f, current balance: %.0f%n"
                    , currentBalance, balance, accountBalance
            );
            promoDolors(100);
        }
    }

    public void promoDolors(double amount){
        synchronized (lockAccountBalance) {
            if (accountBalance >= 50000.0) {
                accountBalance += amount;
            }
        }
    }

    public synchronized void withdraw(double balance){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException ie){
            throw new RuntimeException(ie);
        }

        double currentBalance = accountBalance;
        if(balance <= accountBalance){
            accountBalance -= balance;
            System.out.printf(
                    "%nAmount withdrawn from the account. Previous: %.0f, added: %.0f, current balance: %.0f%n"
                    , currentBalance, balance, accountBalance
            );
        }else{
            System.out.println("Invalid amount entered.");
        }
    }
}
