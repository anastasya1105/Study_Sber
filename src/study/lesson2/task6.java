package study.lesson2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class task6 {
    public static void main(String[] args) throws Exception {
        Map<Integer, Car> car = new HashMap<>();
        List<String> list = new ArrayList<>();
        CarScore.init(car);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String number = reader.readLine();
            if (number.isEmpty()) {
                break;
            }
            list.add(number);

        }
        for (int i = 0; i < list.size(); i++) {
            int n = Integer.parseInt(list.get(i));
            if (n > 0 && n < car.size()) {
                System.out.println("Строка под номером - " + n + ": " + car.get(n));
            }
            else {
                System.out.println("Строка под номером - " + n + " отсутствует.");
            }
        }
    }
}
