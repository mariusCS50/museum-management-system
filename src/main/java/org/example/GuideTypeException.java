package org.example;

public class GuideTypeException extends Exception {
    public GuideTypeException() {
        super("GuideTypeException: Guide must be a professor.");
    }

    public GuideTypeException(String museumCode, String timetable, String person) {
        super(museumCode + " ## " + timetable + " ## " + "GuideTypeException: Guide must be a professor. ## (new guide: " + person + ")");
    }
}