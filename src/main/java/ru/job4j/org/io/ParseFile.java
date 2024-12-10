package ru.job4j.org.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {

    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public String getContentWithoutUnicode() {
        return getContent(u -> u < 0x80);
    }

    public String getContentWithUnicode() {
       return getContent(u -> true);
    }

    private synchronized String getContent(Predicate<Character> filter) {
        StringBuilder sb = new StringBuilder();
        try (var input = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = input.read()) != -1) {
                if (filter.test((char) data)) {
                    sb.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
