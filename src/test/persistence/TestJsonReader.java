package persistence;

import model.StudentProfile;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestJsonReader {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            StudentProfile sp = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyStudentProfile() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyStudentProfile.json");
        try {
            StudentProfile sp = reader.read();
            assertEquals("", sp.getName());
            assertEquals(0, sp.getAge());
            assertEquals("", sp.getGender());
            assertEquals("", sp.getMajor());
            assertEquals("", sp.getSexualPreference());
            assertEquals("", sp.getDescription());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralStudentProfile.json");
        try {
            StudentProfile sp = reader.read();
            assertEquals("DDP", sp.getName());
            assertEquals(19, sp.getAge());
            assertEquals("Male", sp.getGender());
            assertEquals("Undecided", sp.getMajor());
            assertEquals("Fluid", sp.getSexualPreference());
            assertEquals("Blah", sp.getDescription());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
