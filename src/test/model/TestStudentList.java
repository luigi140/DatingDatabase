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
        this.student1 = new StudentProfile("Kabir", "I am from India and I hope to be a Clinical Psychologist");
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
    public void testRemoveStudentProfileWhenEmpty() {

        assertTrue(testStudentList.isEmpty());
        assertEquals(0, testStudentList.length());

        assertFalse(testStudentList.removeStudentProfile(this.student1));

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
