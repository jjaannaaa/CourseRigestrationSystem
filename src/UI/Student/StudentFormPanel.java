package UI.Student;

import javax.swing.*;
import java.awt.*;

public class StudentFormPanel extends JPanel {
    JTextField txtName = new JTextField(15);
    JTextField txtEmail = new JTextField(15);

    public StudentFormPanel() {
        setLayout(new GridLayout(2, 2, 10, 10));

        add(new JLabel("Name:"));
        add(txtName);

        add(new JLabel("Email:"));
        add(txtEmail);
    }

    public String getNameText() {
        return txtName.getText();
    }

    public String getEmailText() {
        return txtEmail.getText();
    }
    public void setNameText(String name) {
        txtName.setText(name);
    }
    public void setEmailText(String email) {
        txtEmail.setText(email);
    }
    public void clearFields() {
        txtName.setText("");
        txtEmail.setText("");
    }
}
