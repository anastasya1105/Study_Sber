package study.lesson2;

import java.util.*;

public class task2{
    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();
        Set<String> car2 = new TreeSet<>();
        List<Word> word = new ArrayList<>();
        CarScore.init(cars);
        for (int n = 0; n < cars.size(); n++) {
            String mod = cars.get(n).getModel();
            String typ = cars.get(n).getType();
            car2.add(mod);
            car2.add(typ);
        }
        for (String text : car2) {
            word.add(new Word(text));
        }
        for (String text : car2) {
            System.out.println(text);
        }
        System.out.println("--------");
        for (Word o: word) {
            System.out.println(o);
        }
        Collections.sort(word);
        System.out.println("--------");
        for (Word o: word) {
            System.out.println(o);
        }
    }
}
