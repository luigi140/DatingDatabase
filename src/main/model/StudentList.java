package model;

import java.util.LinkedList;

public class StudentList {

    private final LinkedList<StudentProfile> studentList;

    // EFFECTS: Constructs a StudentList
    public StudentList() {
        studentList = new LinkedList<>();
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
        StudentList filteredList = new StudentList();

        for (StudentProfile i : studentList) {
            if ((i.getGender() == gender && i.getAge() == age && i.getMajor() == major)
                    || i.getGender() == gender || i.getAge() == age || i.getMajor() == major) {
                filteredList.addStudentProfile(i);
            }
        }
        return filteredList;
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

}
