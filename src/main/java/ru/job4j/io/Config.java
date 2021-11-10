package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();
    private static final Pattern PAIR_TEMPLATE = Pattern.compile(".+" + "=" + ".+");

    public Config(final String path) {
        this.path = path;
    }

    public void load() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));
        String[] temp;
        for (String str : lines) {
            if (str.contains("#") || str.length() == 0) {
                    continue;
            }
            Matcher matcher = PAIR_TEMPLATE.matcher(str);
            if (!matcher.find()) {
                throw new IllegalArgumentException("Файл не соответствует шаблону, нет ключа или значения");
            }
            temp = str.split("=");
            if (temp.length != 2) {
                throw new IllegalArgumentException("Нарушение шаблона, несколько знаков ' = '");
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
