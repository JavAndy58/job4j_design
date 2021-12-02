package ru.job4j.io;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

public class CSVReader {

    private static final Pattern TEMPLATE_FILE = Pattern.compile("[a-zA-Z0-9\\.a-z]");
    private static final Pattern TEMPLATE_DELIMITER = Pattern.compile("\\W");
    private static final Pattern TEMPLATE_OUT = Pattern.compile("\\w");
    private static final Pattern TEMPLATE_FILTER = Pattern.compile("([a-z],)+");

    public static void handle(ArgsName argsName) throws Exception {
        validation(argsName);
        String argumentsHead = argsName.get("filter");
        String[] arrayHead = argumentsHead.split(",");
        int[] switcher = new int[arrayHead.length];
        StringBuilder outBuffer = new StringBuilder();
        for (int i = 0; i < arrayHead.length; i++) {
            switcher[i] = i;
        }
        try (Scanner scanner = new Scanner(new FileReader(argsName.get("path")))) {
            if (scanner.hasNextLine()) {
                String lineHeadScanner = scanner.nextLine();
                String lineHeadScannerSubstring = lineHeadScanner.substring(0, lineHeadScanner.length() - 1);
                String[] linesHeadScanner = lineHeadScannerSubstring.split(",");
                StringJoiner lineHeadBuffer = new StringJoiner(argsName.get("delimiter"));
                for (Integer index : switcher) {
                    lineHeadBuffer.add(linesHeadScanner[index]);
                }
                outBuffer.append(lineHeadBuffer);
                outBuffer.append(System.lineSeparator());
            }
            while (scanner.hasNextLine()) {
                String lineScanner = scanner.nextLine();
                String lineScannerSubstring = lineScanner.substring(0, lineScanner.length() - 1);
                String[] linesScanner = lineScannerSubstring.split(argsName.get("delimiter"));
                StringJoiner lineBuffer = new StringJoiner(argsName.get("delimiter"));
                for (Integer index : switcher) {
                    lineBuffer.add(linesScanner[index]);
                }
                outBuffer.append(lineBuffer);
                outBuffer.append(System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (argsName.get("out").equals("stdout")) {
            System.out.print(outBuffer);
            } else {
                 try (PrintWriter writer = new PrintWriter(argsName.get("out"))) {
                    writer.write(outBuffer.toString());
                 }
            }
    }

    private static void validation(ArgsName argsName) throws IllegalArgumentException {

        Matcher matcherFile = TEMPLATE_FILE.matcher(argsName.get("path"));
        File file = new File(argsName.get("path"));
        if (!file.exists() && !file.isDirectory() && !matcherFile.find()) {
            throw new IllegalArgumentException("Файл указан не правильно или не существует");
        }
        Matcher matcherDelimited = TEMPLATE_DELIMITER.matcher(argsName.get("delimiter"));
        if (!matcherDelimited.find()) {
            throw new IllegalArgumentException("Делитель указан не правильно");
        }
        Matcher matcherOut = TEMPLATE_OUT.matcher(argsName.get("out"));
        File fileOut = new File(argsName.get("out"));
        if (!matcherOut.find() && (!fileOut.exists() || argsName.get("out").equals("stdout"))) {
            throw new IllegalArgumentException("Файл для вывода или stdout указаны не правильно");
        }
        Matcher matcherFilter = TEMPLATE_FILTER.matcher(argsName.get("filter"));
        if (!matcherFilter.find()) {
            throw new IllegalArgumentException("Фильтр указан не правильно");
        }
    }

    public static void main(String[] args) throws Exception {
        handle(ArgsName.of(args));
    }
}