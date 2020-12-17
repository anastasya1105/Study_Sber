package study.lesson2;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class task5 {
    public static void main(String[] args) {
        List<Car> car = new LinkedList<>();
        CarScore.init(car);
        for (Car car1 : car) {
            System.out.println(car1);
        }
        System.out.println("-----------------------");
        for (Car s : new ReverseIterator<Car>(car)) {
            System.out.println(s);
        }
    }
}
