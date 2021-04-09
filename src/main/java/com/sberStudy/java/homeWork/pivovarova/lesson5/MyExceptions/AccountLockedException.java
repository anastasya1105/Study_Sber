package com.sberStudy.java.homeWork.pivovarova.lesson5.MyExceptions;

public class AccountLockedException extends RuntimeException{
    public AccountLockedException(String message) {
        super(message);
    }
}
