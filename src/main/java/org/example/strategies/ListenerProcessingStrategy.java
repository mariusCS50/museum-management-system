package org.example.strategies;

import java.io.*;

public class ListenerProcessingStrategy implements FileProcessingStrategy {
    private FileProcessingStrategy museumStrategy;
    private FileProcessingStrategy groupStrategy;
    private FileProcessingStrategy eventStrategy;

    public ListenerProcessingStrategy() {
        this.museumStrategy = new MuseumProcessingStrategy();
        this.groupStrategy = new GroupProcessingStrategy();
        this.eventStrategy = new EventProcessingStrategy();
    }

    @Override
    public void processFile(String... args) throws IOException {
        museumStrategy.processFile(args[0]);
        groupStrategy.processFile(args[1]);
        eventStrategy.processFile(args[2]);
    }
}
