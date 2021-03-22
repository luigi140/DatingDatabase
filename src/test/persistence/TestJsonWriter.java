package persistence;

import model.StudentList;
import model.StudentProfile;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestJsonWriter extends TestJson{

    @Test
    void testWriterInvalidFile() {
        try {
            StudentList list = new StudentList("Dating List");
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
            StudentList list = new StudentList("Dating List");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyStudentList.json");
            writer.open();
            writer.write(list);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyStudentList.json");
            list = reader.read();
            assertEquals("Dating List", list.getName());
            assertEquals(0, list.length());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
    @Test
    void testWriterGeneralStudentProfile() {
        try {
            StudentList list = new StudentList("Dating List");
            list.addStudentProfile(new StudentProfile("DDP", 19, "Male", "Undecided",
                            "Fluid", "Blah"));
            list.addStudentProfile(new StudentProfile("Casey", 20, "Female", "CS",
                            "Fluid", "Blah"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralStudentList.json");
            writer.open();
            writer.write(list);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralStudentList.json");
            list = reader.read();
            assertEquals("Dating List", list.getName());
            List<StudentProfile> students = list.getStudentProfiles();
            assertEquals(2, students.size());
            checkStudent("DDP", 19, "Male", "Undecided", "Fluid", "Blah",
                    students.get(0));
            checkStudent("Casey", 20, "Female", "CS", "Fluid", "Blah",
                    students.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
