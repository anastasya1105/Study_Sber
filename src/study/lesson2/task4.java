package study.lesson2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class task4 {
    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();
        CarScore.init(cars);
        for (int c = 0; c < cars.size(); c++) {
            System.out.println(cars.get(c));
        }
        System.out.println("-----------------------------");
        for (int n = cars.size() - 1; n >= 0; n--) {
            System.out.println(cars.get(n));
        }

    }
}
