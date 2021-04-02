package ui;


import exceptions.NameException;
import model.StudentList;
import model.StudentProfile;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents the GUI class using Java Swing
public class SwingClass extends JPanel {

    private static final String JSON_STORE = "./data/profiles.json";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    private static final String addString = "Add";
    private static final String saveString = "Save";
    private static final String loadString = "Load";

    private JList list;
    private JFrame frame;
    private final JLabel label1 = new JLabel("Name: ");
    private final JLabel label2 = new JLabel(" Description: ");
    private JButton saveButton;
    private JButton loadButton;
    private JButton addButton;
    private JTextField studentName;
    private JTextField description;
    private final DefaultListModel studentList = new DefaultListModel();
    private StudentList studentList2 = new StudentList("Dating List");


    // EFFECTS: Creates a constructor
    public SwingClass() {
        super(new BorderLayout());

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        frame = new JFrame();
        frame.setBounds(0, 0, 700, 700);

        refreshStudentList();
        listScrollPane();
        createPanelWithBoxLayout();

        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // REQUIRES: a java list of student profiles
    // MODIFIES: this
    // EFFECTS:  returns a Default List model of student profiles for JList to process, or
    //           throws a name exception
    public void refreshStudentList() {
        for (StudentProfile s : studentList2.getStudentProfiles()) {
            try {
                studentList.addElement(s.getName() + " : " + s.getDescription());
            } catch (NameException e) {
                System.out.println("Name too long ... Can't add to Default List!");
            }
        }
    }

    //EFFECTS: Creates the list and puts it in a scroll pane.
    public void listScrollPane() {
        newJList();

        addButton = new JButton(addString);
        AddListener addListener = new AddListener(addButton);
        AudioListener audioListener = new AudioListener();
        addButton.setActionCommand(addString);
        addButton.addActionListener(addListener);
        addButton.addActionListener(audioListener);
        addButton.setEnabled(false);

        saveButton = new JButton(saveString);
        saveButton.setActionCommand(saveString);
        saveButton.addActionListener(new SaveListener());
        saveButton.addActionListener(audioListener);
        saveButton.setEnabled(false);

        loadButton = new JButton(loadString);
        loadButton.setActionCommand(loadString);
        loadButton.addActionListener(new LoadListener());
        loadButton.addActionListener(audioListener);

        studentName = new JTextField(10);
        studentName.addActionListener(addListener);
        studentName.getDocument().addDocumentListener(addListener);

        description = new JTextField(10);
        description.addActionListener(addListener);
        description.getDocument().addDocumentListener(addListener);
    }

    // EFFECTS: Creates a new JList
    public void newJList() {
        list = new JList(studentList);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(5);
    }

    // EFFECTS: Create a panel that uses BoxLayout and adds all the functionalities to the panel.
    public void createPanelWithBoxLayout() {
        JScrollPane listScrollPane = new JScrollPane(list);
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(loadButton);
        buttonPane.add(saveButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(label1);
        buttonPane.add(studentName);
        buttonPane.add(label2);
        buttonPane.add(description);
        buttonPane.add(addButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    // EFFECTS: saves the studentList to file
    private void saveStudentProfile() {
        try {
            jsonWriter.open();
            jsonWriter.write(studentList2);
            jsonWriter.close();
            System.out.println("Saved " + studentList2.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads studentList from file
    private void loadStudentProfile() {
        try {
            studentList2 = jsonReader.read();
            System.out.println("Loaded " + studentList2.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: Creates an ActionListener object for save button
    class SaveListener implements ActionListener {

        // EFFECTS: returns the action performed by the save button
        public void actionPerformed(ActionEvent e) {

            saveStudentProfile();

            int size = studentList.getSize();

            saveButton.setEnabled(size != 0);
        }
    }

    // EFFECTS: Creates an ActionListener object for load button
    class LoadListener implements ActionListener {

        // EFFECTS: returns the action performed by the load button
        public void actionPerformed(ActionEvent e) {

            loadStudentProfile();
            refreshStudentList();
            saveButton.setEnabled(true);

            int size = studentList.getSize();

            if (size == 0) {
                loadButton.setEnabled(true);

            } else {
                loadButton.setEnabled(false);
            }
        }
    }

    // EFFECTS: returns an audio note saved in in the computer under soundName;
    //          Or throws an exception.
    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    // EFFECTS: Creates an Audio ActionListener object for save,load and add buttons
    class AudioListener implements ActionListener {

        // EFFECTS: returns the action performed/audio note on pressing the buttons
        public void actionPerformed(ActionEvent e) {
            playSound("./data/blip.wav");
        }
    }


    // EFFECTS: Creates an ActionListener object for the add button and the two text fields
    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private final JButton button;

        // EFFECTS: Creates an AddListener constructor
        public AddListener(JButton button) {
            this.button = button;
        }

        // EFFECTS: returns the action performed
        public void actionPerformed(ActionEvent e) {
            String name = studentName.getText();
            String des = description.getText();

            studentList2.addStudentProfile(new StudentProfile(name, des));

            //User didn't type in a unique name...
            if (name.equals("")) {
                Toolkit.getDefaultToolkit().beep();
                studentName.requestFocusInWindow();
                studentName.selectAll();
                return;
            }

            studentList.addElement(studentName.getText() + " : " + description.getText());

            studentName.requestFocusInWindow();
            studentName.setText("");

            description.requestFocusInWindow();
            description.setText("");

        }


        // EFFECTS: Enables the add button when text is inputted
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        // EFFECTS: Disables the add button when no text inputted
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        // EFFECTS: Updates the enable state of the button
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        // EFFECTS: Enables functionality of the button
        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        // EFFECTS: Disables functionality of the button
        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }
}
