package study.lesson1.task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class convert {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int celsius = Integer.parseInt(reader.readLine());
        double fahrenheit = (9.0 / 5.0 ) * celsius + 32;
        System.out.println(fahrenheit);
    }
}
