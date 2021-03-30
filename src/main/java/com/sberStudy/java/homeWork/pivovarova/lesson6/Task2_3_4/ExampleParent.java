package com.sberStudy.java.homeWork.pivovarova.lesson6.Task2_3_4;

public class ExampleParent {
    private int id;
    private int age;

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ExampleParent(int id, int age) {
        this.id = id;
        this.age = age;
    }

    public void info() {
        System.out.println("id - " + getAge() + ", age - " + getId());
    }
}
