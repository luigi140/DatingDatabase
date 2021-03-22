package model;

import org.json.JSONObject;
import persistence.Writable;

public class StudentProfile implements Writable {

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


    @Override
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

