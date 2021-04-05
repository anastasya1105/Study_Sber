package com.sberStudy.java.homeWork.pivovarova.lesson16;


import java.util.ArrayList;
import java.util.List;

public class CalculatorFibonacci implements CalculatorFi {
    private List<Integer> fibonacciList;

    public CalculatorFibonacci() {
        this.fibonacciList = fibonacciList;
    }

    public List<Integer> fibonachi(int n) {
        return calculationFibonacci(n);
    }
    private List calculationFibonacci(int n){
        fibonacciList = new ArrayList();
        int number;
        if (n == 0) {
            fibonacciList.add(0);
        }
        else {
            fibonacciList.add(0);
            fibonacciList.add(1);
            for (int i = 2; i <= n; i++) {
                number = fibonacciList.get(i-2) + fibonacciList.get(i - 1);
                fibonacciList.add(number);
            }
        }

        return fibonacciList;
    }
}
