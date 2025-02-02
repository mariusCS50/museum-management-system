package org.example.entities;

import java.io.PrintWriter;

public class Guide extends Professor implements Observer{
    public Guide(String surname, String name, String role) {
        super(surname, name, role);
    }

    @Override
    public void update(PrintWriter writer, String message) {
        writer.println("To: " + this.getEmail() + " ## Message: " + message);
    }
}
