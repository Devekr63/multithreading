package org.example.cached_data;

public class CachedData {
//    private boolean flag = false;
    //shared data can be cached locally for a thread.

    //volatile keyword avoid caching locally of the shared data for the thread.
    private volatile boolean flag = false;

    public void toggleFlag(){
        flag = !flag;
    }

    public boolean isReady(){
        return flag;
    }
    public static void main(String[] args) {
        CachedData cachedDataExample = new CachedData();

        Thread writerThread = new Thread(()->{
            try{
                Thread.sleep(100);
            }catch (InterruptedException ie){
                ie.printStackTrace();
            }
            cachedDataExample.toggleFlag();
            System.out.println("A. Flag set to : "+cachedDataExample.isReady());
        });

        Thread readerThread = new Thread(()->{
            while(!cachedDataExample.isReady()){}
            System.out.println("B. Flag set to : "+cachedDataExample.isReady());
        });

        writerThread.start();
        readerThread.start();
    }
}
