package ui;


import model.StudentProfile;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class DatingAppNew {
    private static final String JSON_STORE = "./data/profiles.json";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    private StudentProfile student1;
    private Scanner input;

    public DatingAppNew() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runDatingApp();
    }


    public void runDatingApp() {
        input = new Scanner(System.in);  // Create a Scanner object
        String command;

        System.out.println("What do you want to do?");
        displayMenu();
        command = input.next();

        if (command.equalsIgnoreCase("a")) {
            newProfile();
        } else if (command.equalsIgnoreCase("r")) {
            deleteProfile();
        } else if (command.equalsIgnoreCase("f")) {
            editProfile();
        } else if (command.equalsIgnoreCase("f")) {
            System.out.println("Okay ... see you again!");
        }
    }


    private void deleteProfile() {
        StudentProfile emptyProfile = new StudentProfile("", 0, "", "", "",
                "");
        try {
            jsonWriter.open();
            jsonWriter.write(emptyProfile);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }

    }

    private void newProfile() {

        System.out.println("What's your name?");
        String name = input.next();

        System.out.println("What's your age?");
        int age = Integer.parseInt(input.next());

        System.out.println("Please input your gender.");
        String gender = input.next();

        System.out.println("What's your major?");
        String major = input.next();

        System.out.println("What's your sexual preference?");
        String sexualPreference = input.next();

        System.out.println("Use this space to describe yourself.");
        String des = input.next();

        StudentProfile profile = new StudentProfile(name, age, gender, major, sexualPreference, des);
        try {
            jsonWriter.open();
            jsonWriter.write(profile);
            jsonWriter.close();
            System.out.println("Saved " + profile.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void editProfile() {
        try {
            student1 = jsonReader.read();
            System.out.println("Loaded " + student1.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

        System.out.println("Choose what you want to edit.");
        System.out.println("0: To Edit Name");
        System.out.println("1: To Edit Age");
        System.out.println("2: To Edit Gender");
        System.out.println("3: To Edit Major");
        System.out.println("4: To Edit Sexual Preference");
        System.out.println("5: To Edit Description");

        input = new Scanner(System.in);  // Create a Scanner object
        String command;
        command = input.nextLine();


        if (command.equals(0)) {
            student1.setName(command);
        } else if (command.equals(1)) {
            Integer.toString(student1.setAge(Integer.parseInt(command)));
        } else if (command.equals(2)) {
            student1.setGender(command);
        } else if (command.equals(3)) {
            student1.setMajor(command);
        } else if (command.equals(4)) {
            student1.setSexualPreference(command);
        } else if (command.equals(5)) {
            student1.setDescription(command);
        }
    }

    private void displayMenu() {
        System.out.println("a: Choose me to Add Profile.");
        System.out.println("r: Choose me to Remove Profile.");
        System.out.println("f: Choose me to Edit Profile.");
        System.out.println("q: I want to quit.");
    }

    // EFFECTS: saves the studentProfile to file
    private void saveStudentProfile() {
        try {
            jsonWriter.open();
            jsonWriter.write(student1);
            jsonWriter.close();
            System.out.println("Saved " + student1.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads studentProfile from file
    private void loadStudentProfile() {
        try {
            student1 = jsonReader.read();
            System.out.println("Loaded " + student1.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


}

