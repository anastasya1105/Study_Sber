package com.sberStudy.java.homeWork.pivovarova.lesson2;

import java.util.*;

public class task1 {
    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();
        Set<String> car2 = new HashSet<>();
        Set<String> car3 = new HashSet<>();
        Set<String> car4 = new HashSet<>();
        CarScore.init(cars);
        for (int n = 0; n < cars.size(); n++) {
            String mod = cars.get(n).getModel();
            String typ = cars.get(n).getType();
            car2.add(mod);
            car2.add(typ);
            car3.add(mod);
            car4.add(typ);
        }
        System.out.println("В каталоге парка машин представленно: " + car2.size() + " слов. "+ car3.size() + " слова обозначают марки машин и " + car4.size() + " тип кузова.");

    }
}
