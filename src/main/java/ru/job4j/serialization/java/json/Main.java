package ru.job4j.serialization.java.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Driver driver = new Driver("Petr", 40, true, new CarMazda("CX-5"), new String[] {"Passport", "License"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(driver));
        final String driverJson =
                "{"
                + "\"name\":\"Petr\","
                + "\"age\":40,"
                + "\"sex\":true,"
                + "\"documents\":"
                + "[\"Passport\",\"License\"],"
                + "\"carMazda\":"
                + "{"
                + "\"modelAuto\":\"CX-5\""
                + "},"
                + "}";
        final Driver driverMod = gson.fromJson(driverJson, Driver.class);
        System.out.println(driverMod);
    }
}
