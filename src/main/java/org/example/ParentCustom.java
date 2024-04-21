package org.example;

import java.util.Arrays;
import java.util.List;

public interface ParentCustom {
    List<Integer> nums = Arrays.asList(1,2,3,4,5,6);
    void run();

    void start();

    void update(int p);
}
