package com.sberStudy.java.homeWork.pivovarova.lesson6;

import com.sberStudy.java.homeWork.pivovarova.lesson6.Calculator.Calculator;
import com.sberStudy.java.homeWork.pivovarova.lesson6.Calculator.CalculatorHandler;
import com.sberStudy.java.homeWork.pivovarova.lesson6.Calculator.CalculatorImpl;
import com.sberStudy.java.homeWork.pivovarova.lesson6.Calculator.PerformanceProxy;
import org.junit.Test;

import java.lang.reflect.Proxy;

public class cacheCalculator {

    @Test
    public void cacheMetricTest() {
        Calculator calculator= PerformanceProxy.cache(new CalculatorImpl());
        System.out.println(calculator.calc(3));
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(5));
        System.out.println(calculator.calc(6));
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(1));

    }
    @Test
    public void cacheTest() {
        Calculator calculator = CalculatorHandler.cache(new CalculatorImpl());
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(5));
        System.out.println(calculator.calc(14));
        System.out.println(calculator.calc(18));
        System.out.println(calculator.calc(5));
        System.out.println(calculator.calc(14));
    }
}
