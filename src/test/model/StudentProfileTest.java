package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class testStudentProfile {
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
        assertEquals("Female", testStudentProfile.getSexuality());
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
        assertEquals("Female", testStudentProfile.getSexuality());
        assertEquals("I am from India and I hope to be a Clinical Psychologist",
                testStudentProfile.getDescription());
    }
//
//    @Test
//    void testDeposit() {
//        testStudentProfile.deposit(4.49);
//        assertEquals(504.49, testStudentProfile.getBalance());
//    }
//
//    @Test
//    void testWithdraw() {
//        testStudentProfile.withdraw(150.50);
//        assertEquals(349.50, testStudentProfile.getBalance());
//    }
//
//    @Test
//    void testMultipleDeposits() {
//        testStudentProfile.deposit(150.0);
//        testStudentProfile.deposit(1.49);
//        assertEquals(651.49, testStudentProfile.getBalance());
//    }
//
//    @Test
//    void testMultipleWithdrawals() {
//        testStudentProfile.withdraw(150.0);
//        testStudentProfile.withdraw(2.50);
//        assertEquals(347.50, testStudentProfile.getBalance());
//    }
//
//    @Test
//    void testToString() {
//        assertTrue( testStudentProfile.toString().contains("name = Jane, balance = $500.00"));
//    }
}