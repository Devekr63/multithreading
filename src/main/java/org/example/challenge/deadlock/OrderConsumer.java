package org.example.challenge.deadlock;

public class OrderConsumer implements Runnable{
    Warehouse warehouse;

    int orderConsumer;

    private String name;

    public OrderConsumer(Warehouse warehouse, String name) {
        this.name = name;
        this.warehouse = warehouse;
    }


    @Override
    public void run() {


        while(orderConsumer != 5){
            try{
                Thread.sleep(500);
            }catch(InterruptedException ie){
                throw new RuntimeException(ie);
            }
            warehouse.fulfillOrder();
            orderConsumer++;
            System.out.println("Fulfilled by: "+name);
        }
    }
}
