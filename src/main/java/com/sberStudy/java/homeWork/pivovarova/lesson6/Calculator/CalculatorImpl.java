package com.sberStudy.java.homeWork.pivovarova.lesson6.Calculator;

import java.math.BigInteger;

public class CalculatorImpl implements Calculator{
    @Override
    public BigInteger calc(int number) {
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= number; i++) {
            result = result.multiply(BigInteger.valueOf(i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
