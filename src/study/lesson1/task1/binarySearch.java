package study.lesson1.task1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class binarySearch {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int key = Integer.parseInt(reader.readLine());
        int[] massiv = new int[7];
        for (int k = 0; k < massiv.length; k++) {
            int l = Integer.parseInt(reader.readLine());
            massiv[k] = l;
        }
        int[] mas = Arrays.copyOf(massiv, massiv.length);
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


        int min = 0;
        int max = massiv.length - 1;
        recursiveBinarySearch(key, massiv, min, max, mas);

    }
    public static void recursiveBinarySearch(int key, int [] massiv, int min, int max, int[] mas){
        int c = (max + min) / 2;
        int d = massiv[c];
        while ((d != key) && (min <= max)) {
            System.out.println(key + "   "  + c + "   " + d + "   " + max + "    " + min);
            if (key < d) {
                max = c - 1;
            }
            if (key > d) {
                min = c + 1;
            }
            c = (min + max) / 2;
            d = massiv[c];
        }
        if (key == d) {
            int index = - 1;
            for (int u = 0; u < mas.length; u++) {
                if (mas[u] == d) {
                    index = u;
                }
            }
            System.out.println(key + " является " +  (index + 1) + " элементом в массиве");
        }
        else {
            System.out.println("Элемент не найден в массиве");
        }
    }
}
