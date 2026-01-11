package UI.Course;

import javax.swing.*;

public class CourseButtonPanel extends JPanel{
    JButton btnAdd = new JButton("Add");
    JButton btnUpdate = new JButton("Update");
    JButton btnDelete = new JButton("Delete");
    JButton btnClear = new JButton("Clear");

    public CourseButtonPanel() {
        add(btnAdd);
        add(btnUpdate);
        add(btnDelete);
        add(btnClear);
    }

    public JButton getAddButton() {
        return btnAdd;
    }
    public JButton getDeleteButton() {
        return btnDelete;
    }
    public JButton getUpdateButton() {
        return btnUpdate;
    }
    public JButton getBtnClear(){
        return btnClear;
    }
}
