package org.example;

public class Student extends Person {
    private String school;
    private int studyYear;

    public Student(String surname, String name, String role) {
        super(surname, name, role);
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }

    @Override
    public String toString() {
        return super.toString() + ", school=" + school + ", studyYear=" + studyYear;
    }
}