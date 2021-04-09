package com.sberStudy.java.homeWork.pivovarova.lesson5;

import com.sberStudy.java.homeWork.pivovarova.lesson5.MyExceptions.AccountBalanceException;
import com.sberStudy.java.homeWork.pivovarova.lesson5.MyExceptions.AccountLockedException;
import com.sberStudy.java.homeWork.pivovarova.lesson5.api.PinValidator;
import com.sberStudy.java.homeWork.pivovarova.lesson5.api.Server;
import com.sberStudy.java.homeWork.pivovarova.lesson5.api.Terminal;
import com.sberStudy.java.homeWork.pivovarova.lesson5.bank.MessagesImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class TerminalImpl implements Terminal {
    private long blockTime;
    private final Server server;
    private final PinValidator pinValidator;
    private MessagesImpl messages = new MessagesImpl();
    private static boolean exit = false;
    private static final String OPERATION = "Для запроса баланса нажмите 1. " +
            "Для пополнения счета нажмите 2. " +
            "Для снятия наличных нажмите 3. " +
            "Для возврата карты нажмите EXIT (любую клавишу).";

    public TerminalImpl(Server server, PinValidator pinValidator) {
        this.server = server;
        this.pinValidator = pinValidator;

    }

    @Override
    public void work() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            messages.printMessages("Добро пожаловать! Вставьте карту (Введите номер карты)");
            int cardNumber = Integer.parseInt(reader.readLine());
            messages.printMessages("Введите пин-код:");
            String pinCode = reader.readLine();
            while (!pinCode.matches("-?\\d+(\\.\\d+)?")) {
                messages.printMessages("Пин-код не должен содержать нецифровые символы");
                messages.printMessages("Введите пин-код:");
                pinCode = reader.readLine();
            }
            int pin = Integer.parseInt(pinCode);
            if (isValid(cardNumber, pin)) return;
            operation(reader, cardNumber, pin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isValid(int cardNumber, int pin) {
        try {
            pinValidator.isValid(cardNumber, pin);
        } catch (AccountLockedException e) {
            messages.printException(e);
            blockTime = (new Date().getTime() / 1000 + 10);
            while (blockTime != new Date().getTime() / 1000) {
                try {
                    Thread.sleep(1000);
                    messages.printMessages("Дейстия с картой заблокированы на "
                            + (blockTime - new Date().getTime() / 1000) + " секунд.");
                } catch (InterruptedException c) {
                    c.printStackTrace();
                }
            }
            messages.printMessages("Не забудьте забрать вашу карту! До свидания! ");
            return true;
        }
        return false;
    }

    private void operation(BufferedReader reader, int cardNumber, int pin) throws IOException {
        int button;
        while (!exit) {
            messages.printMessages(OPERATION);
            button = Integer.parseInt(reader.readLine());
            switch (button) {
                case 1: {
                    messages.printMessages("Ваш баланс: " + server.getBalance(cardNumber, pin));
                    break;
                }
                case 2: {
                    messages.printMessages("Введите сумму: ");
                    int sum = Integer.parseInt(reader.readLine());
                    while (sum % 100 != 0) {
                        messages.printMessages("Сумма пополнения счёта должна быть кратна 100 рублям. " +
                                "Попробуйте выполнить операцию заново");
                        break;
                    }
                    server.plusBalance(cardNumber, pin, sum);
                    messages.printMessages("Баланс пополнен на сумму: " + sum);
                    break;
                }
                case 3: {
                    messages.printMessages("Введите сумму для снятия: ");
                    int sum = Integer.parseInt(reader.readLine());
                    try {
                        server.minusBalance(cardNumber, pin, sum);
                        messages.printMessages("Заберите наличные");
                        break;
                    } catch (AccountBalanceException e) {
                        messages.printException(e);
                        break;
                    }
                }
                default: {
                    messages.printMessages("Не забудьте забрать вашу карту! До свидания! ");
                    exit = true;
                    break;
                }

            }
        }
    }
}
