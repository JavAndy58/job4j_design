package ru.job4j.io;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        final Pattern TEMPLATE_SCANNER = Pattern.compile("\".*\"");
        try (Scanner scanner = new Scanner(new FileReader(argsName.get("path")))) {
            scanner.useDelimiter(argsName.get("delimiter"));
//            scanner.findWithinHorizon(TEMPLATE_SCANNER.matcher(argsName.get("delimiter")));
            while (scanner.hasNext()) {
                System.out.println(scanner.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        handle(ArgsName.of(args));

    }
}