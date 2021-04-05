package com.sberStudy.java.homeWork.pivovarova.lesson5;

import com.sberStudy.java.homeWork.pivovarova.lesson5.MyExceptions.AccountBalanceException;
import com.sberStudy.java.homeWork.pivovarova.lesson5.MyExceptions.AccountLockedException;
import com.sberStudy.java.homeWork.pivovarova.lesson5.MyExceptions.InvalidPinException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TerminalImpl implements Terminal{
    private final TerminalServer server;
    private final PinValidator pinValidator;
    private Messages messages = new Messages();
    private boolean exit = false;

    public TerminalImpl(TerminalServer server, PinValidator pinValidator) {
        this.server = server;
        this.pinValidator = pinValidator;

    }

    public void work() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            while (!exit) {
                messages.printMessages("Добро пожаловать! Введите пин-код");
                Integer pin = Integer.parseInt(reader.readLine());
                Client client = null;
                try {
                    client = pinValidator.validatePIN(pin);
                } catch (AccountLockedException | InvalidPinException e) {
                    messages.printException(e);
                }
                messages.printMessages("Для запроса баланса нажмите 1. " +
                        "Для пополнения счета нажмите 2. " +
                        "Для снятия наличных нажмите 3. " +
                        "Для возврата карты нажмите 4.");
                int button = Integer.parseInt(reader.readLine());
                while (!exit) {

                    switch (button) {
                        case 1: {
                            messages.printMessages("Ваш баланс: " + server.checkClient(client));
                            messages.printMessages("Для пополнения счета нажмите 2. " +
                                    "Для снятия наличных нажмите 3. " +
                                    "Для возврата карты нажмите 4.");
                            button = Integer.parseInt(reader.readLine());
                            break;
                        }
                        case 2: {
                            messages.printMessages("Введите сумму: ");
                            int sum = Integer.parseInt(reader.readLine());
                            if (sum%100!=0){
                                messages.printMessages("Сумма пополнения счёта должна быть кратна 100 рублям. " +
                                        "Попробуйте выполнить операцию заново");
                                messages.printMessages("Для запроса баланса нажмите 1. " +
                                        "Для пополнения счета нажмите 2. " +
                                        "Для снятия наличных нажмите 3. " +
                                        "Для возврата карты нажмите 4.");
                                button = Integer.parseInt(reader.readLine());
                                break;
                            }
                            else {
                                server.plusBalance(client, sum);
                                messages.printMessages("Баланс пополнен на сумму: " + sum);
                                messages.printMessages("Для запроса баланса нажмите 1. " +
                                        "Для пополнения счета нажмите 2. " +
                                        "Для снятия наличных нажмите 3. " +
                                        "Для возврата карты нажмите 4.");
                                button = Integer.parseInt(reader.readLine());
                                break;
                            }
                        }
                        case 3: {
                            messages.printMessages("Введите сумму для снятия: ");
                            int sum = Integer.parseInt(reader.readLine());
                            try {
                                server.minusBalance(client, sum);
                                messages.printMessages("Заберите наличные");
                                messages.printMessages("Для запроса баланса нажмите 1. " +
                                        "Для пополнения счета нажмите 2. " +
                                        "Для снятия наличных нажмите 3. " +
                                        "Для возврата карты нажмите 4.");
                                button = Integer.parseInt(reader.readLine());
                                break;
                            } catch (AccountBalanceException e) {
                                messages.printException(e);
                                messages.printMessages("Для запроса баланса нажмите 1. " +
                                        "Для пополнения счета нажмите 2. " +
                                        "Для снятия наличных нажмите 3. " +
                                        "Для возврата карты нажмите 4.");
                                button = Integer.parseInt(reader.readLine());
                                break;
                            }
                        }
                        case 4: {
                            messages.printMessages("Не забудьте забрать вашу карту! До свидания! ");
                            exit = true;
                            break;
                        }

                    }
                }

            }
        } catch (IOException e) {
            messages.printError();
        }
    }

    @Override
    public int checkClient(Client client) {
        return server.checkClient(client);
    }

    @Override
    public void plusBalance(Client client, int sum) {
        server.plusBalance(client, sum);
    }

    @Override
    public void minusBalance(Client client, int sum) {
        try {
            server.minusBalance(client, sum);
        } catch (AccountBalanceException e) {
            messages.printException(e);
        }
    }
}
