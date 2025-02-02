package org.example.strategies;

import org.example.information.Database;
import org.example.entities.*;
import org.example.exceptions.*;
import org.example.information.Review;
import org.example.locations.Museum;

import java.io.*;
import java.util.*;

public class GroupProcessingStrategy implements FileProcessingStrategy {
    private Database database = Database.getInstance();

    @Override
    public void processFile(String... args) throws IOException {
        Scanner scanner = new Scanner(new FileReader(args[0] + ".in"));
        PrintWriter writer = new PrintWriter(new FileWriter(args[0] + ".out"));

        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String[] params = scanner.nextLine().split("\\|");
            try {
                switch (params[0]) {
                    case "ADD GUIDE" : writer.println(addGuide(params));
                        break;
                    case "FIND GUIDE" : writer.println(findGuide(params));
                        break;
                    case "REMOVE GUIDE" : writer.println(removeGuide(params));
                        break;
                    case "ADD MEMBER" : writer.println(addMember(params));
                        break;
                    case "FIND MEMBER" : writer.println(findMember(params));
                        break;
                    case "REMOVE MEMBER" : writer.println(removeMember(params));
                        break;
                    case "ADD REVIEW" : writer.println(addReview(params));
                        break;
                    case "REMOVE REVIEW" : writer.println(removeReview(params));
                        break;
                }
            } catch (GuideExistsException | GuideTypeException | GroupNotExistsException | GroupThresholdException |
                     PersonNotExistsException e) {
                writer.println(e.getMessage());
            }
        }
        writer.close();
    }

    private String addGuide(String[] params) throws GuideTypeException, GuideExistsException {
        GroupKey groupKey = new GroupKey(Integer.parseInt(params[9]), params[10]);
        Group group = database.getGroups().get(groupKey);
        Museum museum = database.getMuseums().get(Long.parseLong(params[9]));
        Person guide = PersonFactory.createPerson(params);

        if (guide instanceof Student) {
            throw new GuideTypeException(params[9], params[10], guide.toString());
        }

        if (group == null) {
            group = new Group(null, Integer.parseInt(params[9]), params[10]);
            database.addGroup(groupKey, group);
        }

        if (group.getGuide() != null) {
            throw new GuideExistsException(params[9], params[10], group.getGuide().toString());
        }

        group.setGuide((Guide) guide);
        if (museum != null) {
            museum.addObserver((Guide) guide);
        }
        return params[9] + " ## " + params[10] + " ## " + "new guide: " + guide.toString();
    }

    private String findGuide(String[] params) throws GuideTypeException, GroupNotExistsException {
        GroupKey groupKey = new GroupKey(Integer.parseInt(params[9]), params[10]);
        Group group = database.getGroups().get(groupKey);
        Person guide = PersonFactory.createPerson(params);

        if (guide instanceof Student) {
            throw new GuideTypeException(params[9], params[10], guide.toString());
        }

        if (group == null) {
            throw new GroupNotExistsException(params[9], params[10], "find guide: " + guide.toString());
        }

        if (group.getGuide().equals(guide)) {
            return params[9] + " ## " + params[10] + " ## " + "guide found: " + guide.toString();
        }
        return params[9] + " ## " + params[10] + " ## " + "guide not exists: " + guide.toString();
    }

    private String removeGuide(String[] params) throws GroupNotExistsException {
        GroupKey groupKey = new GroupKey(Integer.parseInt(params[9]), params[10]);
        Group group = database.getGroups().get(groupKey);
        Museum museum = database.getMuseums().get(Long.parseLong(params[9]));
        Person guide = PersonFactory.createPerson(params);

        if (group == null) {
            throw new GroupNotExistsException(params[9], params[10], "removed guide: " + guide.toString());
        }

        group.resetGuide();
        if (museum != null) {
            museum.removeObserver((Guide) guide);
        }

        return params[9] + " ## " + params[10] + " ## " + "removed guide: " + guide.toString();
    }

    private String addMember(String[] params) throws GroupNotExistsException, GroupThresholdException {
        GroupKey groupKey = new GroupKey(Integer.parseInt(params[9]), params[10]);
        Group group = database.getGroups().get(groupKey);
        Person member = PersonFactory.createPerson(params);

        if (group == null) {
            throw new GroupNotExistsException(params[9], params[10], "new member: " + member.toString());
        }

        List<Person> members = group.getMembers();
        if (members.size() >= 10) {
            throw new GroupThresholdException(params[9], params[10], member.toString());
        }

        group.addMember(member);
        return params[9] + " ## " + params[10] + " ## " + "new member: " + member.toString();
    }

    private String findMember(String[] params) throws GroupNotExistsException {
        GroupKey groupKey = new GroupKey(Integer.parseInt(params[9]), params[10]);
        Group group = database.getGroups().get(groupKey);
        Person member = PersonFactory.createPerson(params);

        if (group == null) {
            throw new GroupNotExistsException(params[9], params[10], "find member: " + member.toString());
        }

        List<Person> members = group.getMembers();
        if (members.contains(member)) {
            return params[9] + " ## " + params[10] + " ## " + "member found: " + member.toString();
        }
        return params[9] + " ## " + params[10] + " ## " + "member not exists: " + member.toString();
    }

    private String removeMember(String[] params) throws GroupNotExistsException, PersonNotExistsException {
        GroupKey groupKey = new GroupKey(Integer.parseInt(params[9]), params[10]);
        Group group = database.getGroups().get(groupKey);
        Person member = PersonFactory.createPerson(params);

        if (group == null) {
            throw new GroupNotExistsException(params[9], params[10], "removed member: " + member.toString());
        }

        List<Person> members = group.getMembers();
        if (!members.contains(member)) {
            throw new PersonNotExistsException(params[9], params[10], member.toString());
        }

        group.removeMember(member);
        return params[9] + " ## " + params[10] + " ## " + "removed member: " + member.toString();
    }

    public String addReview(String[] params) throws MuseumNotExistsException {
        Museum museum = database.getMuseums().get(Long.parseLong(params[1]));
        if (museum == null) {
            throw new MuseumNotExistsException(params[9]);
        }

        Person person = PersonFactory.createPerson(params);
        Review review = new Review(Integer.parseInt(params[10]), params[11]);
        museum.getReviews().put(person, review);

        return person.getSurname() + " " + person.getName() + " left a " + review.getScore() + " star review: " + review.getMessage();
    }

    public String removeReview(String[] params) throws MuseumNotExistsException {
        Museum museum = database.getMuseums().get(Long.parseLong(params[1]));
        if (museum == null) {
            throw new MuseumNotExistsException(params[9]);
        }

        Person person = PersonFactory.createPerson(params);
        museum.getReviews().remove(person);

        return person.getSurname() + " " + person.getName() + " removed their review";
    }
}