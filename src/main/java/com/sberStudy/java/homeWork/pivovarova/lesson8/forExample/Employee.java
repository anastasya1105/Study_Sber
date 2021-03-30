package com.sberStudy.java.homeWork.pivovarova.lesson8.forExample;

import java.io.Serializable;

public class Employee extends People implements Serializable {

    private String position;
    private int personalFileNumber;

    public Employee(String name, String surname, String patronymic, boolean isMale, String birthday, Address adress, Passport passport, String position, int personalFileNumber) {
        super(name, surname, patronymic, isMale, birthday, adress, passport);
        this.position = position;
        this.personalFileNumber = personalFileNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getPersonalFileNumber() {
        return personalFileNumber;
    }

    public void setPersonalFileNumber(int personalFileNumber) {
        this.personalFileNumber = personalFileNumber;
    }


    @Override
    public String toString() {
        return super.toString()+
                "personalFileNumber=" + personalFileNumber +
                ", position='" + position + '\'' +
                '}';
    }
}
