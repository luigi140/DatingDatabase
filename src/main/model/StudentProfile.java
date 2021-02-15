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
        if (a >= 18) {           // Below 18, you are a minor. Hence,  you are not eligible
            age = a;             // for the DatingApp.
        } else {
            System.out.println("Age must be above 18");
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

    private String updateGender(String newGender) {
        gender = newGender;
        return gender;
    }


    private String updateSexuality(String newSexuality) {
        sexuality = newSexuality;
        return newSexuality;
    }


}
