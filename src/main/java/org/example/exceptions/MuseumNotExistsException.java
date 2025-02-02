package org.example.exceptions;

public class MuseumNotExistsException extends RuntimeException {
    public MuseumNotExistsException() {
        super("MuseumNotExistException: Museum does not exist.");
    }

    public MuseumNotExistsException(String museumCode) {
        super("MuseumNotExistException: Museum with code " + museumCode + " does not exist.");
    }
}
