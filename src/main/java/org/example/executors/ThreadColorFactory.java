package org.example.executors;

import java.util.concurrent.ThreadFactory;

class ThreadColorFactory implements ThreadFactory {
    String threadName;

    public ThreadColorFactory(ThreadColor color){
        this.threadName = color.name();
    }
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName(threadName);
        return thread;
    }
}