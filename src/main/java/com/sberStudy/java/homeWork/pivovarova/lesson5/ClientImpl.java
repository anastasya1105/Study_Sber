package com.sberStudy.java.homeWork.pivovarova.lesson5;

public class ClientImpl {
    private String name;
    private String surname;
    private String patronymic;
    private int cardNumber;
    private int pin;

    public ClientImpl(String name, String surname, String patronymic, int cardNumber, int pin) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.cardNumber = cardNumber;
        this.pin = pin;
    }

    public String getFullName() {
        StringBuilder sb = new StringBuilder(surname)
                .append(" ")
                .append(name)
                .append(" ")
                .append(patronymic);
        return sb.toString();
    }


    public int getCardNumber() {
        return cardNumber;
    }

    public int getPin() {
        return pin;
    }

}
