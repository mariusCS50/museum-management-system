package org.example.entities;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private List<Person> members;
    private Guide guide;
    private Integer museumCode;
    private String timetable;

    public Group(Guide guide, Integer museumCode, String timetable) {
        this.members = new ArrayList<>();
        this.guide = guide;
        this.museumCode = museumCode;
        this.timetable = timetable;
    }

    public void setGuide(Guide guide) {
        this.guide = guide;
    }

    public Guide getGuide() {
        return this.guide;
    }

    public void resetGuide() {
        this.guide = null;
    }

    public void addMember(Person member) {
        this.members.add(member);
    }

    public void removeMember(Person member) {
        this.members.remove(member);
    }

    public List<Person> getMembers() {
        return this.members;
    }
}