package ru.job4j.serialization.java.json;

import java.util.Arrays;

public class Driver {
    private final String name;
    private final int age;
    private final boolean sex;
    private final CarMazda carMazda;
    private final String[] documents;

    public Driver(String name, int age, boolean sex, CarMazda carMazda, String[] documents) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.carMazda = carMazda;
        this.documents = documents;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", carMazda=" + carMazda +
                ", documents=" + Arrays.toString(documents) +
                '}';
    }
}
