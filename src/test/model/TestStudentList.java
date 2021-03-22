package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestStudentList {

    StudentList testStudentList;
    StudentProfile student1;


    @BeforeEach
    public void setup() {
        this.testStudentList = new StudentList("Kabir");
        this.student1 = new StudentProfile("Kabir", 18, "Male",
                "Psychology", "Female",
                "I am from India and I hope to be a Clinical Psychologist");
    }

    @Test
    public void testAddStudentProfile() {

        testStudentList.addStudentProfile(this.student1);
        assertTrue(testStudentList.addStudentProfile(this.student1));
        assertEquals(2, testStudentList.length());

    }

    @Test
    public void testRemoveStudentProfile() {

        testStudentList.addStudentProfile(this.student1);
        testStudentList.addStudentProfile(this.student1);
        testStudentList.addStudentProfile(this.student1);
        assertEquals(3, testStudentList.length());

        assertTrue(testStudentList.removeStudentProfile(this.student1));

        assertEquals(2, testStudentList.length());

    }

    @Test
    public void testFilterPreferences() {
        StudentProfile student2 = new StudentProfile("Guy", 20, "Male",
                "Computer Science", "Female",
                "I simply like coding...");
        StudentProfile student3 = new StudentProfile("Kiara", 20, "Female",
                "Law", "Female",
                "I like to experiment :D");
        StudentProfile student4 = new StudentProfile("Eden", 21, "Male",
                "Computer Science", "Female",
                "Troubleshooting and solving real-life problems through code brings me joy");
        StudentProfile student5 = new StudentProfile("Mark", 23, "Queer",
                "Environmental Sciences", "Queer",
                "The world is becoming a better place and I want someone to be a part of it with me");

        testStudentList.addStudentProfile(student2);
        testStudentList.addStudentProfile(this.student1);
        testStudentList.addStudentProfile(student3);
        testStudentList.addStudentProfile(student4);
        testStudentList.addStudentProfile(student5);

        testStudentList.filterPreferences("Male", 20, "Computer Science");

        assertEquals(4,
                testStudentList.filterPreferences("Male", 20,
                        "Computer Science").length());
    }

    @Test
    public void testFilterPreferencesWithEmptyStudentList() {

        assertTrue(testStudentList.isEmpty());
        testStudentList.filterPreferences("Male", 20, "Computer Science");

        assertEquals(0,
                testStudentList.filterPreferences("Male", 20,
                        "Computer Science").length());
    }

    @Test
    public void testGetNextStudentProfile() {
        testStudentList.addStudentProfile(this.student1);

        assertFalse(testStudentList.isEmpty());
        assertEquals(testStudentList.length(), 1);

        testStudentList.getNextStudentProfile();

        assertEquals(testStudentList.length(), 0);
        assertTrue(testStudentList.isEmpty());
    }


}
