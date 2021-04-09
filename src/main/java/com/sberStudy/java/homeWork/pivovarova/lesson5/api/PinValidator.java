package com.sberStudy.java.homeWork.pivovarova.lesson5.api;

import com.sberStudy.java.homeWork.pivovarova.lesson5.MyExceptions.AccountLockedException;

public interface PinValidator {
    boolean isValid(int cardNumber, int pin) throws AccountLockedException;
}
