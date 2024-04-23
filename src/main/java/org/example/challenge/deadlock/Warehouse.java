package org.example.challenge.deadlock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Warehouse {
    private HashMap<ShoeType, Integer> inventory;
    private List<Order> ordersList = new ArrayList<>();

    public Warehouse(HashMap<ShoeType, Integer> inventory) {
        this.inventory = inventory;
    }

    public synchronized void receiveOrder(Order order){
        while(ordersList.size() > 99){
            try{
                wait();
            }catch(InterruptedException ie){
                throw new RuntimeException(ie);
            }
        }
        ShoeType type = order.shoeType();
        if(inventory.get(type) - order.quantity() >= 0){
            inventory.put(type, inventory.get(type)-order.quantity());
            ordersList.add(order);
        }
        notifyAll();
        System.out.println("\nNew order received. "+order+"\n");
    }

    public synchronized void fulfillOrder(){
        while(ordersList.isEmpty()){
            try{
                wait();
            }catch(InterruptedException ie){
                throw new RuntimeException(ie);
            }
        }
        Order fulfilledOrder = ordersList.get(0);
        ordersList.remove(0);

        notifyAll();
        System.out.println("Order fulfilled. "+fulfilledOrder);
    }
}
