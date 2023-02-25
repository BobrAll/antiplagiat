package org.example.filereaders;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class DocxTextReader implements TextReader {

    @Override
    public String read(String filename) throws IOException {
        String text = "";
        File file = new File(filename);
        FileInputStream fis = new FileInputStream(file.getAbsolutePath());

        XWPFDocument document = new XWPFDocument(fis);

        List<XWPFParagraph> paragraphs = document.getParagraphs();

        for (XWPFParagraph para : paragraphs)
            text += para.getText() + "\n";

        fis.close();
        return text;
    }
}