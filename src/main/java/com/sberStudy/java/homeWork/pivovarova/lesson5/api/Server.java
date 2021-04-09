package com.sberStudy.java.homeWork.pivovarova.lesson5.api;

import com.sberStudy.java.homeWork.pivovarova.lesson5.MyExceptions.AccountBalanceException;

public interface Server {
    void saveClient();

    void plusBalance(int cardNumber, int pin, int sum);

    int getBalance(int cardNumber, int pin);

    void minusBalance(int cardNumber, int pin, int sum) throws AccountBalanceException;

    int getPin(int cardNumber);
    void createTable();
}
