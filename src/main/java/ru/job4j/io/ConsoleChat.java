package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> listPhrases = readPhrases();
        List<String> listDialogs = new ArrayList<>();
        boolean switchPhrases = true;

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String inputText = bufferedReader.readLine();
            while (!(OUT).equals(inputText)) {
                listDialogs.add(inputText);
                if ((STOP).equals(inputText)) {
                    switchPhrases = false;
                }
                if (!(STOP).equals(inputText) && switchPhrases) {
                    String line;
                    int randomIndex = (int) (Math.random() * listPhrases.size());
                    line = listPhrases.get(randomIndex);
                    System.out.println(line);
                    listDialogs.add(line);
                }
                if ((CONTINUE).equals(inputText)) {
                    switchPhrases = true;
                }
                inputText = bufferedReader.readLine();
                if ((OUT).equals(inputText)) {
                    listDialogs.add(inputText);
                }
            }
        saveLog(listDialogs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readPhrases() {
        List<String> listPhrases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers, Charset.forName("WINDOWS-1251")))) {
            br.lines().forEach(listPhrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listPhrases;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, Charset.forName("WINDOWS-1251")))) {
            for (String str : log) {
                bufferedWriter.write(str + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/history_dialogue.txt", "./data/bot_words.txt");
        cc.run();
    }
}
