package org.example.entities;

import java.util.*;

public class GroupKey {
    private Integer museumCode;
    private String timetable;

    public GroupKey(Integer museumCode, String timetable) {
        this.museumCode = museumCode;
        this.timetable = timetable;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        GroupKey key = (GroupKey) obj;
        return Objects.equals(museumCode, key.museumCode) &&
                Objects.equals(timetable, key.timetable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(museumCode, timetable);
    }
}
