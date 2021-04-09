package com.sberStudy.java.homeWork.pivovarova.lesson5.bank;

public class GeneratorCardNumber {
    public int generateNumberCard() {
        int i = (int) ((System.currentTimeMillis() & 0xfffffff) + (Math.random() * 1000_000_000));
        String str = String.valueOf(i);
        return Integer.parseInt(str.substring(0,8));
    }
    public int generatePin() {
        int i = (int) ((System.currentTimeMillis() & 0xfffffff) + (Math.random() * 1000_000_000));
        String str = String.valueOf(i);
        return Integer.parseInt(str.substring(0,4));
    }


}
