package org.example.readers;

import org.example.Text;

import java.io.File;
import java.util.ArrayList;

public class FileReader {
    private static String readFile(String filename) {
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

    public static ArrayList<Text> readFiles(String dirPath) {
        File dir = new File(dirPath);
        ArrayList<Text> loadedFiles = new ArrayList<>();

        /**
         * Поиск всех файлов с расширением .docx и .pdf
         */
        File[] files = dir.listFiles((dir1, name) -> name.matches(".*[(pdf)(docx)]$"));

        for (File file : files) {
            String filePath = file.getAbsolutePath();
            Text text = new Text(filePath, readFile(filePath));

            loadedFiles.add(text);
        }

        return loadedFiles;
    }
}
