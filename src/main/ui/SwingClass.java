package ui;


import model.StudentList;
import model.StudentProfile;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;


public class SwingClass extends JPanel implements ListSelectionListener {

    private static final String JSON_STORE = "./data/profiles.json";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    private static final String addString = "Add";
    private static final String saveString = "Save";
    private static final String loadString = "Load";

    private JList list;
    private JFrame frame;
    private JButton saveButton;
    private JButton loadButton;
    private JButton addButton;
    private JTextField studentName;
    private JTextField description;
    private DefaultListModel studentList;
    private StudentList studentList2 = new StudentList("List");


    public SwingClass() {
        super(new BorderLayout());

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        frame = new JFrame();
        frame.setBounds(0, 0, 500, 500);
        studentList = new DefaultListModel();

        refreshStudentList();
        listScrollPane();
        createPanelWithBoxLayout();

        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void refreshStudentList() {
        for (StudentProfile s : studentList2.getStudentProfiles()) {
            studentList.addElement(s.getName() + " " + s.getDescription());
        }
    }

    //Create the list and put it in a scroll pane.
    public void listScrollPane() {
        list = new JList(studentList);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);

        addButton = new JButton(addString);
        AddListener addListener = new AddListener(addButton);
        addButton.setActionCommand(addString);
        addButton.addActionListener(addListener);
        addButton.setEnabled(false);

        saveButton = new JButton(saveString);
        saveButton.setActionCommand(saveString);
        saveButton.addActionListener(new SaveListener());

        loadButton = new JButton(loadString);
        loadButton.setActionCommand(loadString);
        loadButton.addActionListener(new LoadListener());

        studentName = new JTextField(10);
        studentName.addActionListener(addListener);
        studentName.getDocument().addDocumentListener(addListener);

        description = new JTextField(10);
        description.addActionListener(addListener);
        description.getDocument().addDocumentListener(addListener);

    }

    //Create a panel that uses BoxLayout.
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
        buttonPane.add(studentName);
        buttonPane.add(description);
        buttonPane.add(addButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    // EFFECTS: saves the studentName to file
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
    // EFFECTS: loads studentName from file
    private void loadStudentProfile() {
        try {
            studentList2 = jsonReader.read();
            System.out.println("Loaded " + studentList2.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            saveStudentProfile();

            int size = studentList.getSize();

            if (size == 0) { //Nobody's left, disable firing.
                saveButton.setEnabled(false);
            } else {
                saveButton.setEnabled(true);
            }
        }
    }

    class LoadListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            loadStudentProfile();
            refreshStudentList();

            int size = studentList.getSize();

            if (size == 0) {
                loadButton.setEnabled(true);

            } else { //Select an index.
                loadButton.setEnabled(false);
            }
        }
    }

    //This listener is shared by the text field and the hire button.
    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private final JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }

        //Required by ActionListener.
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

            studentList.addElement(studentName.getText() + " " + description.getText());

            studentName.requestFocusInWindow();
            studentName.setText("");

            description.requestFocusInWindow();
            description.setText("");

        }

        //Required by DocumentListener.
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //Required by DocumentListener.
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //Required by DocumentListener.
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    //This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {

            if (list.getSelectedIndex() == -1) {
                //No selection
                saveButton.setEnabled(false);

            } else {
                //Selection
                saveButton.setEnabled(true);
            }
        }
    }
}
