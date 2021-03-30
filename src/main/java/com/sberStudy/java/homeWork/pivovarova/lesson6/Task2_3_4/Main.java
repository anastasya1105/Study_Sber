package com.sberStudy.java.homeWork.pivovarova.lesson6.Task2_3_4;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        Example example = new Example(2568, 28, "Ivan", "Russia", "Rostov-on-Don");

        task2(example);
        task3(example);
        try {
            task4(example);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    /*
    Задача 2:
Вывести на консоль все методы класса, включая все родительские методы (включая приватные)
     */
    public static void task2(Object example) {
        Class aClass = example.getClass();
        System.out.println("Задача 2");
        Set<Method> setMethod = new HashSet<>();
        for (Method m : aClass.getMethods()) {
            setMethod.add(m);
        }
        for (Method m : aClass.getDeclaredMethods()) {
            m.setAccessible(true);
            setMethod.add(m);
        }
        for (Method m : setMethod) {
            System.out.println(m);
        }
    }
    /*
    Задача 3:
Вывести все геттеры класса
     */
    public static void task3(Object example) {
        Class aClass = example.getClass();
        System.out.println("Задача 3");
        Arrays.stream(aClass.getMethods()).filter(t -> t.getName().startsWith("get")).forEach(System.out::println);
    }

    /*
    Задача 4:
Проверить что все String константы имеют значение = их имени
public static final String MONDAY = "MONDAY";
     */
    public static void task4(Object example) throws IllegalAccessException {
        Class aClass = example.getClass();
        System.out.println("Задача 4");
        List<String> mistakes = new ArrayList();
        List<Field> list = Arrays.stream(aClass.getDeclaredFields())
                            .filter(field -> field.getType().equals(String.class))
                            .filter(field -> Modifier.isFinal(field.getModifiers()))
                            .collect(Collectors.toList());
        for (Field f : list) {
            f.setAccessible(true);
            if (!f.getName().equals(f.get(example))) {
                mistakes.add(f.getName());
            }
        }
        if (mistakes.size()!=0) {
            for (String str : mistakes) {
            System.out.println("значение константы " + str + " не совпадает с ее именем");
            }
        }
        else {
            System.out.println("все String константы имеют значение = их имени");
        }

    }
}
