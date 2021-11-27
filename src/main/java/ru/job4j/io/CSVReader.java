package ru.job4j.io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
        String argumentsLine = argsName.get("filter");
        String[] argumentsFilter = argumentsLine.split(",");
        int[] switcher = new int[argumentsFilter.length];

        try (Scanner scanner = new Scanner(new FileReader(argsName.get("path")))) {

            while (scanner.hasNextLine()) {
                String str;
                String lineTemp = scanner.nextLine();
                str = lineTemp.substring(0, lineTemp.length() - 1);
                String[] linesTemp = str.split(argsName.get("delimiter"));
                for (int i = 0; i < linesTemp.length; i++) {
                    if (Arrays.asList(argumentsFilter).contains(linesTemp[i])) {
                        switcher[i] = i;
                    }
                }
                for (int i = 0; i < linesTemp.length; i++) {
                    if (Arrays.binarySearch(switcher, i) >= 0) {
                        if (argsName.get("out").equals("stdout")) {
                            if (i != switcher[switcher.length - 1]) {
                                System.out.print(linesTemp[i] + ";");
                            } else {
                                System.out.println(linesTemp[i]);
                            }
                        } else {
                            try (FileWriter writer = new FileWriter((argsName.get("out")), true)) {
                                if (i != switcher[switcher.length - 1]) {
                                    writer.write(linesTemp[i] + ";");
                                } else {
                                    writer.write(linesTemp[i] + System.lineSeparator());
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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