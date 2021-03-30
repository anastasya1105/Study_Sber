package com.sberStudy.java.homeWork.pivovarova.lesson4;

import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class ParametrizedTest {
    private MyIterable<Object> iterable;

    @BeforeClass
    public static void startMessage() {
        System.out.println("Начало выполнения тестовых сценариев класса: " + ParametrizedTest.class.getSimpleName());
    }

    @AfterClass
    public static void finishMessage() {
        System.out.println("Завершено выполнение тестовых сценариев класса: " + ParametrizedTest.class.getSimpleName());
    }

    @Parameterized.Parameter
    public Object exrResult;

    @Parameterized.Parameter(1)
    public Object[] testArr;

    @Parameterized.Parameters
    public static Iterable<Object[]> testParams() {
        return Arrays.asList(new Object[][]{
                {1, new Object[]{1, 2, 3}},
                {"Test1", new Object[]{"Test1", "Test2"}},
                {'1', new Object[]{'1', '2'}}
        });

    }

    @Test
    public void testParametrizeNext() {
        iterable = new MyIterable<>(testArr);
        assertEquals("Выполнение параметризованной сверки", exrResult, iterable.iterator().next());
    }
}
