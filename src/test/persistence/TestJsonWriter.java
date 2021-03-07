package persistence;

import model.StudentProfile;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestJsonWriter {

    @Test
    void testWriterInvalidFile() {
        try {
            StudentProfile sp = new StudentProfile("DDP", 19, "Male", "Undecided",
            "Fluid", "Blah");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyStudentProfile() {
        try {
            StudentProfile sp = new StudentProfile("", 0, "", "",
                    "", "");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyStudentProfile.json");
            writer.open();
            writer.write(sp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyStudentProfile.json");
            sp = reader.read();
            assertEquals("", sp.getName());
            assertEquals(0, sp.getAge());
            assertEquals("", sp.getGender());
            assertEquals("", sp.getMajor());
            assertEquals("", sp.getSexualPreference());
            assertEquals("", sp.getDescription());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
    @Test
    void testWriterGeneralStudentProfile() {
        try {
            StudentProfile sp = new StudentProfile("DDP", 19, "Male", "Undecided",
                    "Fluid", "Blah");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralStudentProfile.json");
            writer.open();
            writer.write(sp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralStudentProfile.json");
            sp = reader.read();
            assertEquals("DDP", sp.getName());
            assertEquals(19, sp.getAge());
            assertEquals("Male", sp.getGender());
            assertEquals("Undecided", sp.getMajor());
            assertEquals("Fluid", sp.getSexualPreference());
            assertEquals("Blah", sp.getDescription());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
