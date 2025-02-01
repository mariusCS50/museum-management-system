package org.example.strategies;

import org.example.locations.Location;
import org.example.locations.Museum;
import org.example.entities.PersonFactory;
import org.example.database.Database;

import java.io.*;
import java.util.*;

public class MuseumProcessingStrategy implements FileProcessingStrategy {
    private Database database = Database.getInstance();

    @Override
    public void processFile(String... args) throws IOException {
        Scanner scanner = new Scanner(new FileReader(args[0] + ".in"));
        PrintWriter writer = new PrintWriter(new FileWriter(args[0] + ".out"));

        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] params = line.split("\\|");
            try {
                Location location = buildLocation(params);
                Museum museum = buildMuseum(params, location);
                database.addMuseum(museum);
                writer.println(museum.getCode() + ": " + museum.getName());
            } catch (IndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
                writer.println("Exception: Data is broken. ## (" + line + ")");
            }
        }
        writer.close();
    }

    public Location buildLocation(String[] params) {
        Location.Builder locationBuilder = new Location.Builder(params[3], Integer.parseInt(params[16]));

        if (!params[4].isEmpty()) {
            locationBuilder.setLocality(params[4]);
        }
        if (!params[5].isEmpty()) {
            locationBuilder.setAdminUnit(params[5]);
        }
        if (!params[6].isEmpty()) {
            locationBuilder.setAddress(params[6]);
        }
        if (!params[18].isEmpty()) {
            locationBuilder.setLatitude(params[18]);
        }
        if (!params[19].isEmpty()) {
            locationBuilder.setLongitude(params[19]);
        }

        return locationBuilder.build();
    }

    public Museum buildMuseum(String[] params, Location location) {
        Museum.Builder museumBuilder = new Museum.Builder(params[2], Long.parseLong(params[1]), Long.parseLong(params[14]), location);

        if (!params[13].isEmpty()) {
            String[] splitName = params[13].split(" ");
            museumBuilder.setManager(PersonFactory.createPerson(splitName[0], splitName[1], "manager"));
        }
        if (!params[10].isEmpty()) {
            museumBuilder.setFoundingYear(Integer.parseInt(params[10]));
        }
        if (!params[8].isEmpty()) {
            museumBuilder.setPhoneNumber(params[8]);
        }
        if (!params[9].isEmpty()) {
            museumBuilder.setFax(params[9]);
        }
        if (!params[12].isEmpty()) {
            museumBuilder.setEmail(params[12]);
        }
        if (!params[11].isEmpty()) {
            museumBuilder.setUrl(params[11]);
        }
        if (!params[17].isEmpty()) {
            museumBuilder.setProfile(params[17]);
        }

        return museumBuilder.build();
    }
}
