package com.sberStudy.java.homeWork.pivovarova.lesson5;


import com.sberStudy.java.homeWork.pivovarova.lesson5.MyExceptions.AccountBalanceException;

public class ClientAccount {
    private int balance;

    public ClientAccount() {
        this.balance = 0;
    }

    public int getBalance() {
        return balance;
    }

    private void setBalance(int balance) {
        this.balance = balance;
    }

    public void plusBalance(int sum) {
        setBalance(getBalance() + sum);

    }

    /**
     *
     * @param sum - сумма, запрошенная клиентом к выдаче
     * @throws AccountBalanceException - исключение при превышении остатка по счету
     */
    public void minusBalance(int sum) throws AccountBalanceException {

        if (getBalance() - sum < 0) {
            throw new AccountBalanceException("Недостаточно средств для выполнения операции. " +
                    "Необходимо пополнить счет или запросить меньшую сумму");
        } else {
            setBalance(getBalance() - sum);
        }
    }
}
