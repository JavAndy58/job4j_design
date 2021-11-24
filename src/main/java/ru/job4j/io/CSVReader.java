package ru.job4j.io;

import java.io.*;
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
                str = lineTemp.substring(1, lineTemp.length() - 1);
                String[] linesTemp = str.split(";");

                for (int i = 0; i < linesTemp.length; i++) {
                    if (Arrays.asList(argumentsFilter).contains(linesTemp[i])) {
                        switcher[i] = i;
                    }

                }
                for (int i = 0; i < linesTemp.length; i++) {
                    if (Arrays.binarySearch(switcher, i) >= 0) {
                        System.out.print(linesTemp[i] + " ");
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws Exception {
        handle(ArgsName.of(args));
    }
}