package org.example.challenge.deadlock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Order> orders = new ArrayList<>();
        HashMap<ShoeType, Integer> inventory = new HashMap<>();

        inventory.put(ShoeType.RUNNING, 1000);
        inventory.put(ShoeType.LOAFER,1200);
        inventory.put(ShoeType.SPORTS, 1250);
        inventory.put(ShoeType.BASKETBALL, 900);
        inventory.put(ShoeType.SNEAKER, 1000);

        Warehouse warehouse = new Warehouse(inventory);

        Thread shoeProducer = new Thread(new OrderProducer(warehouse

                ,Arrays.asList( new Order(100, ShoeType.RUNNING, 100),
                        new Order(101, ShoeType.LOAFER, 150),
                        new Order(120, ShoeType.SNEAKER, 200),
                        new Order(150, ShoeType.BASKETBALL, 301),
                        new Order(150, ShoeType.BASKETBALL, 100),
                        new Order(150, ShoeType.SNEAKER, 301),
                        new Order(150, ShoeType.SPORTS, 200),
                        new Order(150, ShoeType.RUNNING, 301),
                        new Order(150, ShoeType.LOAFER, 300),
                        new Order(150, ShoeType.SPORTS, 301)
                ))

                ,"producer");
        Thread shoeConsumer1 = new Thread(new OrderConsumer(warehouse, "CON-01"),"consumer");
        Thread shoeConsumer2 = new Thread(new OrderConsumer(warehouse, "CON-02"),"consumer");

        shoeProducer.start();
        shoeConsumer1.start();
        shoeConsumer2.start();

//        try{
//            shoeConsumer1.join();
//            shoeConsumer2.join();
//            shoeProducer.join();
//        }catch(InterruptedException ie){
//            throw new RuntimeException(ie);
//        }
    }
}
