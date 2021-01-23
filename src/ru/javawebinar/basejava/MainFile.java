package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) {
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src/ru/javawebinar/basejava");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        printFiles(dir);
    }

    public static void printFiles(File file) {
        File[] files = file.listFiles();
        Objects.requireNonNull(files);

        for (File value : files) {
            if (value.isFile()) {
                System.out.println("\tFile: " + value.getName());
            }
            if (value.isDirectory()) {
                System.out.println("Directory: " + value.getName());
                printFiles(value);
            }
        }
    }
}
