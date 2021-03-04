package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestStudentProfile {
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
        assertEquals("Female", testStudentProfile.getSexualPreference());
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
        assertEquals("Female", testStudentProfile.getSexualPreference());
        assertEquals("I am from India and I hope to be a Clinical Psychologist",
                testStudentProfile.getDescription());
    }

    @Test
    void testSetName() {
        assertEquals("Kabir", testStudentProfile.getName());
        testStudentProfile.setName("Darshan");
        assertEquals("Darshan", testStudentProfile.getName());
    }

    @Test
    void testSetAge() {
        assertEquals(18, testStudentProfile.getAge());
        testStudentProfile.setAge(19);
        assertEquals(19, testStudentProfile.getAge());
    }

    @Test
    void testSetAgeBelow18() {
        assertEquals(18, testStudentProfile.getAge());
        testStudentProfile.setAge(15);
        assertEquals(0, testStudentProfile.getAge());
    }

    @Test
    void testSetGender() {
        assertEquals("Male", testStudentProfile.getGender());
        testStudentProfile.setGender("Queer");
        assertEquals("Queer", testStudentProfile.getGender());
    }

    @Test
    void testSetDescription() {
        assertEquals("I am from India and I hope to be a Clinical Psychologist",
                testStudentProfile.getDescription());
        testStudentProfile.setDescription("Just looking for someone to have coffee with...");
        assertEquals("Just looking for someone to have coffee with...",
                testStudentProfile.getDescription());
    }

    @Test
    void testSetMajor() {
        assertEquals("Psychology", testStudentProfile.getMajor());
        testStudentProfile.setMajor("Sociology");
        assertEquals("Sociology", testStudentProfile.getMajor());
    }

    @Test
    void testSetSexualPreference() {
        assertEquals("Female", testStudentProfile.getSexualPreference());
        testStudentProfile.setSexualPreference("Queer");
        assertEquals("Queer", testStudentProfile.getSexualPreference());
    }
}