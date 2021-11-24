package ru.job4j.io;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class CSVReader {
    private static List<Integer> index = new ArrayList<>();
    public static void handle(ArgsName argsName) throws Exception {

        String argumentsLine = argsName.get("filter");
        String[] argumentsFilter = argumentsLine.split(",");

        try (Scanner scanner = new Scanner(new FileReader(argsName.get("path")))) {

            while (scanner.hasNextLine()) {
                String lineTemp;
                String line;
                String[] linesTemp;
                lineTemp = scanner.nextLine();
                line = lineTemp.substring(1, lineTemp.length() - 1);
                linesTemp = line.split(";");

                int[] switcher = new int[argumentsFilter.length];

                for (int i = 0; i < linesTemp.length; i++) {
                    if (Arrays.asList(argumentsFilter).contains(linesTemp[i])) {
                        switcher[i] = i;
                    }

                }
                System.out.println(Arrays.toString(switcher));
//                for (int i = 0; i < linesTemp.length; i++) {
//                    if (Arrays.binarySearch(switcher, i) >= 0) {
//                        System.out.print(linesTemp[i]);
//                    }
//                }

                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private static void searchIndex(ArgsName argsName) {
//        String[] indexSearch;
//        String argumentsLine = argsName.get("filter");
//        String[] argumentsFilter = argumentsLine.split(",");
//
//        try (Scanner scanner = new Scanner(new FileReader(argsName.get("path")))) {
//            System.out.println(Arrays.toString(scanner.nextLine().split(";")));
//            indexSearch = scanner.nextLine().split(";");
//            for (int i = 0; i < indexSearch.length; i++) {
//               if (argumentsFilter.equals())
//            }
//            index.add(1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) throws Exception {
        handle(ArgsName.of(args));
    }
}