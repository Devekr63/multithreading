package org.example.deadlock;

class Message {
    private String message;
    private boolean hasMessage;

    public synchronized String read() {
        while (!hasMessage) {
            try {
                wait();
            } catch (InterruptedException ie) {
                throw new RuntimeException(ie);
            }
        }
        hasMessage = false;
        notifyAll();
        return message;
    }

    public synchronized void write(String message) {
        while (hasMessage) {
            try {
                wait();
            } catch (InterruptedException ie) {
                throw new RuntimeException(ie);
            }
        }
        hasMessage = true;
        notifyAll();
        this.message = message;
    }

    public boolean hasMessage() {
        return hasMessage;
    }

}
