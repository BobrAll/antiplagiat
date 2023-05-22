package org.example;

import org.example.readers.FileReader;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Необходимо указать путь к папке, в которой нужно проверить файлы.");
            return;
        }

        ArrayList<Text> texts = FileReader.readFiles(args[0]);

        for (int i = 0; i < texts.size(); i++)
            for (int j = i; j < texts.size(); j++)
                if (i != j) compareTexts(texts.get(i), texts.get(j));

        //for (Text text: texts) System.out.println(text);
    }
    public static void compareTexts(Text text1, Text text2) {
        System.out.printf("Текст №1: %s\nшинглов: %s\n", text1.getFilename(), text1.getShingles().size());
        System.out.printf("Текст №2: %s\nшинглов: %s\n", text2.getFilename(), text2.getShingles().size());

        compareShingles(text1.getShingles(), text2.getShingles());
        System.out.println();
    }
    public static void compareShingles(ArrayList<Integer> shingles1, ArrayList<Integer> shingles2) {
        double plagiat;
        int counter = 0;
        for (int shingle1 : shingles1) {
            for (int shingle2 : shingles2) {
                if (shingle1 == shingle2)
                    counter++;
            }
        }

        plagiat = (double)counter / Math.min(shingles1.size(), shingles2.size()) * 100;
        if (plagiat > 100) plagiat = 100;

        System.out.println("найдено совпадений: " + counter);
        System.out.printf("плагиат: %.1f%%\n", plagiat);
    }
}

