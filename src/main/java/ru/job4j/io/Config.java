package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        String line = toString();
        String[] lines = line.split(System.lineSeparator());
        String[] temp;
        for (String str : lines) {
            if (str.contains("#") || str.length() == 0) {
                    continue;
            }
            Pattern pattern = Pattern.compile(".+" + "=" + ".+");
            Matcher matcher = pattern.matcher(str);
            if (!matcher.find()) {
                throw new IllegalArgumentException();
            }
            temp = str.split("=");
            if (temp.length != 2) {
                throw new IllegalArgumentException();
            }
            values.put(temp[0], temp[1]);
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
