package org.example.lock;

public class Main {
    public static void main(String[] args) {
        Message message = new Message();

        Thread reader = new Thread(new MessageReader(message), " 'Reader' ");
        Thread writer = new Thread(new MessageProducer(message), " 'Writer' ");

        writer.setUncaughtExceptionHandler((thread, e) -> {
            System.out.println("Writer had an exception. \n"+e);
            if(reader.isAlive()){
                System.out.println("Going to interrupt the reader");
                reader.interrupt();
            }
        });

        reader.setUncaughtExceptionHandler((thread, e) -> {
            System.out.println("Reader had an exception. \n"+e);
            if(writer.isAlive()){
                System.out.println("Going to interrupt the writer.");
                writer.interrupt();
            }
        });

        reader.start();
        writer.start();
    }
}