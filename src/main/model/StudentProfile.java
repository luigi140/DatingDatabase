package model;

import org.json.JSONObject;
import persistence.Writable;

public class StudentProfile implements Writable {

    private String name;
    private String description;

    //EFFECTS: Creates a constructor for studentProfile
    public StudentProfile(String studentName, String description) {
        this.name = studentName;
        this.description = description;
    }


    //EFFECTS: returns the field name
    public String getName() {
        return this.name;
    }

    //EFFECTS: returns the field description
    public String getDescription() {
        return this.description;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("description", description);
        return json;
    }


}

