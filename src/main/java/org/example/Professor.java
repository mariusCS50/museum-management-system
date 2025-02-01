package org.example;

import java.io.PrintWriter;

public class Professor extends Person implements Observer{
    private int experience;
    private String school;

    public Professor(String surname, String name, String role) {
        super(surname, name, role);
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return super.toString() + ", school=" + school + ", experience=" + experience;
    }

    @Override
    public void update(PrintWriter writer, String message) {
        writer.println("To: " + this.getEmail() + " ## Message: " + message);
    }
}