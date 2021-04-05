package com.sberStudy.java.homeWork.pivovarova.lesson5;

import com.sberStudy.java.homeWork.pivovarova.lesson5.MyExceptions.AccountBalanceException;

import java.util.HashMap;
import java.util.Map;

public class TerminalServer implements Terminal{
    Map<Integer, Client> clientMap = new HashMap<>();

    public TerminalServer() {
        this.clientMap = clientMap;
        Client client = new Client();
        client.putMoney(780);

        Client client1 = new Client();
        client1.putMoney(690);

        Client client2 = new Client();
        client2.putMoney(6940);

        Client client3 = new Client();
        client3.putMoney(8460);

        clientMap.put(6954, client);
        clientMap.put(6413, client1);
        clientMap.put(4128, client2);
        clientMap.put(3298, client3);
    }

    public Client getClientByPIN(int pin){
        return clientMap.get(pin);
    }


    @Override
    public int checkClient(Client client) {
        return client.getBalance();
    }

    @Override
    public void minusBalance(Client client, int sum) throws AccountBalanceException {
        client.getMoney(sum);
    }

    @Override
    public void plusBalance(Client client, int sum){
        client.putMoney(sum);
    }
}
