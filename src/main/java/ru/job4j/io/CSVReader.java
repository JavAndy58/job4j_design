package ru.job4j.io;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {

        validation(argsName);
        String argumentsLine = argsName.get("filter");
        String[] argumentsFilter = argumentsLine.split(",");
        int[] switcher = new int[argumentsFilter.length];
        StringBuilder outBuffer = new StringBuilder();

        try (Scanner scanner = new Scanner(new FileReader(argsName.get("path")))) {
            if (scanner.hasNextLine()) {
                String[] linesTempHead = formatString(argsName, scanner.nextLine());
                for (int i = 0; i < linesTempHead.length; i++) {
                    if (Arrays.asList(argumentsFilter).contains(linesTempHead[i])) {
                        switcher[i] = i;
                        if (i < (argumentsFilter.length - 1)) {
                            outBuffer.append(linesTempHead[i]).append(";");
                        } else {
                            outBuffer.append(linesTempHead[i]).append(System.lineSeparator());
                        }
                    }
                }
            }
            while (scanner.hasNextLine()) {
                String[] linesTemp = formatString(argsName, scanner.nextLine());
                for (Integer index : switcher) {
                    if (index != switcher[switcher.length - 1]) {
                        outBuffer.append(linesTemp[index]).append(";");
                    } else {
                        outBuffer.append(linesTemp[index]).append(System.lineSeparator());
                    }
                }
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

    private static String[] formatString(ArgsName argsName, String stringLine) {
        String stringTemp;
        stringTemp = stringLine.substring(1, stringLine.length() - 1);
        return stringTemp.split(argsName.get("delimiter"));
    }

    private static void validation(ArgsName argsName) throws IllegalArgumentException {

        final Pattern TEMPLATE_FILE = Pattern.compile("[a-zA-Z0-9\\.a-z]");
        final Pattern TEMPLATE_DELIMITER = Pattern.compile("\\W");
        final Pattern TEMPLATE_OUT = Pattern.compile("\\w");
        final Pattern TEMPLATE_FILTER = Pattern.compile("([a-z],)+");

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