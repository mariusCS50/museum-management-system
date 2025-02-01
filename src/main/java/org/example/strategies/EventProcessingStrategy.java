package org.example.strategies;

import org.example.locations.Museum;
import org.example.database.Database;

import java.io.*;
import java.util.*;

public class EventProcessingStrategy implements FileProcessingStrategy {
    private final Database database = Database.getInstance();

    @Override
    public void processFile(String... args) throws IOException {
        Scanner scanner = new Scanner(new FileReader(args[0] + ".in"));
        PrintWriter writer = new PrintWriter(new FileWriter(args[0] + ".out"));

        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String[] params = scanner.nextLine().split("\\|");
            Museum museum = database.getMuseums().get(Long.parseLong(params[1]));

            String message = museum.getName() + " (" + museum.getCode() + ") " + params[2];
            museum.notifyObservers(writer, message);
        }

        writer.close();
    }
}
