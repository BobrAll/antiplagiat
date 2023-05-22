package org.example;

import lombok.Data;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class Text {
    private final int SHINGLE_LEN = 3;
    private String filename;
    private String text;
    private ArrayList<Integer> shingles;

    public Text(String filename, String text) {
        this.filename = filename;
        this.text = text;
        this.shingles = createShingles(text);
    }

    /**
     * @param text - текст, который нужно обработать
     * @return текст, очищенный от всех лишних символов, включая двойные пробелы и т.д.
     */
    private String clearText(String text) {
        final String STOP_SYMBOLS[] = {".", ",", "!", "?", ":", ";", "−", "-", "–", "—", "_", "~", "\\",
                "/", "*", "∗", "(", ")", "+", "@", "#", "№", "$", "%", "^", "&", "=", "'", "\"", "“", "”",
                "`", "«", "»", "[", "]", "{", "}", "|", "\t"};

        final String STOP_WORDS[] = {"без", "безо", "в", "во", "вместо", "вне", "для", "до", "за", "из",
                "изо", "из-за", "из-под", "к", "ко", "кроме", "между", "меж", "на", "над", "надо", "о", "и",
                "об", "обо", "от", "ото", "перед", "передо", "пред", "предо", "по", "под", "подо", "при",
                "про", "ради", "с", "со", "сквозь", "среди", "у", "через", "чрез", "а", "то", "так", "же",
                "также", "будто", "бы", "тем", "что", "того", "да", "тому", "даже", "это", "едва", "если",
                "зато", "зачем", "но", "однако", "таким", "образом", "все", "всё", "затем", "далее", "пока",
                "значит", "тогда", "следовательно", "чтобы", "дабы", "все-таки", "как-будто", "еще", "или",
                "поэтому", "иначе", "как-то", "как", "когда", "лишь", "не", "либо", "ни", "кто", "именно",
                "несмотря", "прежде", "чем", "тех", "особенно", "более", "каким", "почему", "пусть", "раз",
                "после", "потому", "я", "мы", "ты", "вы", "он", "она", "оно", "они", "ними", "ним", "ему",
                "ей", "него", "нему", "меня", "тебя", "мне", "мной", "мною", "тебе", "тобою", "тобой", "вас",
                "вам", "вами", "нём", "им", "его", "её", "неё", "ней", "ею", "нею", "нас", "нам", "возможно",
                "нами", "их", "них", "ими", "этой", "был", "нем", "была", "было", "том", "этом", "быть", "те",
                "только", "который", "которые", "которыми", "типо", "весь", "весьма", "где", "там", "есть",
                "является", "может", "вот", "ну", "кратко", "ведь", "вообще", "общем", "итак", "например",
                "просто", "никак"
        };

        text = extractTextFromTemplate(text);

        text = text.toLowerCase();

        for (String symbol : STOP_SYMBOLS)
            text = text.replace(symbol, "");

        /**
         * Очистка текста от всех двойных пробелов и переносов строки
         */
        while (true) {
            int startLength = text.length();
            text = text.replace("\n", " ").replace("  ", " ");

            if (text.length() == startLength)
                break;
        }

        for (String word : STOP_WORDS)
            text = text.replace(" " + word + " ", " ");

        return text;
    }

    public String extractTextFromTemplate(String text) {
        final String TEMPLATES[] = {
                "Университет ИТМО, факультет программной инженерии и компьютерной техники",
                "Двухнедельная отчётная работа по «Информатике»: аннотация к статье",
                "Дата лекции",
                "Дата сдачи",
                "Выполнил(а)",
                "№ группы",
                "оценка",
                "Фамилия И.О. студента не заполнять",
                "Название статьи/главы книги/видеолекции",
                "ФИО автора статьи (или e-mail)",
                "Дата публикации",
                "Размер статьи",
                "(от 400 слов)",
                "Теги, ключевые слова или словосочетания",
                "Перечень фактов, упомянутых в статье",
                "Позитивные следствия и/или достоинства описанной в статье технологии (минимум три пункта)",
                "Негативные следствия и/или недостатки описанной в статье технологии (минимум три пункта)",
                "Ваши замечания, пожелания преподавателю или анекдот о программистах",
                "Наличие этой графы не влияет на оценку"
        };

        Pattern pattern = Pattern.compile("Теги, ключевые слова или словосочетания.*Ваши замечания, пожелания преподавателю или анекдот о программистах", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find())
            text = text.substring(matcher.start(), matcher.end());
        else
            System.err.println("Несоответствие шаблона");

        for (String template : TEMPLATES)
            text = text.replace(template, "");

        return text;
    }
    private ArrayList<Integer> createShingles(String text) {
        ArrayList<Integer> shingles = new ArrayList<>();
        String[] words = clearText(text).split(" ");

        for (int i = 0; i <= words.length - SHINGLE_LEN; i++) {
            String shingle = "";

            for (int j = 0; j < SHINGLE_LEN; j++)
                shingle += " " + words[i + j] + " ";

            shingles.add(shingle.hashCode());
        }

        return shingles;
    }

    @Override
    public String toString() {
        return "Файл: " + filename + ":\n" + text + "\n";
    }
}
