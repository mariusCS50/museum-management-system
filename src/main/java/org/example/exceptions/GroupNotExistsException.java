package org.example.exceptions;

public class GroupNotExistsException extends Exception {
  public GroupNotExistsException() {
    super("GroupNotExistsException: Group does not exist.");
  }

  public GroupNotExistsException(String museumCode, String timetable, String person) {
    super(museumCode + " ## " + timetable + " ## " + "GroupNotExistsException: Group does not exist. ## (" + person + ")");
  }
}