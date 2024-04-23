package org.example.deadlock;

import java.util.Random;

class MessageReader implements Runnable {
    private Message incomingMessage;

    public MessageReader(Message incomingMessage) {
        this.incomingMessage = incomingMessage;
    }

    public void run() {
        Random random = new Random();
        String latestMessage = "";
        do {
            try {
                Thread.sleep(random.nextInt(500, 2000));
            } catch (InterruptedException ie) {
                throw new RuntimeException(ie);
            }
            latestMessage = incomingMessage.read();
            System.out.println("Message: " + latestMessage);
        } while (!latestMessage.equals("Finished"));
    }
}
