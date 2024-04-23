package org.example.challenge.deadlock;

import java.util.List;

public class OrderProducer implements Runnable{
    List<Order> orders;
    Warehouse warehouse;

    public OrderProducer(Warehouse warehouse, List<Order> orders) {
        this.warehouse = warehouse;
        this.orders = orders;
    }

    @Override
    public void run() {
        for (Order order:orders){
            try{
                Thread.sleep(500);
            }catch(InterruptedException ie){
                throw new RuntimeException(ie);
            }
            warehouse.receiveOrder(order);
        }
    }
}
