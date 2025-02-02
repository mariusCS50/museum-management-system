package org.example.entities;

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

        if (role.equals("ghid")) {
            Guide guide = new Guide(surname, name, role);
            guide.setAge(age);
            guide.setEmail(email);
            guide.setSchool(school);
            guide.setExperience(studyYearOrExperience);
            return guide;
        }

        if (params[3].equals("student")) {
            Student student = new Student(surname, name, role);
            student.setAge(age);
            student.setEmail(email);
            student.setSchool(school);
            student.setStudyYear(studyYearOrExperience);
            return student;
        }

        if (params[3].equals("profesor")) {
            Professor professor = new Professor(surname, name, role);
            professor.setAge(age);
            professor.setEmail(email);
            professor.setSchool(school);
            professor.setExperience(studyYearOrExperience);
            return professor;
        }

        return new Person(surname, name, role);
    }
}