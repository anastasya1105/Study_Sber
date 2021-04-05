package com.sberStudy.java.homeWork.pivovarova.lesson5;

import com.sberStudy.java.homeWork.pivovarova.lesson5.MyExceptions.AccountLockedException;
import com.sberStudy.java.homeWork.pivovarova.lesson5.MyExceptions.InvalidPinException;

import java.util.Date;

public class PinValidator {
    private static final int PIN_LIMIT = 3;
    private static final int TIME_DELAY = 10000;

    private TerminalServer terminalServer;
    private Date blockTime;
    private int errPINCounter;

    public PinValidator(TerminalServer terminalServer) {
        this.terminalServer = terminalServer;
        errPINCounter = 0;
    }

    public Client validatePIN(Integer pin) throws AccountLockedException, InvalidPinException {
        Client client = terminalServer.getClientByPIN(pin);
        if (blockTime == null || blockTime.before(new Date())){
            if (client == null){
                errPINCounter++;
                if (errPINCounter>= PIN_LIMIT) {
                    blockTime = new Date(new Date().getTime()+ TIME_DELAY);
                    errPINCounter = 0;
                }
                throw new InvalidPinException("Некорректный ПИН. Попробуйте ввести код заново.");
            }
            return client;
        }
        else {
            throw new AccountLockedException("Выполнено 3 некорректные попытки ввода ПИН-кода. Дейстия с картой заблокированы на "
                    + (blockTime.getTime() - new Date().getTime()) / 1000 + " секунд.");
        }

    }

}
