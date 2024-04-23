package org.example.revise;

public class BankAccount {
    private double accountBalance;

    private String name;

    private final Object lockAccountBalance = new Object();

    public BankAccount(double accountBalance, String name){
        this.name = name;
        this.accountBalance = accountBalance;
    }

    public double getBalance(){
        return accountBalance;
    }

    public void deposit(double amount){
        try{
            Thread.sleep(100);
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }

        synchronized (lockAccountBalance){
            if(accountBalance >= amount){
                accountBalance += amount;
                System.out.printf("%n%.0f rupees has been deposited to the %s account.%nBalance available: %.0f%n", amount, name, accountBalance);
            }else{
                System.out.println("Account is insufficient. Please withdraw a lower amount.");
            }
        }
    }

    public void withdraw(double amount){
        try{
            Thread.sleep(100);
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }

        synchronized (lockAccountBalance){
            accountBalance -= amount;
            System.out.printf("%n%.0f rupees has debited from the %s account.%nBalance available: %.0f%n", amount, name, accountBalance);
        }
    }
}
