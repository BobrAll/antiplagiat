package org.example.readers;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class FileReader {
    public static String readFile(String filename) {
        String fileExtension = filename.substring(filename.lastIndexOf("."));
        TextReader reader = null;

        switch (fileExtension){
            case ".pdf": reader = new PdfTextReader(); break;
            case ".docx": reader = new DocxTextReader(); break;
            default: System.err.println("Неподдерживаемый формат файла."); break;
        }

        try {
            return reader.read(filename);
        } catch (Exception e) {
            System.err.println("ошибка чтения файла");
            return null;
        }
    }

    public static void readFiles(String path) {
        File dir = new File(path);

        //Поиск всех файлов с расширением .docx и .pdf
        File[] arrFiles = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.matches(".*[(pdf)(docx)]$");
            }
        });

        System.out.println("Найдено файлов: " + arrFiles.length + "\nНачать обработку? (y/n)");
        try {
            if (System.in.read() == 'y')
                for (File file : arrFiles)
                    System.out.println(readFile(file.getAbsolutePath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
