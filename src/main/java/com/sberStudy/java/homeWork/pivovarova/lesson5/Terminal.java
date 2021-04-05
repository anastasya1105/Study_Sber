package com.sberStudy.java.homeWork.pivovarova.lesson5;

import com.sberStudy.java.homeWork.pivovarova.lesson5.MyExceptions.AccountBalanceException;

public interface Terminal {
    int checkClient(Client client);
    void minusBalance(Client client, int sum) throws AccountBalanceException;
    void plusBalance(Client client, int sum) throws AccountBalanceException;

}
