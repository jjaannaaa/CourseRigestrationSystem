package UI.Course;

import javax.swing.*;
import java.awt.*;

public class CourseFormPanel extends JPanel{
    private JTextField txtName = new JTextField(15);
    public CourseFormPanel(){
        setLayout(new GridLayout(1, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(new JLabel("Course Name"));
        add(txtName);
    }
    public String getNameText() {
        return txtName.getText();
    }
    public void setNameText(String name) {
        txtName.setText(name);
    }
    public void clearFields(){
        setNameText("");
    }
}
