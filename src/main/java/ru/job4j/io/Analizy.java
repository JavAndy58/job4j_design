package ru.job4j.io;

import java.io.*;
import java.util.Objects;

public class Analizy {
    public void unavailable(String source, String target) {
        String line;
        int rsl = 0;

        try (BufferedReader in = new BufferedReader(new FileReader(source));
        PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            while ((line = in.readLine()) != null) {
                String[] lines = line.split(" ");
                if (rsl == 0 && (Objects.equals(lines[0], "400") || Objects.equals(lines[0], "500"))) {
                    out.print(lines[1] + ";");
                    rsl = 1;
                }
                if (rsl == 1 && (Objects.equals(lines[0], "200") || Objects.equals(lines[0], "300"))) {
                    out.print(lines[1] + ";");
                    out.print(System.lineSeparator());
                    rsl = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("server.log", "unavailable.csv");
    }
}
