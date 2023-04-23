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

        for (int i = 0; i < texts.size(); i++) {
            for (int j = i; j < texts.size(); j++) {
                if (i == j) continue;

                System.out.println("----------");
                System.out.println("comparing:");
                System.out.println(texts.get(i).getFilename());
                System.out.println(texts.get(j).getFilename());
                compareShingles(texts.get(i).getShingles(), texts.get(j).getShingles());
                System.out.println("----------\n\n\n");
            }
        }
//        System.out.println("TEXTS: ");
//        for (Text text: texts) {
//            System.out.println(text.getFilename() + ":");
//            System.out.println(text.getText());
//            System.out.println("------------------------------------------------------------------------");
//        }
    }

    public static void compareShingles(ArrayList<Integer> shingles1, ArrayList<Integer> shingles2) {
        int counter = 0;
        for (int shingle1 : shingles1) {
            for (int shingle2 : shingles2) {
                if (shingle1 == shingle2)
                    counter++;
            }
        }
        System.out.println("text1 contain shingles: " + shingles1.size());
        System.out.println("text2 contain shingles: " + shingles2.size());
        System.out.println("matched shingles: " + counter);
        System.out.println("% of plagiat: " + (double)counter / Math.min(shingles1.size(), shingles2.size()) * 100);
    }
}

