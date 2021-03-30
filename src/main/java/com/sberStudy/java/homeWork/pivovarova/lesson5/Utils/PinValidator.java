package com.sberStudy.java.homeWork.pivovarova.lesson5.Utils;



import com.sberStudy.java.homeWork.pivovarova.lesson5.ClientAccount;
import com.sberStudy.java.homeWork.pivovarova.lesson5.MyExceptions.AccountLockedException;
import com.sberStudy.java.homeWork.pivovarova.lesson5.MyExceptions.InvalidPinException;
import com.sberStudy.java.homeWork.pivovarova.lesson5.Terminal.TerminalServer;

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

    /**
     * Метод выполняет проверку наличия клиента по введенному ПИН-коду и временную блокировку в случае некорректного ввода
     *
     * @param pin - ПИН-код, для которого выполняется валидация.
     * @return - объект счета клиента, для введенного пин-кода
     * @throws InvalidPinException    - ошибка некорректного ввода ПИН-кода
     * @throws AccountLockedException - ошибка, возникающая при превышении доступного кол-во ввода некорректного пин-кода
     */
    public ClientAccount validatePIN(Integer pin) throws InvalidPinException, AccountLockedException {
        ClientAccount clientAccount = terminalServer.getClientByPIN(pin);

        if (blockTime == null || blockTime.before(new Date())) {
            if (clientAccount == null) {
                errPINCounter++;

                if (errPINCounter >= PIN_LIMIT - 1) {
                    blockTime = new Date(new Date().getTime() + TIME_DELAY);
                    errPINCounter = 0;
                }
                throw new InvalidPinException("Некорректный ПИН. Попробуйте ввести код заново.");
            } else {
                return clientAccount;
            }
        } else {
            throw new AccountLockedException("Выполнено " + PIN_LIMIT + " некорректные попытки ввода ПИН-кода." +
                    "Дейстия с картой заблокированы на " + (blockTime.getTime() - new Date().getTime()) / 1000 + " секунд.");
        }
    }
}
