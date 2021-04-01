package model;

import exceptions.NameException;
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
    public String getName() throws NameException {
        if (this.name.length() >= 25) {
            throw new NameException();
        }
        return this.name;
    }

    //EFFECTS: returns the field description
    public String getDescription() {
        return this.description;
    }


    // EFFECTS: Creates a json object of student profile
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("description", description);
        return json;
    }


}

