package study.lesson2;

import java.util.*;

public class task3 {
    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();
        Set<String> car2 = new HashSet<>();
        Map<String, Integer> car3 = new HashMap<>();
        CarScore.init(cars);
        for (int n = 0; n < cars.size(); n++) {
            String mod = cars.get(n).getModel();
            String typ = cars.get(n).getType();
            car2.add(mod);
            car2.add(typ);
        }

        for (String mod2 : car2) {
            int a = 0;
            for (int c = 0; c < cars.size(); c++) {
                String mod3 = cars.get(c).getModel();
                String mod4 = cars.get(c).getType();

                if (mod2.equals(mod3)) {
                    a++;
                }
                if (mod2.equals(mod4)) {
                    a++;
                }
            }
            car3.put(mod2, a);
        } 

        for (Map.Entry<String, Integer> pair : car3.entrySet())
        {
            String key = pair.getKey();
            int value = pair.getValue();
            System.out.println("слово " + key + " встречается " + value + " раз");
        }
    }
}

