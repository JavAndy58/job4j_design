package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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

    public void load() throws IllegalArgumentException {
        String line;
        String[] lines;
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {

            while ((line = in.readLine()) != null) {
                if (line.contains("#") || line.length() == 0) {
                    continue;
                }
                lines = line.split("=");
                if (lines.length != 2) {
                    throw new IllegalArgumentException();
                }
                values.put(lines[0], lines[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
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
