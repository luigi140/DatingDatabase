package model;

import exceptions.NameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class TestStudentProfile {
    private StudentProfile testStudentProfile;


    @BeforeEach
    void runBefore() {
        testStudentProfile = new StudentProfile("Kabir",  "I am from India and I hope to be a Clinical Psychologist");
    }

    @Test
    void testConstructor() {

        assertEquals("I am from India and I hope to be a Clinical Psychologist",
                testStudentProfile.getDescription());
        try {
            assertEquals("Kabir", testStudentProfile.getName());
        } catch (NameException e) {
            fail("NameException thrown");
        }
    }

    @Test
    void testConstructorWithExceptionThrown() {
        StudentProfile testStudentProfile2 = new StudentProfile("Divyadarshan Anil Punjabi", "Blah");

        assertEquals("I am from India and I hope to be a Clinical Psychologist",
                testStudentProfile.getDescription());
        try {
            assertEquals("Divyadarshan Anil Punjabi", testStudentProfile2.getName());
            fail("NameException shouldn't be thrown");
        } catch (NameException e) {
            // expected
        }
    }


}