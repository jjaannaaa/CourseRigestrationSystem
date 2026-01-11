package UI.Student;

import Model.Student;
import Repository.StudentRepo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentTablePanel extends JPanel {
    JTable table;
    DefaultTableModel model;

    public StudentTablePanel() {
        setLayout(new BorderLayout());

        model = new DefaultTableModel(
                new String[]{"ID", "Name", "Email"}, 0
        );
        table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);
        loadStudents();
    }

    public void loadStudents() {
        model.setRowCount(0);
        for (Student s : StudentRepo.getStudents()) {
            model.addRow(new Object[]{
                    s.getId(),
                    s.getName(),
                    s.getEmail()
            });
        }
    }

    public int getSelectedStudentId() {
        int row = table.getSelectedRow();
        return row == -1 ? -1 : (int) model.getValueAt(row, 0);
    }
    public JTable getTable() {
        return table;
    }
}
