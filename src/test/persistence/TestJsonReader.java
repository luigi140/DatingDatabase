package persistence;

import model.StudentList;
import model.StudentProfile;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestJsonReader extends TestJson{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            StudentList sp = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyStudentProfile() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyStudentList.json");
        try {
            StudentList sp = reader.read();
            assertEquals("Dating List", sp.getName());
            assertEquals(0, sp.length());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralStudentList.json");
        try {
            StudentList sp = reader.read();
            assertEquals("Dating List", sp.getName());
            List<StudentProfile> students = sp.getStudentProfiles();
            assertEquals(2, students.size());
            checkStudent("DDP", "Blah", students.get(0));
            checkStudent("Casey", "Blah", students.get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
