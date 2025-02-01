package org.example;

public class GroupThresholdException extends Exception {
    public GroupThresholdException() {
        super("GroupThresholdException: Group cannot have more than 10 members.");
    }

    public GroupThresholdException(String museumCode, String timetable, String person) {
        super(museumCode + " ## " + timetable + " ## " + "GroupThresholdException: Group cannot have more than 10 members. ## (new member: " + person + ")");
    }
}