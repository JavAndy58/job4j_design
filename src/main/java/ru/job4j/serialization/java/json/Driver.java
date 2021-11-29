package ru.job4j.serialization.java.json;

import java.util.Arrays;

public class Driver {

    private final String name;
    private final int age;
    private final boolean sex;
    private final String[] documents;
    private final CarMazda carMazda;

    public Driver(String name, int age, boolean sex, String[] documents, CarMazda carMazda) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.documents = documents;
        this.carMazda = carMazda;
    }

    @Override
    public String toString() {
        return "Driver{name='" + name + '\'' + ", age=" + age + ", sex=" + sex
                + ", documents=" + Arrays.toString(documents) + '}';
    }
}
