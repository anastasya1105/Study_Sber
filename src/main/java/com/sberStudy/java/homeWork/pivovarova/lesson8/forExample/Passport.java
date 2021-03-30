package com.sberStudy.java.homeWork.pivovarova.lesson8.forExample;

import java.io.Serializable;
import java.util.Date;

public class Passport implements Serializable {
    int series;
    int number;
    Date whenCreated;
    String whereCreated;

    public Passport() {
    }

    public Passport(int series, int number, Date whenCreated, String whereCreated) {
        this.series = series;
        this.number = number;
        this.whenCreated = whenCreated;
        this.whereCreated = whereCreated;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getWhenCreated() {
        return whenCreated;
    }

    public void setWhenCreated(Date whenCreated) {
        this.whenCreated = whenCreated;
    }

    public String getWhereCreated() {
        return whereCreated;
    }

    public void setWhereCreated(String whereCreated) {
        this.whereCreated = whereCreated;
    }

    @Override
    public String toString() {
        return "Passport{" +
                ", series=" + series +
                ", number=" + number +
                ", whenCreated=" + whenCreated +
                ", whereCreated='" + whereCreated + '\'' +
                '}';
    }
}
