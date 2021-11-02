package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String line;
            String[] lines;
            String search = "404";
            while ((line = in.readLine()) != null) {
                lines = line.split(" ");
                boolean found = Arrays.asList(lines).contains(search);
                if (found) {
                    list.add(line);
                    list.add(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}
