package org.example.entities;

import java.util.*;

public class Person {
    private String surname;
    private String name;
    private String role;
    private int age;
    private String email;

    public Person(String surname, String name, String role) {
        this.surname = surname;
        this.name = name;
        this.role = role;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;  // Check if it's the same object
        if (o == null || getClass() != o.getClass()) return false;  // Check if it's the same type
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(surname, person.surname) &&
                Objects.equals(name, person.name) &&
                Objects.equals(role, person.role) &&
                Objects.equals(email, person.email);
    }

    public String toString() {
        return "surname=" + surname + ", name=" + name + ", role=" + role + ", age=" + age + ", email=" + email;
    }
}