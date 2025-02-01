package org.example.exceptions;

public class GuideExistsException extends Exception {
    public GuideExistsException() {
        super("GuideExistsException: Guide already exists.");
    }

    public GuideExistsException(String museumCode, String timetable, String person) {
        super(museumCode + " ## " + timetable + " ## " + "GuideExistsException: Guide already exists. ## (new guide: " + person + ")");
    }
}