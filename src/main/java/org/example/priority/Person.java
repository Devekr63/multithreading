package org.example.priority;

import java.util.Arrays;

public class Person extends Thread {

    static int[] arr = {1,2,3,4,5};
    private String name;
    private int id;

    public Person(ThreadGroup group, String name, String name1, int id) {
        super(group, name);
        this.name = name1;
        this.id = id;
    }

    public Person(String name, int id){
        super();
        this.name = name;
        this.id = id;

    }

    @Override
    public void run() {
        System.out.println(name+" : "+id);
        squareArr();
    }



    public String getPersonName() {
        return name;
    }


    public int getPersonId() {
        return id;
    }

    public void squareArr(){
        for(int i=0; i<arr.length; i++){
            arr[i] = arr[i]*arr[i];
        }
    }
}
