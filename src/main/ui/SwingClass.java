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

    private static final String okayString = "Add";
    private static final String removeString = "Remove";

    private JList list;
    private JFrame frame;
    private JButton removeButton;
    private JButton addButton;
    private JTextField studentProfile;
    private DefaultListModel studentList;
    private StudentList studentList2 = new StudentList("List");


    public SwingClass() {
        super(new BorderLayout());

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        frame = new JFrame();
        frame.setBounds(0, 0, 500, 500);
        studentList = new DefaultListModel();

        loadStudentProfile();
        refreshStudentList();
        listScrollPane();
        createPanelWithBoxLayout();

        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void refreshStudentList() {
        for (StudentProfile s : studentList2.getStudentProfiles()) {
            studentList.addElement(s.getName() + " " + s.getAge() + " " + s.getGender() + " " + s.getMajor()
                    + " " + s.getSexualPreference() + " " + s.getDescription());
        }
    }

    //Create the list and put it in a scroll pane.
    public void listScrollPane() {
        list = new JList(studentList);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);

        addButton = new JButton(okayString);
        OkayListener okayListener = new OkayListener(addButton);
        addButton.setActionCommand(okayString);
        addButton.addActionListener(okayListener);
        addButton.setEnabled(false);

        removeButton = new JButton(removeString);
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(new RemoveListener());

        studentProfile = new JTextField(10);
        studentProfile.addActionListener(okayListener);
        studentProfile.getDocument().addDocumentListener(okayListener);
        //String name = studentList.getElementAt(
        //        list.getSelectedIndex()).toString();
    }

    //Create a panel that uses BoxLayout.
    public void createPanelWithBoxLayout() {
        JScrollPane listScrollPane = new JScrollPane(list);
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(removeButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(studentProfile);
        buttonPane.add(addButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    // EFFECTS: saves the studentProfile to file
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
    // EFFECTS: loads studentProfile from file
    private void loadStudentProfile() {
        try {
            studentList2 = jsonReader.read();
            System.out.println("Loaded " + studentList2.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    class RemoveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever is selected.
            int index = list.getSelectedIndex();
            studentList.remove(index);

            int size = studentList.getSize();

            if (size == 0) { //Nobody's left, disable firing.
                removeButton.setEnabled(false);

            } else { //Select an index.
                if (index == studentList.getSize()) {
                    //removed item in last position
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }

    //This listener is shared by the text field and the hire button.
    class OkayListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private final JButton button;

        public OkayListener(JButton button) {
            this.button = button;
        }

        //Required by ActionListener.
        public void actionPerformed(ActionEvent e) {
            String name = studentProfile.getText();

            //User didn't type in a unique name...
            if (name.equals("") || alreadyInList(name)) {
                Toolkit.getDefaultToolkit().beep();
                studentProfile.requestFocusInWindow();
                studentProfile.selectAll();
                return;
            }

            int index = list.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }

            studentList.insertElementAt(studentProfile.getText(), index);
            //If we just wanted to add to the end, we'd do this:
            //listModel.addElement(employeeName.getText());

            //Reset the text field.
            studentProfile.requestFocusInWindow();
            studentProfile.setText("");

            //Select the new item and make it visible.
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }


        //This method tests for string equality. You could certainly
        //get more sophisticated about the algorithm.  For example,
        //you might want to ignore white space and capitalization.
        protected boolean alreadyInList(String name) {
            return studentList.contains(name);
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
                //No selection, disable fire button.
                removeButton.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                removeButton.setEnabled(true);
            }
        }
    }
}
