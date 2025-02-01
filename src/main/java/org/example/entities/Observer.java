package org.example.entities;

import java.io.PrintWriter;

public interface Observer {
    void update(PrintWriter writer, String message);
}
