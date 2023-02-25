package org.example;

import static org.example.readers.FileReader.*;

public class Main {
    public static void main(String[] args) {
        if (args.length != 0)
            processArgs(args);
        else
            System.out.println("Инструкция по запуску:\n" +
                    "-f \"абсолютный_путь_к_файлу\" - позволяет считать, обработать и отобразить файл\n" +
                    "-d \"абсолютный_путь_к_директории\" - позволяет просканировать заданную директорию" +
                    " и найти все файлы с расширением .pdf и .docx, затем передать их в обработку");
    }

    public static void processArgs(String[] args) {
        if (args.length != 2)
            System.out.println("Неверное количество аргументов");
        else switch (args[0]) {
            case "-f": System.out.println(readFile(args[1])); break;
            case "-d": readFiles(args[1]); break;
            default: System.err.println("Введен неверный ключ: " + args[1]); break;
        }
    }
}

