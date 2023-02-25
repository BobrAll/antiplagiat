package org.example.filereaders;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;

import java.io.IOException;

public class PdfTextReader implements TextReader{
    @Override
    public String read(String filename) throws IOException {
        String text = "";

        PdfReader reader = new PdfReader(filename);
        SimpleTextExtractionStrategy strategy = new SimpleTextExtractionStrategy();

        for (int i = 1; i <= reader.getNumberOfPages(); i++)
            text += PdfTextExtractor.getTextFromPage(reader, i, new SimpleTextExtractionStrategy());

        return text;
    }
}
