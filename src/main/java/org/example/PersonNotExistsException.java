package org.example;

public class PersonNotExistsException extends Exception {
    public PersonNotExistsException() {
        super("PersonNotExistsException: Person was not found in the group.");
    }

    public PersonNotExistsException(String museumCode, String timetable, String person) {
        super(museumCode + " ## " + timetable + " ## " + "PersonNotExistsException: Person was not found in the group. ## (" + person + ")");
    }
}