package org.example.lock;

import java.util.Random;

class MessageProducer implements Runnable {
    private Message outgoingMessage;

    public MessageProducer(Message outgoinMessage) {
        this.outgoingMessage = outgoinMessage;
    }

    private final String text = """
            Baa, Baa, Black Sheep,
            Have you any wool?
            Yes sir, Yes sir;
            Three bags full
            """;

    @Override
    public void run() {
        Random random = new Random();
        String[] messages = text.split("\n");

        for (String message : messages) {
            outgoingMessage.write(message);
            try {
                Thread.sleep(random.nextInt(500, 2000));
            } catch (InterruptedException ie) {
                throw new RuntimeException(ie);
            }
        }
        outgoingMessage.write("Finished");
    }
}
