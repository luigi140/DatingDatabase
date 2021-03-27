package ui;


import model.StudentList;
import model.StudentProfile;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class DatingAppNew {
    private static final String JSON_STORE = "./data/profiles.json";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    private StudentList list;
    private Scanner input;

    // EFFECTS: creates a constructor
    public DatingAppNew() {
        list = new StudentList("Dating List");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runDatingApp();
    }


    // MODIFIES: this
    // EFFECTS: takes in an input and processes the desired functionality
    public void runDatingApp() {
        boolean keepGoing = true;
        input = new Scanner(System.in);
        String command;

        while (keepGoing) {
            System.out.println("What do you want to do?");
            displayMenu();
            command = input.next();

            if (command.equalsIgnoreCase("a")) {
                newProfile();
            } else if (command.equalsIgnoreCase("r")) {
                printStudentProfiles();
            } else if (command.equalsIgnoreCase("s")) {
                saveStudentProfile();
            } else if (command.equalsIgnoreCase("l")) {
                loadStudentProfile();
            } else if (command.equalsIgnoreCase("q")) {
                keepGoing = false;
                System.out.println("Okay ... see you again!");
            }

        }

    }

    //EFFECTS: adds a profile to the studentList and return's it
    private void newProfile() {

        System.out.println("What's your name?");
        String name = input.next();

        System.out.println("Use this space to describe yourself.");
        String des = input.next();

        list.addStudentProfile(new StudentProfile(name, des));

    }

    // EFFECTS: prints all the studentProfile's in the list to the console
    private void printStudentProfiles() {
        List<StudentProfile> students = list.getStudentProfiles();

        for (StudentProfile t : students) {
            System.out.println(t);
        }
    }

    //EFFECTS: returns options to choose from on the main menu
    private void displayMenu() {
        System.out.println("a: Choose me to Add Profile.");
        System.out.println("r: Choose me to Print Profiles.");
        System.out.println("s: Choose me to Save Profiles.");
        System.out.println("l: Choose me to Load Profiles.");
        System.out.println("q: I want to quit.");
    }


    // EFFECTS: saves the studentProfile to file
    private void saveStudentProfile() {
        try {
            jsonWriter.open();
            jsonWriter.write(list);
            jsonWriter.close();
            System.out.println("Saved " + list.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads studentProfile from file
    private void loadStudentProfile() {
        try {
            list = jsonReader.read();
            System.out.println("Loaded " + list.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


}

