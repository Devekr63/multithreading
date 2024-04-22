package org.example.synchronization;

public class BankAccount {

    private double accountBalance;

    public BankAccount(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void deposit(double balance){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException ie){
            throw new RuntimeException(ie);
        }

        double currentBalance = accountBalance;
        accountBalance += balance;
        System.out.printf(
                "%nMoney deposited to the account. Previous: %.0f, added: %.0f, current balance: %.0f%n"
                , currentBalance, balance, accountBalance
        );
    }

    public void withdraw(double balance){
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
