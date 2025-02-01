package org.example;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Database.getInstance().clear();

        FileProcessingStrategy strategy = switch (args[0]) {
            case "museums" -> new MuseumProcessingStrategy();
            case "groups" -> new GroupProcessingStrategy();
            case "listener" -> new ListenerProcessingStrategy();
            default -> null;
        };

        try {
            assert strategy != null;
            strategy.processFile(Arrays.copyOfRange(args, 1, args.length));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
