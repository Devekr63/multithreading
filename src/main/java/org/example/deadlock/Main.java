package org.example.deadlock;

public class Main {
    public static void main(String[] args) {
        Message message = new Message();

        Thread reader = new Thread(new MessageProducer(message));
        Thread writer = new Thread(new MessageReader(message));

        reader.start();
        writer.start();
    }
}