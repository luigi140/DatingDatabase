package model;

public class StudentProfile {

    private String name;
    private String sexualPreference;
    private String gender;
    private String major;
    private String description;
    private int age;

    public StudentProfile(String studentName, int age, String gender, String major,String sexualPreference,
                          String description) {
        this.name = studentName;
        if (age >= 18) {           // Below 18, you are a minor. Hence,  you are not eligible
            this.age = age;        // for the DatingApp.
        } else {
            System.out.println("Age must be above 18");
        }
        this.gender = gender;
        this.major = major;
        this.sexualPreference = sexualPreference;
        this.description = description;
    }


    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public String getGender() {
        return this.gender;
    }

    public String getDescription() {
        return this.description;
    }

    public String getMajor() {
        return this.major;
    }

    public String getSexualPreference() {
        return this.sexualPreference;
    }

    public String setName(String updateName) {
        this.name = updateName;
        return this.name;
    }

    public int setAge(int updateAge) {
        if (updateAge >= 18) {           // Below 18, you are a minor. Hence,  you are not eligible
            this.age = updateAge;        // for the DatingApp.
        } else {
            this.age = 0;
            System.out.println("Age must be above 18");
        }
        return this.age;
    }

    public String setGender(String updateGender) {
        this.gender = updateGender;
        return this.gender;
    }

    public String setDescription(String updateDescription) {
        this.description = updateDescription;
        return this.description;
    }

    public String setMajor(String updateMajor) {
        this.major = updateMajor;
        return this.major;
    }

    public String setSexualPreference(String updateSexualPreference) {
        this.sexualPreference = updateSexualPreference;
        return this.sexualPreference;
    }


}
