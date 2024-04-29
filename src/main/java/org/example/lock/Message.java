package org.example.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Message {
    private String message;
    private boolean hasMessage;

    private final Lock lock = new ReentrantLock();

    public String read() {

//        lock.lock();

        if (lock.tryLock()) {
            try {
                while (!hasMessage) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ie) {
                        throw new RuntimeException(ie);
                    }
                }
                hasMessage = false;
            } finally {
                lock.unlock();
            }
        }else{
            System.out.println("** read is blocked. "+lock);
            hasMessage = false;
        }
        return message;
    }

    public void write(String message) {
        if(lock.tryLock()) {
            try {
                while (hasMessage) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ie) {
                        throw new RuntimeException(ie);
                    }
                }
                hasMessage = true;
            } finally {
                lock.unlock();
            }
        }else {
            System.out.println("** write is blocked. "+lock);
            hasMessage = true;
        }
        this.message = message;
    }

    public boolean hasMessage() {
        return hasMessage;
    }

}
