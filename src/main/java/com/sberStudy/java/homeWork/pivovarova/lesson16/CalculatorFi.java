package com.sberStudy.java.homeWork.pivovarova.lesson16;

import java.util.List;

public interface CalculatorFi {

    @Cachable(db = MySQLite.class)
    public List<Integer> fibonachi(int n);
}
