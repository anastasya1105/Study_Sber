package com.sberStudy.java.homeWork.pivovarova.lesson5.bank;

import com.sberStudy.java.homeWork.pivovarova.lesson5.ClientImpl;
import com.sberStudy.java.homeWork.pivovarova.lesson5.MyExceptions.AccountBalanceException;
import com.sberStudy.java.homeWork.pivovarova.lesson5.api.Server;

public class TerminalServer implements Server {
    private static final ServerDB serverDB = new ServerDB();
    GeneratorCardNumber generator = new GeneratorCardNumber();

    @Override
    public void saveClient() {
        ClientImpl client = new ClientImpl("Vladimir","Petrov","Andreevich", generator.generateNumberCard(), generator.generatePin());
        ClientImpl client1 = new ClientImpl("Dmitriy","Ivanov","Vasilievich", generator.generateNumberCard(), generator.generatePin());
        ClientImpl client2 = new ClientImpl("Petr","Kolosov","Matveevich", generator.generateNumberCard(), generator.generatePin());
        ClientImpl client3 = new ClientImpl("Georgiy","Farrell","Yuryevich", generator.generateNumberCard(), generator.generatePin());

        System.out.println(client.getCardNumber() + " " + client.getPin());
        System.out.println(client1.getCardNumber() + " " + client1.getPin());
        System.out.println(client2.getCardNumber() + " " + client2.getPin());
        System.out.println(client3.getCardNumber() + " " + client3.getPin());
        serverDB.saveClient(client.getCardNumber(),client.getPin(), client.getFullName(), 3780);
        serverDB.saveClient(client1.getCardNumber(),client1.getPin(), client1.getFullName(), 2690);
        serverDB.saveClient(client2.getCardNumber(),client2.getPin(), client2.getFullName(), 6940);
        serverDB.saveClient(client3.getCardNumber(),client3.getPin(), client3.getFullName(), 8460);
    }


    @Override
    public void plusBalance(int cardNumber, int pin, int sum) {
        int newBalance = getBalance(cardNumber, pin) + sum;
        serverDB.UpdateBalance(cardNumber, pin, newBalance);
    }

    @Override
    public int getBalance(int cardNumber, int pin) {
        return serverDB.getBalance(cardNumber, pin);
    }

    @Override
    public void minusBalance(int cardNumber, int pin, int sum) throws AccountBalanceException {
        int newBalance = getBalance(cardNumber, pin) - sum;
        if (newBalance <0) {
            throw new AccountBalanceException("Недостаточно средств для выполнения операции.  Попробуйте выполнить операцию, указав другую сумму");
        }
        if (sum%100!=0) {
            throw new AccountBalanceException("Сумма снятия должна быть кратна 100 рублям. Попробуйте выполнить операцию заново");
        }
        serverDB.UpdateBalance(cardNumber, pin, newBalance);
    }

    @Override
    public int getPin(int cardNumber) {
        return serverDB.getPin(cardNumber);
    }

    @Override
    public void createTable() {
        DateSourceHelper.createDb();
    }
}
