package org.example;

import org.example.filereaders.DocxTextReader;
import org.example.filereaders.PdfTextReader;
import org.example.filereaders.TextReader;

public class Main {
    public static void main(String[] args) {
        String text = readFile(args);

        System.out.println(text);
    }

    public static String readFile(String[] fileNames) {
        String filename = fileNames.length > 0? fileNames[0] :
                "C:/Users/user/IdeaProjects/antiplagiat/src/main/resources/textfiles/отчет2.docx";
        String fileExtension = filename.substring(filename.indexOf("."));

        TextReader reader = null;
        String text = null;

        switch (fileExtension){
            case ".pdf": reader = new PdfTextReader(); break;
            case ".docx": reader = new DocxTextReader(); break;
            default:
                System.err.println("Неподдерживаемый формат файла.");
                break;
        }

        try {
            text = reader.read(filename);
        } catch (Exception e) {
            System.err.println("ошибка чтения файла");
        }

        return text;
    }
}

