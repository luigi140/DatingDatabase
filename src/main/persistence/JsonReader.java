package persistence;

import model.StudentList;
import model.StudentProfile;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public StudentList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseStudentList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private StudentList parseStudentList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        StudentList list = new StudentList(name);
        addStudents(list, jsonObject);
        return list;
    }

    // MODIFIES: list
    // EFFECTS: parses students from JSON object and adds them to student list
    private void addStudents(StudentList list, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("students");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addStudent(list, nextThingy);
        }
    }

    // MODIFIES: list
    // EFFECTS: parses student profile from JSON object and adds it to workroom
    private void addStudent(StudentList list, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int age = jsonObject.getInt("age");
        String gender = jsonObject.getString("gender");
        String major = jsonObject.getString("major");
        String spp = jsonObject.getString("sexualPreference");
        String des = jsonObject.getString("description");

        StudentProfile sp = new StudentProfile(name, age, gender, major, spp, des);
        list.addStudentProfile(sp);
    }
}
