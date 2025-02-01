package org.example;

import java.io.IOException;

public interface FileProcessingStrategy {
    void processFile(String... args) throws IOException;
}
