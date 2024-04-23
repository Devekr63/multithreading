package org.example.revise.sync;

public class Item {
    private int quantity;

    final Object lockQuantity = new Object();

    public Item(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void add(int amount){
        try{
            Thread.sleep(100);
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }
        synchronized(lockQuantity) {
            quantity += amount;
            System.out.printf("%n%d amount is added. New quantity is %d%n",amount ,quantity);
        }
    }

    public void remove(int amount){
        try{
            Thread.sleep(100);
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }
        synchronized (lockQuantity){
            if(quantity >= 0){
                quantity -= amount;
                System.out.printf("%n%d amount is remove. New quantity is %d%n",amount ,quantity);
            }
            else {
                System.out.println("Quantity is zero.");
            }
        }
    }
}
