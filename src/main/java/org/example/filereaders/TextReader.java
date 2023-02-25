package org.example.filereaders;

import java.io.IOException;

public interface TextReader {
    String read(String filename) throws IOException;
}
