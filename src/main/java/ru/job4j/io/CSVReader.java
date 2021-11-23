package ru.job4j.io;

import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
//        String argumentsLine = argsName.get("filter");
//        String[] argumentsFilter = argumentsLine.split(",");


        try (Scanner scanner = new Scanner(new FileReader(argsName.get("path")))) {
            String line;
            String[] lines;

            while (scanner.hasNext()) {
                scanner.useDelimiter("\"");
                line = scanner.next();
                lines = line.split(";");
                for (String str : lines) {
                    System.out.print(str + " ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        handle(ArgsName.of(args));
    }
}