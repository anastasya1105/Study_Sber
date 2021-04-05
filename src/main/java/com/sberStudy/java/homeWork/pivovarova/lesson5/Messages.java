package com.sberStudy.java.homeWork.pivovarova.lesson5;

public class Messages {

    public void printException(Exception e) {
        System.out.println(e.getMessage());
    }

    public void printError() {
        System.out.println("Ошибка! Нет возможности ввести пин-код.");
    }

    public void printMessages(String messages) {
        System.out.println(messages);
    }
}
