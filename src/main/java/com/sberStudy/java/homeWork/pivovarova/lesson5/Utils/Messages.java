package com.sberStudy.java.homeWork.pivovarova.lesson5.Utils;

public class Messages {
    public static void consoleExceptionPrinter(Throwable throwable) {
        System.out.println(throwable.getMessage());
    }

    public static void printConsoleMessage(String message) {
        System.out.println(message);
    }

    public static void printDefaultConsoleErr() {
        System.out.println("Возникла техническая ошибка, просьба обратиться в службу поддержки.");
    }
}
