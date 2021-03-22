package persistence;

import model.StudentProfile;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestJson {
    protected void checkStudent(String name, int age, String gender, String major, String spp, String des,
                                StudentProfile student) {

        assertEquals(name, student.getName());
        assertEquals(age, student.getAge());
        assertEquals(gender, student.getGender());
        assertEquals(major, student.getMajor());
        assertEquals(spp, student.getSexualPreference());
        assertEquals(des, student.getDescription());
    }
}