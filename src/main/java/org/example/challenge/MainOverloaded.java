package org.example.challenge;

public class MainOverloaded {
    public static void main(String[] args) {
        main(2);
    }

    public static void main(int x){
        System.out.println("Overloaded main method."+2);
    }
}

