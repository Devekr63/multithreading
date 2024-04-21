package org.example;

public class CustomThread extends Thread implements ParentCustom{
    @Override
    public void run() {
        for(int i=1; i<nums.size(); i++){
            System.out.println(
                    "{ "+currentThread().isAlive()+" } { "
                            +currentThread().getName()+" } { "
                            +getStringBuff(currentThread()
                                .getThreadGroup().toString())
                                .substring(10)
                            +" } { priority: "+currentThread().getPriority()+" } { "
                            +nums.get(i)
                    +" }"
            );
            System.out.println();
            try{
                Thread.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(int p) {
        this.setPriority(p);
    }

    private StringBuffer getStringBuff(String string){
        return new StringBuffer(string);
    }
}
