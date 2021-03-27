package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestStudentProfile {
    private StudentProfile testStudentProfile;


    @BeforeEach
    void runBefore() {
        testStudentProfile = new StudentProfile("Kabir",  "I am from India and I hope to be a Clinical Psychologist");
    }

    @Test
    void testConstructor() {
        assertEquals("Kabir", testStudentProfile.getName());
        assertEquals("I am from India and I hope to be a Clinical Psychologist",
                testStudentProfile.getDescription());
    }


}