package com.sberStudy.java.homeWork.pivovarova.lesson6.Calculator;

import java.math.BigInteger;

public interface Calculator {
    /*
     * Расчет факториала числа.
     * @param number
     */

    @Metric
    @Cache
    BigInteger calc (int number);

}
