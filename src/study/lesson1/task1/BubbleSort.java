package study.lesson1.task1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BubbleSort {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] massiv = new int[5];
        for (int k = 0; k < massiv.length; k++) {
            int l = Integer.parseInt(reader.readLine());
            massiv[k] = l;
        }
        int i;
        boolean del = true;
        while (del) {
            del = false;
            int c;
            for (i = 1; i < massiv.length; i++) {
                int a = massiv[i];
                int b = massiv[i - 1];
                if (a < b) {
                    c = massiv[i - 1];
                    massiv[i - 1] = massiv[i];
                    massiv[i] = c;
                    del = true;
                }
            }
        }
        for (int n = 0; n < massiv.length; n++)
            System.out.println(massiv[n]);
    }
}
