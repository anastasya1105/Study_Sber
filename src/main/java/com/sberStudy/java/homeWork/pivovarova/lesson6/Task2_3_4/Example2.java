package com.sberStudy.java.homeWork.pivovarova.lesson6.Task2_3_4;

public class Example2 {
    private String name;
    private String country;
    private String town;

    public Example2(String name, String country, String town) {
        this.name = name;
        this.country = country;
        this.town = town;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public static void printDayOfWeek(int day) {
        System.out.println("Какой-то день недели");
    }
    private String returnStr() {
        return name + "день недели";
    }
    public void printInfo() {
        System.out.println("Example: name " + this.name + ", country " + this.country + ", town " + this.town);
    }
}
