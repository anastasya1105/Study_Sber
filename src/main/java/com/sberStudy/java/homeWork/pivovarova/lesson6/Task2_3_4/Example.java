package com.sberStudy.java.homeWork.pivovarova.lesson6.Task2_3_4;

public class Example extends ExampleParent {
    public static final String MONDAY = "MONDAY";
    public static final String TUESDAY = "TUESDAY";
    public static final String WEDNESDAY = "WEDNESDAY";
    public static final String THURSDAY = "THURSDAY";
    public static final String FRIDAY = "FRIDAY";
//    public static final String FRIDAY = "SATURDAY";
    public static final String SATURDAY = "SATURDAY";
    public static final String SUNDAY = "SUNDAY";
//    public static final String SUNDAY = "SATURDAY";
//
    private String name;
    private String country;
    private String town;

    public Example(int id, int age, String name, String country, String town) {
        super(id, age);
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
        System.out.println("Example: name " + this.name + ", id " + this.getId() + ", age " + this.getAge() + ", country " + this.country + ", town " + this.town);
    }
}
