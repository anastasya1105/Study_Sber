package com.sberStudy.java.homeWork.pivovarova.lesson14.forExample;

import java.io.Serializable;

public class People implements Serializable {
    private String name;
    private String surname;
    private String patronymic;
    private boolean isMale;
    private String birthday;
    private Address address = new Address();
    private transient Passport passport = new Passport();

    public People(String name, String surname, String patronymic, boolean isMale, String birthday, Address address, Passport passport) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.isMale = isMale;
        this.birthday = birthday;
        this.address = address;
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Address getAdress() {
        return address;
    }

    public void setAdress(Address adress) {
        this.address = adress;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    @Override
    public String toString() {
        return "People{" +
                " name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", isMale=" + isMale +
                ", address=" + address +
                ", passport=" + passport +
                '}';
    }
}
