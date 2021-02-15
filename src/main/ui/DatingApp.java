package ui;

import jdk.internal.util.xml.impl.Input;
import model.StudentProfile;


import java.util.Scanner;

public class DatingApp {

    private StudentProfile student1;
    private StudentProfile student2;
    private Scanner input;

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String command;

        System.out.println("What do you want to do?");
    }

    //Input.next = command;
    private void processCommand(String command) {
        if (command.equalsIgnoreCase("New Profile")) {
            new StudentProfile();
        } else if (command.equalsIgnoreCase("Delete Profile")) {
            StudentProfile student3 = new StudentProfile();
            student3 = null;
        } else if (command.equals("Find Profile")) {
            findProfile();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void findProfile() {
    }


}
