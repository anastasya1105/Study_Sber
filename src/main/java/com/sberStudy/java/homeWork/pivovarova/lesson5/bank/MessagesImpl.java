package com.sberStudy.java.homeWork.pivovarova.lesson5.bank;

import com.sberStudy.java.homeWork.pivovarova.lesson5.api.Messages;

public class MessagesImpl implements Messages {

    @Override
    public void printException(Exception e) {
        System.out.println(e.getMessage());
    }

    @Override
    public void printError() {
        System.out.println("Ошибка! Нет возможности ввести пин-код.");
    }

    @Override
    public void printMessages(String messages) {
        System.out.println(messages);
    }
}
