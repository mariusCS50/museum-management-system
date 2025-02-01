package org.example.strategies;

import java.io.IOException;

public interface FileProcessingStrategy {
    void processFile(String... args) throws IOException;
}
