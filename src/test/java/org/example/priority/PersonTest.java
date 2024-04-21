package org.example.priority;


import org.example.CustomThread;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;


public class PersonTest {

    @Test
    public void testPersonPriority() throws InterruptedException {
        Person p1 = new Person("Devendra", 100);
        Person p2 = new Person("Deepak", 101);

        int[] resArr = {1,4,9,16,25};

        p1.setPriority(10);
        p2.setPriority(1);

        p2.join();
        p1.join();

//        p2.start();
//        p1.start();

//        p2.squareArr();
//        p1.squareArr();

        p2.run();
        p1.run();
//        assertEquals("Devendra", p1.getPersonName());
        for(int x:Person.arr){
            System.out.println(x);
        }
        assertEquals(resArr[1], Person.arr[1]);
    }

    @Test
    public void testCustomThread(){
        CustomThread ct1 = new CustomThread();
        CustomThread ct2 = new CustomThread();

        ct1.start();
        ct2.start();
    }
}