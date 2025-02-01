package org.example.locations;

import org.example.entities.Observer;

import java.io.PrintWriter;

public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(PrintWriter writer,  String message);
}
