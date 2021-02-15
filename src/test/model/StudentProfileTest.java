package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class testStudentProfile {
    private StudentProfile testStudentProfile;


    @BeforeEach
    void runBefore() {
        testStudentProfile = new StudentProfile("Kabir", 18, "Male", "Psychology",
                "Female", "I am from India and I hope to be a Clinical Psychologist");
    }

    @Test
    void testConstructor() {
        assertEquals("Kabir", testStudentProfile.getName());
        assertEquals(18, testStudentProfile.getAge());
        assertEquals("Male", testStudentProfile.getGender());
        assertEquals("Psychology", testStudentProfile.getMajor());
        assertEquals("Female", testStudentProfile.getSexuality());
        assertEquals("I am from India and I hope to be a Clinical Psychologist",
                testStudentProfile.getDescription());
    }

    @Test
    void testConstructorBelowAge() {
        testStudentProfile = new StudentProfile("Kabir", 15, "Male", "Psychology",
                "Female", "I am from India and I hope to be a Clinical Psychologist");
        assertEquals("Kabir", testStudentProfile.getName());
        assertEquals(0, testStudentProfile.getAge());
        assertEquals("Male", testStudentProfile.getGender());
        assertEquals("Psychology", testStudentProfile.getMajor());
        assertEquals("Female", testStudentProfile.getSexuality());
        assertEquals("I am from India and I hope to be a Clinical Psychologist",
                testStudentProfile.getDescription());
    }

}