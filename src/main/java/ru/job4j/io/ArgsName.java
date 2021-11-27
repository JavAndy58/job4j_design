package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private static Map<String, String> values = new HashMap<>();

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) throws IllegalArgumentException {
        String[] arguments;
        for (String argument : args) {
            arguments = argument.split("=");
            values.put(arguments[0].substring(1), arguments[1]);
        }
    }
}
