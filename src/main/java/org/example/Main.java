package org.example;

import org.example.readers.FileReader;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Необходимо указать путь к папке, в которой нужно проверить файлы.");
            return;
        }

        ArrayList<Text> texts = FileReader.readFiles(args[0]);
    }
}

