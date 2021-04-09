package com.sberStudy.java.homeWork.pivovarova.lesson5.bank;

import com.sberStudy.java.homeWork.pivovarova.lesson5.MyExceptions.AccountLockedException;
import com.sberStudy.java.homeWork.pivovarova.lesson5.api.PinValidator;
import com.sberStudy.java.homeWork.pivovarova.lesson5.api.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class PinValidatorImpl implements PinValidator {
    private MessagesImpl messages = new MessagesImpl();
    private static final int PIN_LIMIT = 3;
    private Server terminalServer;
    private int errPINCounter;


    public PinValidatorImpl(Server terminalServer) {
        this.terminalServer = terminalServer;
        errPINCounter = 0;
    }
    @Override
    public boolean isValid(int cardNumber, int pin) throws AccountLockedException {
        int pinCode = terminalServer.getPin(cardNumber);
        System.out.println(pinCode);
        try  {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                if (pinCode == pin) {
                    return true;
                }
                errPINCounter++;
                if (errPINCounter >= PIN_LIMIT) {
                    throw new AccountLockedException("Выполнено 3 некорректные попытки ввода ПИН-кода. Дейстия с картой заблокированы на 10 секунд.");
                }
                    messages.printMessages("Некорректный ПИН. Попробуйте ввести код заново.");
                    messages.printMessages("Введите пин-код:");
                    pin = Integer.parseInt(reader.readLine());
            }
        } catch (IOException e) {
                messages.printError();
        }
        return false;
    }

}
