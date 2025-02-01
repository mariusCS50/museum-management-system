package org.example;

import java.util.Collection;
import java.util.HashMap;

public class Database {
    private static Database database;
    private HashMap<Long, Museum> museums;
    private HashMap<GroupKey, Group> groups;

    private Database() {
        museums = new HashMap<>();
        groups = new HashMap<>();
    }

    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    public void clear() {
        museums.clear();
        groups.clear();
    }

    public void addMuseum(Museum museum) {
        museums.put(museum.getCode(), museum);
    }

    public void addMuseums(HashMap<Long, Museum> museumsCollection) {
        museums.putAll(museumsCollection);
    }

    public void addGroup(GroupKey key, Group group) {
        groups.put(key, group);
    }

    public void addGroups(HashMap<GroupKey, Group> groupsCollection) {
        groups.putAll(groupsCollection);
    }

    public HashMap<Long, Museum> getMuseums() {
        return museums;
    }

    public HashMap<GroupKey, Group> getGroups() {
        return groups;
    }
}