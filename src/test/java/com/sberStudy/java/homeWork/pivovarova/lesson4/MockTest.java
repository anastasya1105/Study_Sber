package com.sberStudy.java.homeWork.pivovarova.lesson4;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class MockTest {

    @BeforeClass
    public static void startMessage() {
        System.out.println("Начало выполнения тестовых сценариев класса: " + MockTest.class.getSimpleName());
    }

    @AfterClass
    public static void finishMessage() {
        System.out.println("Завершено выполнение тестовых сценариев класса: " + MockTest.class.getSimpleName());
    }

    @Mock
    private MyIterable<Object> myIterable;

    @Mock
    private Iterator<Object> mockIterator;

    @Test
    public void testNext() {
        when(myIterable.iterator()).thenReturn(mockIterator);
        when(mockIterator.next()).thenReturn("S");
        Assert.assertEquals("S", mockIterator.next());

        when(mockIterator.next()).thenReturn(1);
        Assert.assertEquals(1, mockIterator.next());
        Assert.assertNotEquals(2, mockIterator.next());

        ForTest forTest = new ForTest();
        when(mockIterator.next()).thenReturn(forTest);
        Assert.assertEquals(forTest,mockIterator);

    }

    @Test
    public void testHasNext() {
        when(myIterable.iterator()).thenReturn(mockIterator);
        when(mockIterator.hasNext()).thenReturn(true);

        Assert.assertTrue(mockIterator.hasNext());
    }
    @Test(expected = NullPointerException.class)
    public void testException() {
        when(mockIterator.next()).thenThrow(new NullPointerException());
        mockIterator.next();
    }


}
