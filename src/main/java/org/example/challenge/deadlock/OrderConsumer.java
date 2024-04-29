package org.example.challenge.deadlock;

public class OrderConsumer implements Runnable{
    Warehouse warehouse;

    private boolean running=true;

    int orderConsumer;

    private String name;

    public OrderConsumer(Warehouse warehouse, String name) {
        this.name = name;
        this.warehouse = warehouse;
    }


    @Override
    public void run() {

        while(true){
            if(!running){
                System.out.println("Shutting Down.....");
                Thread.currentThread().interrupt();
            }
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

    public void shutdown(){
        running = true;
    }
}
