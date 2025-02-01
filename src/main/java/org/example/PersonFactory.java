package org.example;

public class PersonFactory {
    public static Person createPerson(String surname, String name, String role) {
        return new Person(surname, name, role);
    }

    public static Person createPerson(String[] params) {
        String surname = params[1];
        String name = params[2];
        String role = params[8];
        int age = Integer.parseInt(params[4]);
        String email = (params[5].isEmpty()) ? null : params[5];
        String school = (params[6].isEmpty()) ? null : params[6];
        int studyYearOrExperience = Integer.parseInt(params[7]);

        switch (params[3]) {
            case "student":
                Student student =  new Student(surname, name, role);
                student.setAge(age);
                student.setEmail(email);
                student.setSchool(school);
                student.setStudyYear(studyYearOrExperience);
                return student;
            case "profesor":
                Professor professor = new Professor(surname, name, role);
                professor.setAge(age);
                professor.setEmail(email);
                professor.setSchool(school);
                professor.setExperience(studyYearOrExperience);
                return professor;
            default:
                return new Person(surname, name, role);
        }
    }
}