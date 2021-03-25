package ui;


import model.StudentProfile;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SwingClass extends JPanel implements ListSelectionListener {
    private JList list;
    private DefaultListModel studentList;

    private static final String okayString = "Add";
    private static final String removeString = "Remove";

    private JButton removeButton;
    private JButton addButton;
    private JTextField studentProfile;

    public SwingClass() {
        super(new BorderLayout());

        studentList = new DefaultListModel();
        studentList.addElement(new StudentProfile("DDP", 19, "Male", "Undecided",
                "Fluid", "Blah"));
        studentList.addElement(new StudentProfile("Casey", 20, "Female", "CS",
                "Fluid", "Blah"));

        listScrollPane();

        createPanelWithBoxLayout();

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
        String name = studentList.getElementAt(
                list.getSelectedIndex()).toString();
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
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                removeButton.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                removeButton.setEnabled(true);
            }
        }
    }


    public static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Dating App Ui");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new SwingClass();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

}
