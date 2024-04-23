package org.example.revise.sync;

public class ItemManager {
    public static void main(String[] args) {
        Item item = new Item(100);

        Thread t1 = new Thread( () -> item.add(100) );
        Thread t2 = new Thread(() -> item.remove(50));
        Thread t3 = new Thread(() -> item.add(50));
        Thread t4 = new Thread(() -> item.remove(35));
        Thread t5 = new Thread(() -> item.add(150));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        try{
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }

        System.out.printf("Item final quantity: %d", item.getQuantity());
    }
}
