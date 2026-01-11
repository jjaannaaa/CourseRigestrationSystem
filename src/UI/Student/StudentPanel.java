package UI.Student;

import Repository.StudentRepo;
import UI.Registration.RegistrationPanel;

import javax.swing.*;
import java.awt.*;

public class StudentPanel extends JPanel {
    private StudentFormPanel form;
    private StudentTablePanel table;
    private StudentButtonPanel buttons;

    public StudentPanel() {
        setLayout(new BorderLayout());

        form = new StudentFormPanel();
        table = new StudentTablePanel();
        buttons = new StudentButtonPanel();

        add(form, BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        wireActions();
    }

    private void wireActions() {
        buttons.getAddButton().addActionListener(e -> addStudent());
        buttons.getDeleteButton().addActionListener(e -> deleteStudent());
        buttons.getUpdateButton().addActionListener(e -> updateStudent());
        buttons.getBtnClear().addActionListener(e -> form.clearFields());
        table.getTable().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) fillFormFromTable();
        });
    }

    private void addStudent() {
        String name = form.getNameText();
        String email = form.getEmailText();

        if (name.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Name and Email are required",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        StudentRepo.insertStudent(name, email);
        table.loadStudents();
        form.clearFields();
    }

    private void deleteStudent() {
        int studentId = table.getSelectedStudentId();

        if (studentId == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select a student to delete",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this student?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) return;

        StudentRepo.deleteStudent(studentId);
        table.loadStudents();
        form.clearFields();
    }

    private void fillFormFromTable() {
        int row = table.getTable().getSelectedRow();
        if (row == -1) return;

        String name  = (String) table.getTable().getValueAt(row, 1);
        String email = (String) table.getTable().getValueAt(row, 2);

        form.setNameText(name);
        form.setEmailText(email);
    }

    private void updateStudent() {
        int studentId = table.getSelectedStudentId();

        if (studentId == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select a student to update",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String name = form.getNameText();
        String email = form.getEmailText();

        if (name.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Name and Email cannot be empty",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        System.out.println(studentId);
        System.out.println(name);
        System.out.println(email);
        StudentRepo.updateStudent(studentId, name, email);
        table.loadStudents();
        form.clearFields();
    }
}
