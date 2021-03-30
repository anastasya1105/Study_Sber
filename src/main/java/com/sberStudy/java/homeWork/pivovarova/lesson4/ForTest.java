package com.sberStudy.java.homeWork.pivovarova.lesson4;

public class ForTest {

    public int forTest(int number) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (number <= 1)
            return 1;
        else
            return number * forTest(number - 1);
    }
}
