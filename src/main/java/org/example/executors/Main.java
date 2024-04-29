package org.example.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;


public class Main {

    public static void main(String[] args) {
        var blueService = Executors.newSingleThreadExecutor(
                new ThreadColorFactory(ThreadColor.ANSI_BLUE)
        );
        blueService.execute(Main::countDown);
        blueService.shutdown();

        boolean isDone = false;

        try {
            isDone = blueService.awaitTermination(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if(isDone) {
            System.out.println("Blue thread has completed it job.");
            var redService = Executors.newSingleThreadExecutor(
                    new ThreadColorFactory(ThreadColor.ANSI_RED)
            );
            redService.execute(Main::countDown);
            redService.shutdown();

            try {
                isDone = redService.awaitTermination(500, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if(isDone) {
                System.out.println("Red thread has completed it job.");
                var yellowService = Executors.newSingleThreadExecutor(
                        new ThreadColorFactory(ThreadColor.ANSI_YELLOW)
                );
                yellowService.execute(Main::countDown);
                yellowService.shutdown();

                try {
                    isDone = yellowService.awaitTermination(500, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if(isDone) System.out.println("All Threads have completed the task.");
            }
        }
    }
    public static void notmain(String[] args) {
        Thread red = new Thread(Main::countDown, ThreadColor.ANSI_RED.name());
        Thread blue = new Thread(Main::countDown, ThreadColor.ANSI_BLUE.name());
        Thread yellow = new Thread(Main::countDown, ThreadColor.ANSI_YELLOW.name());

        red.start();

        try {
            red.join();
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }

        blue.start();

        try {
            blue.join();
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }

        yellow.start();

        try {
            yellow.join();
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }
    }

    private static void countDown(){
        String threadName = Thread.currentThread().getName();
        var threadColor = ThreadColor.ANSI_RESET;

        try{
            threadColor = ThreadColor.valueOf(threadName.toUpperCase());
        }catch (IllegalArgumentException ignore){

        }

        String color = threadColor.color();
        for(int i=1; i<=20; i++){
            System.out.println(
                    color + " " + threadName.replace("ANSI_", "") + " " + i
            );
        }
    }
}
