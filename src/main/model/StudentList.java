package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import javax.swing.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class StudentList extends DefaultListCellRenderer implements Writable {

    private String name;
    private final LinkedList<StudentProfile> studentList;

    // EFFECTS: Constructs a StudentList
    public StudentList(String name) {
        this.name = name;
        studentList = new LinkedList<>();
    }

    public String getName() {
        return this.name;
    }

    // EFFECTS: returns an unmodifiable list of student profiles in this student List
    public List<StudentProfile> getStudentProfiles() {
        return Collections.unmodifiableList(studentList);
    }

    // MODIFIES: this
    // EFFECTS:  adds a StudentProfile and returns true
    public boolean addStudentProfile(StudentProfile s) {
        return studentList.add(s);
    }

    // MODIFIES: this
    // EFFECTS:  removes a StudentProfile and returns true
    public boolean removeStudentProfile(StudentProfile s) {
        return studentList.remove(s);
    }


    //EFFECTS: returns a new list of studentProfiles with user preferences
    public StudentList filterPreferences(String gender, int age, String major) {
        StudentList filteredList = new StudentList("Filtered List");

        for (StudentProfile i : studentList) {
            if ((i.getGender() == gender && i.getAge() == age && i.getMajor() == major)
                    || i.getGender() == gender || i.getAge() == age || i.getMajor() == major) {
                filteredList.addStudentProfile(i);
            }
        }
        return filteredList;
    }

    //EFFECTS: returns a studentProfile with matching names
    public StudentProfile getStudentWithName(String name) {

        for (StudentProfile i : studentList) {
            if (i.getName().equals(name)) {
                return i;
            }
        }
        return null;
    }


    // REQUIRES: the list should not be empty
    // MODIFIES: this
    // EFFECTS:  returns a list with the first studentProfile removed
    public StudentProfile getNextStudentProfile() {
        return studentList.removeFirst();
    }


    // EFFECTS: returns the number of studentProfiles in the list
    public int length() {
        return studentList.size();
    }


    // EFFECTS: returns true if the list is empty, false otherwise
    public boolean isEmpty() {
        return studentList.size() == 0;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("students", studentsToJson());
        return json;
    }

    // EFFECTS: returns students in this studentList as a JSON array
    private JSONArray studentsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (StudentProfile s : studentList) {
            jsonArray.put(s.toJson());
        }
        return jsonArray;
    }
}
