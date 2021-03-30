package com.sberStudy.java.homeWork.pivovarova.lesson4;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

import java.util.Iterator;

public class CoreTests {
    private MyIterable<Object> iterable;

    @BeforeClass
    public static void startMessage() {
        System.out.println("Начало выполнения тестовых сценариев класса: " + CoreTests.class.getSimpleName());
    }

    @AfterClass
    public static void finishMessage() {
        System.out.println("Завершено выполнение тестовых сценариев класса: " + CoreTests.class.getSimpleName());
    }

    @Test(expected = NullPointerException.class)
    public void testException() {
        iterable.iterator().next();
    }

    @Test
    public void testHasNext() {
        iterable = new MyIterable<>(new Integer[]{1});
        Iterator<Object> iterator = iterable.iterator();
        assertTrue("Проверка наличия следующего элемента", iterator.hasNext());
        iterator.next();
        assertFalse("Проверка наличия следующего элемента", iterator.hasNext());
    }
}
