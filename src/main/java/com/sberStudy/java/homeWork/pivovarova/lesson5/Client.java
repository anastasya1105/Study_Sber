package com.sberStudy.java.homeWork.pivovarova.lesson5;

import com.sberStudy.java.homeWork.pivovarova.lesson5.MyExceptions.AccountBalanceException;

public class Client {
    private int balance;

    public Client() {
        this.balance = 0;
    }

    public int getBalance() {
        return balance;
    }

    private void setBalance(int balance) {
        this.balance = balance;
    }

    public void putMoney(int sum) {
        setBalance(getBalance()+sum);
    }
    public void getMoney(int sum) throws AccountBalanceException {
        if (getBalance()-sum <0) {
            throw new AccountBalanceException("Недостаточно средств для выполнения операции.  Попробуйте выполнить операцию, указав другую сумму");
        }
        if (sum%100!=0) {
            throw new AccountBalanceException("Сумма снятия должна быть кратна 100 рублям. Попробуйте выполнить операцию заново");
        }
        setBalance(getBalance()-sum);
    }

}
