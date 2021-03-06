package model;

import org.json.JSONObject;

public class StudentProfile {

    private String name;
    private String sexualPreference;
    private String gender;
    private String major;
    private String description;
    private int age;

    //EFFECTS: Creates a constructor for studentProfile
    public StudentProfile(String studentName, int age, String gender, String major, String sexualPreference,
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


    //EFFECTS: returns the field name
    public String getName() {
        return this.name;
    }

    //EFFECTS: returns the field age
    public int getAge() {
        return this.age;
    }

    //EFFECTS: returns the field gender
    public String getGender() {
        return this.gender;
    }

    //EFFECTS: returns the field description
    public String getDescription() {
        return this.description;
    }

    //EFFECTS: returns the field major
    public String getMajor() {
        return this.major;
    }

    //EFFECTS: returns the field sexualPreference
    public String getSexualPreference() {
        return this.sexualPreference;
    }

    //MODIFIES: this
    //EFFECTS: updates the name of the studentProfile and returns it
    public String setName(String updateName) {
        this.name = updateName;
        return this.name;
    }

    //MODIFIES: this
    //EFFECTS: updates the age of the studentProfile and returns it
    //       : age to be updated must be >= 18
    //       : sets the age to 0 if age is < 18
    public int setAge(int updateAge) {
        if (updateAge >= 18) {           // Below 18, you are a minor. Hence,  you are not eligible
            this.age = updateAge;        // for the DatingApp.
        } else {
            this.age = 0;
            System.out.println("Age must be above 18");
        }
        return this.age;
    }


    //MODIFIES: this
    //EFFECTS: updates the gender of the studentProfile and returns it
    public String setGender(String updateGender) {
        this.gender = updateGender;
        return this.gender;
    }

    //MODIFIES: this
    //EFFECTS: updates the description of the studentProfile and returns it
    public String setDescription(String updateDescription) {
        this.description = updateDescription;
        return this.description;
    }

    //MODIFIES: this
    //EFFECTS: updates the major of the studentProfile and returns it
    public String setMajor(String updateMajor) {
        this.major = updateMajor;
        return this.major;
    }

    //MODIFIES: this
    //EFFECTS: updates the sexualPreference of the studentProfile and returns it
    public String setSexualPreference(String updateSexualPreference) {
        this.sexualPreference = updateSexualPreference;
        return this.sexualPreference;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("sexualPreference", sexualPreference);
        json.put("gender", gender);
        json.put("major", major);
        json.put("description", description);
        json.put("age", age);
        return json;
    }


}

