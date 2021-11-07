package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        Map<String, String> tmp;
        tmp = toString().lines()
                .filter(Objects::nonNull)
                .filter(x -> !x.contains("#"))
                .map(str -> str.split("="))
                .filter(s -> s.length > 1)
                .collect(Collectors.toMap(str -> str[0], str -> str[1]));
        values.putAll(tmp);
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
