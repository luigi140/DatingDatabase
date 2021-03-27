package persistence;

import model.StudentProfile;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestJson {
    protected void checkStudent(String name, String des, StudentProfile student) {

        assertEquals(name, student.getName());
        assertEquals(des, student.getDescription());
    }
}