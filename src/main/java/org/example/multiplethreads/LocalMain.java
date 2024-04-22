package org.example.multiplethreads;

import java.util.concurrent.TimeUnit;

public class LocalMain {
    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch(TimeUnit.SECONDS);

        //Creating 3 new different Threads, of same stop watch: stopWatch.
        Thread green = new Thread(stopWatch::countDown, ThreadColor.ANSI_GREEN.name());
        Thread red = new Thread(
                () -> stopWatch.countDown(7),
                ThreadColor.ANSI_RED.name());
        Thread yellow = new Thread(stopWatch::countDown, ThreadColor.ANSI_YELLOW.name());

        green.start();
        red.start();
        yellow.start();
    }
}

class StopWatch {
    private TimeUnit timeUnit;

    private int i;

    public StopWatch(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;

    }

    public void countDown(){
        countDown(5);
    }

    public void countDown(int unitCount){
        String threadName = Thread.currentThread().getName();

        ThreadColor threadColor = ThreadColor.ANSI_RESET;

        try{
            threadColor = ThreadColor.valueOf(threadName);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }

        String color = threadColor.color();

        for(i=0; i<unitCount; i++){
            try{
                Thread.sleep(1000);
            }catch (InterruptedException ie){
                ie.printStackTrace();
            }

            System.out.printf("%s%s Thread : i = %d%n", color, threadName, i);
        }
    }
}
