package ru.job4j.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {

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

    public static void main(String[] args) throws Exception {
        handle(ArgsName.of(args));
    }
}