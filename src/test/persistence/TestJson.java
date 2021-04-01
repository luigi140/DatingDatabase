package persistence;

import exceptions.NameException;
import model.StudentProfile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestJson {
    protected void checkStudent(String name, String des, StudentProfile student) {

        try {
            assertEquals(name, student.getName());
        } catch (NameException e) {
            fail("NameException thrown");
        }
        assertEquals(des, student.getDescription());
    }
}