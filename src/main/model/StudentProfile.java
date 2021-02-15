package model;

public class StudentProfile {

    private static String name;
    String sexuality;
    String gender;
    String major;
    String description;
    int age;

    public StudentProfile(String studentName, int a, String g, String m,String s,String d) {
        name = studentName;
        if (a >= 17) {
            age = a;
        }
        gender = g;
        major = m;
        sexuality = s;
        description = d;

    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getDescription() {
        return description;
    }

    public String getMajor() {
        return major;
    }

    public String getSexuality() {
        return sexuality;
    }

    private char updateGender(char newGender) {
        return newGender;
    }


    private String updateSexuality(String newSexuality) {
        return newSexuality;
    }


}
