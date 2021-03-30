package com.sberStudy.java.homeWork.pivovarova.lesson5.Terminal;


import com.sberStudy.java.homeWork.pivovarova.lesson5.ClientAccount;
import com.sberStudy.java.homeWork.pivovarova.lesson5.MyExceptions.AccountBalanceException;

public interface Terminal {
    int checkAccount(ClientAccount clientAccount);

    public void plusBalance(ClientAccount clientAccount, int sum);

    public void minusBalance(ClientAccount clientAccount, int sum) throws AccountBalanceException;
}
